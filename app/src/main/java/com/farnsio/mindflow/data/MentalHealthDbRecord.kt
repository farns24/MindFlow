package com.farnsio.mindflow.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class MentalHealthDbRecord(
    @PrimaryKey
    val dateTimeEpoch: Double,
    @ColumnInfo(name = "patience") val patience: Int,
    @ColumnInfo(name = "energy") val energy: Int,
    @ColumnInfo(name = "comments") val comments: String
)
