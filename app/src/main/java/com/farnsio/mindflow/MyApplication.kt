package com.farnsio.mindflow

import android.app.Application

// appComponent lives in the Application class to share its lifecycle
class MyApplication: Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent = DaggerApplicationComponent.builder().application(this)?.build()
}