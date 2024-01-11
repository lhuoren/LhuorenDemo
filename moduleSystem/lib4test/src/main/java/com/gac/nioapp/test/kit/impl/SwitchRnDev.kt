package com.gac.nioapp.test.kit.impl

import android.app.Activity
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.gac.nioapp.test.R
import com.gac.nioapp.test.kit.HycanAbstractKit
import com.gac.nioapp.test.kit.PublicDeveloperKit
import com.gac.nioapp.test.utils.ReactNativeDevConstant
import com.google.auto.service.AutoService
import kotlin.system.exitProcess

/**
 * 切换 rn DEV
 * @className SwitchRnDev
 * @description TODO
 * @author jackChen
 * @since 2022/12/7 3:51 下午
 * @version 1.0
 */
@AutoService(PublicDeveloperKit::class)
class SwitchRnDev : PublicDeveloperKit {

    override fun getPublicDeveloperKit(): HycanAbstractKit {
        return object : HycanAbstractKit() {

            override val name: Int
                get() = if (ReactNativeDevConstant.isRNDev()) R.string.rn_dev_enable else R.string.rn_dev_disable

            override val icon: Int
                get() = R.drawable.ic_launcher

            override fun onAppInit(context: Context?) {
                ARouter.getInstance().inject(this)
            }

            override fun onClickWithReturn(activity: Activity): Boolean {
                ReactNativeDevConstant.toggle()
                exitProcess(0)
                return true
            }

        }
    }

}