package com.farnsio.mindflow.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.*

class MyDateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getEpochMillieconds(localDateTime: LocalDateTime): Long {
        return localDateTime.atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getLocalDate(epochDateTime: Double): LocalDateTime? {
        return Instant.ofEpochMilli(epochDateTime.toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime()
    }
}