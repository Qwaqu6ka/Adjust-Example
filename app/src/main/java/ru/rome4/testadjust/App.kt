package ru.rome4.testadjust

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustConfig
import com.adjust.sdk.LogLevel

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val appToken = "m3vi7aesgw00" // todo find in adjust dashboard
        val environment = AdjustConfig.ENVIRONMENT_PRODUCTION  // todo change when production
        val config = AdjustConfig(this, appToken, environment).apply { setLogLevel(LogLevel.WARN) }
        Adjust.onCreate(config)

        registerActivityLifecycleCallbacks(AdjustLifecycleCallbacks())
    }


    private class AdjustLifecycleCallbacks : ActivityLifecycleCallbacks {
        override fun onActivityResumed(activity: Activity) {
            Adjust.onResume()
        }

        override fun onActivityPaused(activity: Activity) {
            Adjust.onPause()
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
        override fun onActivityStarted(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        override fun onActivityDestroyed(activity: Activity) {}
    }

}