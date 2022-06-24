package com.zvonimirplivelic.toppop

import android.app.Application
import timber.log.Timber

class TopPopApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}