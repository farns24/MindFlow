package com.farnsio.mindflow.ui.graphs

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farnsio.mindflow.MyApplication
import com.farnsio.mindflow.R
import com.farnsio.mindflow.data.DataService
import com.farnsio.mindflow.data.formatter.MyXAxisFormatter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import javax.inject.Inject


class GraphFragment : Fragment(), DataService.Listener {

    private lateinit var lineChart: LineChart
    private lateinit var graphViewModel: GraphViewModel

    @Inject
    lateinit var dataService: DataService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        graphViewModel =
                ViewModelProvider(this).get(GraphViewModel::class.java)

        (activity?.applicationContext as MyApplication).appComponent?.inject(this)
        val root = inflater.inflate(R.layout.fragment_graphs, container, false)

       lineChart = root.findViewById(R.id.chart1)

        dataService.registerListener(this)
        dataService.loadGraphData()

//        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        dataService.unregisterListener(this)
//        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    override fun onGraphDataLoad(lineData: LineData) {
        lineChart.data = lineData
        lineChart.xAxis.valueFormatter = MyXAxisFormatter()
        lineChart.xAxis.textSize = 16f
        lineChart.axisLeft.textSize = 16f
        lineChart.isDragXEnabled =true
        lineChart.isScaleXEnabled = true
        lineChart.invalidate()
    }
}