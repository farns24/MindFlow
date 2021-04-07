package com.farnsio.mindflow.ui.slideshow

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farnsio.mindflow.MyApplication
import com.farnsio.mindflow.R
import com.farnsio.mindflow.data.AppDatabase
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.time.LocalDateTime
import javax.inject.Inject


class GraphFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    @Inject
    lateinit var appDatabase: AppDatabase

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as MyApplication).appComponent?.inject(this)
        slideshowViewModel =
                ViewModelProvider(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_graphs, container, false)
        val lineChart: LineChart = root.findViewById(R.id.chart1)


        var entries: List<Entry> = ArrayList()
        for (data in appDatabase.mentalHealthStatusDao().getAll()) {
            // turn your data into Entry objects
            entries.plus(Entry(LocalDateTime.parse(data.dateTime).dayOfYear.toFloat(), data.energy.toFloat()))
        }


        val dataSet = LineDataSet(entries, "Energy")
        val data : LineData = LineData(dataSet)
        lineChart.data = data
        return root
    }
}