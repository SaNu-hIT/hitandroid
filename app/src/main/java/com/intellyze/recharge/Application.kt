package com.intellyze.recharge

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho

class Application : MultiDexApplication()
{
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}