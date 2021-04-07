package com.farnsio.mindflow.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MentalHealthStatusDao {

    @Insert
    fun insertAll(vararg mentalHealthDbRecord: MentalHealthDbRecord)

    @Query("SELECT * FROM MentalHealthDbRecord")
    fun getAll(): List<MentalHealthDbRecord>
}