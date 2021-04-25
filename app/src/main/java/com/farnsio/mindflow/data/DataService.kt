package com.farnsio.mindflow.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.LineData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataService(val appDatabase: AppDatabase, val chartFormatter: ChartFormatter): ViewModel() {

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
    fun loadGraphData(beginningOfRange: Long, endOfRange: Long)
    {
        viewModelScope.launch(Dispatchers.IO) {

            val rawMentalHealthRecords = appDatabase.mentalHealthStatusDao().getAllInRange(beginningOfRange, endOfRange)
            val dataSet = chartFormatter.formatChart(rawMentalHealthRecords)
            viewModelScope.launch(Dispatchers.Main) {
                listeners.forEach({ it.onGraphDataLoad(dataSet) })
            }
        }
    }

    interface Listener {
        fun onGraphDataLoad(lineDataSet: LineData)
    }
}