package com.farnsio.mindflow

import android.util.Log
import com.farnsio.mindflow.model.MentalHealthStatusModel
import javax.inject.Inject

class MentalHealthStatusDao {

    @Inject constructor()

    fun storeMentalHealthStatus(mentalHealthStatusModel: MentalHealthStatusModel)
    {
        Log.d("MentalHealthStatusDao", mentalHealthStatusModel.toString())
    }
}
