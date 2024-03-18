package com.nzar.ktor_app.app

import android.app.Application
import com.nzar.ktor_app.di.mainModule
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(mainModule)
        }
    }
}