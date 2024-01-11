package com.gac.nioapp.test.helper

import android.app.Application
import android.util.Log
import java.lang.Exception

/**
 * @className ApplicationHelper
 * @description TODO
 * @author heyufei
 * @since 2021/12/2 3:33 下午
 * @version 1.0
 */
object ApplicationHelper {
    private var mApplication: Application? = null

    fun getApplication(): Application {
        if (mApplication != null) {
            return mApplication!!
        }
        try {
            val clazz = Class.forName("android.app.ActivityThread")
            mApplication = clazz.getMethod("currentApplication").invoke(null) as Application
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("ApplicationHelper", "getApplication: 失败", e)
        }
        return mApplication!!
    }
}