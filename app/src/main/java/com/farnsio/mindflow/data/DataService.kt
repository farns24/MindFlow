package com.farnsio.mindflow.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.function.Consumer
import javax.inject.Inject

class DataService(val appDatabase: AppDatabase): ViewModel() {

    val listeners: HashSet<Listener>

    init {
        listeners = HashSet()
    }

    fun registerListener(listener: Listener)
    {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener)
    {
        listeners.remove(listener)
    }
    fun writeData(mentalHealthDbRecord: MentalHealthDbRecord){
       viewModelScope.launch(Dispatchers.IO) { appDatabase.mentalHealthStatusDao().insertAll(mentalHealthDbRecord) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadGraphData()
    {
        viewModelScope.launch(Dispatchers.IO) {
            var entries: List<Entry> = ArrayList()
            for (data in appDatabase.mentalHealthStatusDao().getAll()) {
                // turn your data into Entry objects
                entries.plus(Entry(LocalDateTime.parse(data.dateTime).dayOfYear.toFloat(), data.energy.toFloat()))
            }


            val dataSet = LineDataSet(entries, "Energy")
            viewModelScope.launch(Dispatchers.Main) {
                listeners.forEach({ it.onGraphDataLoad(dataSet) })
            }
        }
    }

    interface Listener {
        fun onGraphDataLoad(lineDataSet: LineDataSet)
    }
}