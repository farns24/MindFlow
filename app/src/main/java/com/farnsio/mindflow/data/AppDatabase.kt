package com.farnsio.mindflow.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = [MentalHealthDbRecord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mentalHealthStatusDao(): MentalHealthStatusDao
}
