package com.farnsio.mindflow.data.formatter

import android.os.Build
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.time.Instant
import java.time.ZoneId

class MyXAxisFormatter :ValueFormatter(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
        return  Instant.ofEpochMilli(value.toLong()).atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }
}