package com.farnsio.mindflow.modules

import com.farnsio.mindflow.MentalHealthStatusDao
import com.farnsio.mindflow.model.MentalHealthStatusModel
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    // Hypothetical dependency on LoginRetrofitService
    @Provides
    fun provideMentalHealthStatusDao(): MentalHealthStatusDao {
        return MentalHealthStatusDao()
    }
}