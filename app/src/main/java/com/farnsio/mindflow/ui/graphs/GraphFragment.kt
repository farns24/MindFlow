package com.farnsio.mindflow.ui.graphs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farnsio.mindflow.MyApplication
import com.farnsio.mindflow.R
import com.farnsio.mindflow.data.AppDatabase
import com.farnsio.mindflow.data.MentalHealthStatusDao
import com.github.mikephil.charting.charts.LineChart
import javax.inject.Inject

class GraphFragment : Fragment() {

    private lateinit var graphViewModel: GraphViewModel

    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        graphViewModel =
                ViewModelProvider(this).get(GraphViewModel::class.java)

        (activity?.applicationContext as MyApplication).appComponent?.inject(this)
        val root = inflater.inflate(R.layout.fragment_graphs, container, false)

        val lineChart: LineChart = root.findViewById(R.id.chart1)


//        var entries: List<Entry> = ArrayList()
//        for (data in appDatabase.mentalHealthStatusDao().getAll()) {
//            // turn your data into Entry objects
//            entries.plus(Entry(LocalDateTime.parse(data.dateTime).dayOfYear.toFloat(), data.energy.toFloat()))
//        }
//
//
//        val dataSet = LineDataSet(entries, "Energy")
//        val data : LineData = LineData(dataSet)
//        lineChart.data = data
        return root
    }
}