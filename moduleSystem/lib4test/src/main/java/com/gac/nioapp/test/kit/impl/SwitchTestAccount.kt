package com.gac.nioapp.test.kit.impl

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.gac.nioapp.test.R
import com.gac.nioapp.test.dialog.SwitchTestAccountDialog
import com.gac.nioapp.test.kit.HycanAbstractKit
import com.gac.nioapp.test.kit.PublicDeveloperKit
import com.google.auto.service.AutoService

/**
 * @ClassName SwitchTestAccount
 * @Description TODO
 * @Author LiuZhiQiang
 * @Date 2021/12/28 10:20
 */
@AutoService(PublicDeveloperKit::class)
class SwitchTestAccount : PublicDeveloperKit{
    /**
     * Didi kit
     */
    override fun getPublicDeveloperKit(): HycanAbstractKit {

        return object : HycanAbstractKit() {
            override val name: Int
                get() = R.string.switch_test_account


            /**
             * 点击回调 带返回值
             * @return true 隐藏面板 false 不隐藏面板
             */
            override fun onClickWithReturn(activity: Activity): Boolean {
                var newInstance = SwitchTestAccountDialog.newInstance(activity as AppCompatActivity)
                newInstance.showDialog()
                return true
            }
        }
    }
}