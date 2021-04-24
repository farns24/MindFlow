package com.farnsio.mindflow.data

import org.junit.Test
import java.time.LocalDateTime

internal class ChartFormatterTest {


    val chartFormatter = ChartFormatter()

    @Test
    fun test() {
        val rawRecords : ArrayList<MentalHealthDbRecord> = ArrayList()
        rawRecords.add(MentalHealthDbRecord(
            LocalDateTime.of(2014, 6, 20, 8, 0).toString(),
        60, 80, "Test comment"))
        val actualResult = chartFormatter.formatChart(rawRecords)
        val expectedResult = "fish"
    }
}