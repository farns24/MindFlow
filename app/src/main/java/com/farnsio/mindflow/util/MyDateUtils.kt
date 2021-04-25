package com.farnsio.mindflow.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneOffset

class MyDateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getEpochMillieconds(localDateTime: LocalDateTime): Long {
        return localDateTime.atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli()
    }
}