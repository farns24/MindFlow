package com.farnsio.mindflow.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataService(val appDatabase: AppDatabase): ViewModel() {
   fun writeData(mentalHealthDbRecord: MentalHealthDbRecord){
       viewModelScope.launch(Dispatchers.IO) { appDatabase.mentalHealthStatusDao().insertAll(mentalHealthDbRecord) }
    }
}