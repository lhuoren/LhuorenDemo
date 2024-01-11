package com.syy.modulebase.manager.helper

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import com.syy.modulebase.manager.UserInfoManager
import com.syy.modulebase.manager.wrap.ILoginService
import com.syy.modulebase.manager.wrap.LoginWrapService

/**
 * @className LoginHelper
 * @description
 * 一键登录 兼容旧代码
 * 不建议使用
 * @author heyufei
 * @since 2021/11/18 4:20 下午
 * @version 1.0
 */
object Key2LoginHelper {

    private val TAG = "Key2LoginHelper"

    @JvmStatic
    fun startActivity1(activity: Activity?, intent: Intent?) {
        LoginWrapService.instance.goLand4Activity(object : ILoginService.LandCallBack {
            override fun landSuccess() {
                needGoOtherActivity(activity, intent)
            }

            override fun landFail(errorMsg: String, errorCode: Int) {}
        })
    }

    /**
     * 登录
     * 成功后：通知源 Activity 登录成功！ 然后跳转目标页
     */
    @JvmStatic
    fun startActivity2(activity: Activity?, intent: Intent?, requestCode: Int) {
        LoginWrapService.instance.goLand4Activity(object : ILoginService.LandCallBack {
            override fun landSuccess() {
                callActivityOnActivityResult(activity, requestCode)
                needGoOtherActivity(activity, intent)
            }

            override fun landFail(errorMsg: String, errorCode: Int) {}
        })
    }

    @JvmStatic
    fun startActivity3(fragment: Fragment, intent: Intent?, requestCode: Int) {
        startActivity2(fragment.requireActivity(), intent, requestCode)
    }

    private fun callActivityOnActivityResult(activity: Activity?, requestCode: Int) {
        if (activity == null) {
            return
        }
        try {
            //反射调用源 Activity onActivityResult方法
            Activity::class.java.declaredMethods.find {
                "onActivityResult" == it.name
            }?.apply {
                isAccessible = true
                invoke(activity, requestCode, Activity.RESULT_OK, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "callActivityOnActivityResult: 反射失败")
        }

    }

    private fun needGoOtherActivity(activity: Activity?, intent: Intent?) {
        if (activity == null) {
            return
        }
        intent?.let {
            UserInfoManager.getInstance().startActivity(activity, intent)
        }
    }

}