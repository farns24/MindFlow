package com.farnsio.mindflow.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.farnsio.mindflow.data.AppDatabase
import com.farnsio.mindflow.data.ChartFormatter
import com.farnsio.mindflow.data.DataService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun provideAppDatabase(mApplication: Application?): AppDatabase {
        return Room.databaseBuilder(mApplication!!, AppDatabase::class.java, "MentalHealthDb").build()
    }

    @Provides
    fun provideChartFormatter(): ChartFormatter {
        return ChartFormatter()
    }

    @Provides
    fun provideDataService(appDatabase: AppDatabase): DataService
    {
        return DataService(appDatabase)
    }
}