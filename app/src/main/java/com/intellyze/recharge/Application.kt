package com.intellyze.recharge
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho

class Application : MultiDexApplication()
{
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        overrideTypeFace()
    }


     private fun overrideTypeFace() {
         val typefaceUtil = TypefaceUtil()
         typefaceUtil.overrideFont(
            applicationContext, "sans",
            "fonts/opensans_semibold.ttf"
        )
    }

}