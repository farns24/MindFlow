package com.farnsio.mindflow.data

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.time.*


class ChartFormatter {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatChart(rawMentalHealthRecords: List<MentalHealthDbRecord>): LineData {
        var energyEntries: ArrayList<Entry> = ArrayList()
        var patienceEntries: ArrayList<Entry> = ArrayList()
        for (data in rawMentalHealthRecords) {
            // turn your data into Entry objects
            energyEntries.add(
                Entry(
                    data.dateTimeEpoch.toFloat(),
                    data.energy.toFloat()
                )
            )
            patienceEntries.add(
                Entry(
                    data.dateTimeEpoch.toFloat(),
                    data.patience.toFloat()
                )
            )
        }

        val energyDataSet =  LineDataSet(energyEntries, "Energy")
        energyDataSet.setDrawCircles(true)
        energyDataSet.lineWidth = 3f
        energyDataSet.color = Color.RED

        val patienceDataSet =  LineDataSet(patienceEntries, "Patience")
        patienceDataSet.setDrawCircles(true)
        patienceDataSet.lineWidth = 3f
        patienceDataSet.color = Color.CYAN

        val dataSets = ArrayList<ILineDataSet>();
        dataSets.add(energyDataSet);
        dataSets.add(patienceDataSet);
        return LineData(dataSets)
    }


}
