package org.koin.sample

import android.app.Application
import org.koin.android.ext.android.startKoin

/**
 * Main Application
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin(this, listOf(appModule))
    }
}
