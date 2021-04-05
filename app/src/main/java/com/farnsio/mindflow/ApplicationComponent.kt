package com.farnsio.mindflow

import android.app.Application
import com.farnsio.mindflow.modules.DataModule
import com.farnsio.mindflow.ui.home.EditFragment
import dagger.Component
import dagger.Provides

// Definition of the Application graph
@Component(modules = [DataModule::class])
interface ApplicationComponent {
    fun inject(fragment: EditFragment)
}

// appComponent lives in the Application class to share its lifecycle
class MyApplication: Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent = DaggerApplicationComponent.create()
}
