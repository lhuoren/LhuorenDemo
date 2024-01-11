package com.gac.nioapp.test.theme

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.gac.nioapp.test.R
import com.gac.nioapp.test.utils.AppStorage

object DayNightManager {
    const val TAG = "DayNightManager"
    const val DAY_NIGHT_KEY = "DAY_NIGHT_KEY"

    enum class MODE(val va: Int) {
        FOLLOW(1),
        ALWAY(2),
        NO(3)
    }

    /**
     * 注意 context参数不能传 application 的context
     */
    @JvmStatic
    fun isNightMode(context: Context): Boolean {
        val mode: Int = context.getResources().getConfiguration().uiMode and Configuration.UI_MODE_NIGHT_MASK
//        return mode == Configuration.UI_MODE_NIGHT_YES
        // FIXME: 2021/12/28 关闭黑夜模式
        return false
    }


    @JvmStatic
    fun initDayNightMode(context: Context) {
        val mode = getDayNightMode()
        Log.e(TAG, "当前模式 $mode")
        setDayNightMode(mode)
    }

    @JvmStatic
    fun getDayNightMode(): Int {
        val mode = AppStorage.getDarkMode()
        Log.e(TAG, "当前 存贮的模式 $mode")
        return mode
    }

    @JvmStatic
    fun setDayNightMode(mode: Int) {
        Log.e(TAG, "设置 存贮的模式 $mode")
        if (mode == MODE.FOLLOW.va) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        } else if (mode == MODE.ALWAY.va) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else if (mode == MODE.NO.va) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun storeDarkMode(mode: Int) {
        Log.e(TAG, "当前 设置的模式: $mode")
        AppStorage.setDarkMode(mode)
        val lastMode = getDayNightMode()
        Log.e(TAG, "当前 获取到的 设置的模式: $lastMode")
        setDayNightMode(mode)
    }

    @JvmStatic
    fun getModeColor(context: Context, resId: Int): Int {
        return ContextCompat.getColor(context, getColor(context, resId))
    }

    private fun getColor(context: Context, resId: Int): Int {
        if (!isNightMode(context)) {
            return resId
        } else {
            when (resId) {
                R.color.primary -> {
                    return R.color.primary_night
                }
                R.color.primaryDark -> {
                    return R.color.primaryDark_night
                }
                R.color.contentTitle -> {
                    return R.color.contentTitle_night
                }
                R.color.contentDescribe -> {
                    return R.color.contentDescribe_night
                }
                R.color.contentSub -> {
                    return R.color.contentSub_night
                }
                R.color.contentTag -> {
                    return R.color.contentTag_night
                }
                R.color.contentLight -> {
                    return R.color.contentLight_night
                }
                R.color.backgroundMain -> {
                    return R.color.backgroundMain_night
                }
                R.color.backgroundPlaceholder -> {
                    return R.color.backgroundPlaceholder_night
                }
                R.color.backgroundMainView -> {
                    return R.color.backgroundMainView_night
                }
                R.color.backgroundPlaceholderView -> {
                    return R.color.backgroundPlaceholderView_night
                }
                R.color.backgroundShadow -> {
                    return R.color.backgroundShadow_night
                }
                else -> {
                    return resId
                }
            }
        }
    }

}