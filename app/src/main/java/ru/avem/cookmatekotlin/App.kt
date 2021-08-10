package ru.avem.cookmatekotlin

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        isAppRunning = false
        super.onTerminate()
    }

    companion object {
        var isAppRunning = true
        lateinit var instance: App
            private set
    }
}
