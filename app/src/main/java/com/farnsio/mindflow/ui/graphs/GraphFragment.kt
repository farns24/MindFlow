package com.farnsio.mindflow.ui.graphs

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farnsio.mindflow.MyApplication
import com.farnsio.mindflow.R
import com.farnsio.mindflow.data.DataService
import com.farnsio.mindflow.data.formatter.MyXAxisFormatter
import com.farnsio.mindflow.util.MyDateUtils
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZoneOffset.of
import javax.inject.Inject


class GraphFragment : Fragment(), DataService.Listener {

    private lateinit var lineChart: LineChart
    private lateinit var dateRange: Button
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
        dateRange = root.findViewById(R.id.adjust_range_button)

        dateRange.setOnClickListener {
            val builder = MaterialDatePicker.Builder.dateRangePicker()
            val picker = builder.build()
            picker.addOnPositiveButtonClickListener {

//                Log.d("DatePicker Activity", "Date String = ${picker.headerText}::  Date epoch values::${it.first}:: to :: ${it.second}")
                it.first?.let { it1 -> it.second?.let { it2 -> dataService.loadGraphData(it1, it2) } }
            }
                picker.show(childFragmentManager, "Date range to display")
        }

        dataService.registerListener(this)
        val myDateUtils = MyDateUtils()
        dataService.loadGraphData(myDateUtils.getEpochMillieconds(LocalDateTime.now()), myDateUtils.getEpochMillieconds(
            LocalDateTime.now().minusDays(7)))

        return root
    }



    override fun onDestroy() {
        super.onDestroy()
        dataService.unregisterListener(this)
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