package com.gac.nioapp.test.public_component

import android.content.Context
import android.content.res.Configuration

/**
 * Application 生命周期回调
 */
interface IApplicationLifeCycleService {

    fun attachBaseContext(context: Context)

    fun onCreate()

    fun onTerminate()

    fun onLowMemory()

    fun onTrimMemory(level: Int)

    fun onConfigurationChanged(newConfig: Configuration)
}