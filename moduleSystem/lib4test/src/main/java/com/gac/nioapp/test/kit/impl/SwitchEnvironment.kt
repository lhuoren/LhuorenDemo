package com.gac.nioapp.test.kit.impl

import android.app.Activity
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.gac.nioapp.test.R
import com.gac.nioapp.test.dialog.SwitchEnvironmentDialog
import com.gac.nioapp.test.kit.HycanAbstractKit
import com.gac.nioapp.test.kit.PublicDeveloperKit
import com.gac.nioapp.test.utils.EnvironmentManager
import com.google.auto.service.AutoService
import com.syy.modulebase.http.constants.HttpUrlConstant


/**
 * @ClassName SwitchEnvironment
 * @Description TODO
 * @Author LiuZhiQiang
 * @Date 2021/12/24 10:52
 */
@AutoService(PublicDeveloperKit::class)
class SwitchEnvironment : PublicDeveloperKit {

    /**
     * Didi kit
     */
    override fun getPublicDeveloperKit(): HycanAbstractKit {
        return object : HycanAbstractKit() {
            override val name: Int
                get() {
                    var id = -1
                    val environment = HttpUrlConstant.getAPPURL()
                    if (TextUtils.isEmpty(environment)) {
                        id = R.string.switch_environment
                    } else {
                        id = when (EnvironmentManager.getCurrentPosition()) {
                            1 -> {
                                R.string.app_test
                            }
                            2 -> {
                                R.string.app_pre
                            }
                            0 -> {
                                R.string.app_dev
                            }
                            3 -> {
                                R.string.app_prd
                            }
                            else -> {
                                R.string.switch_environment
                            }
                        }
                    }
                    return id
                }


            override fun onClickWithReturn(activity: Activity): Boolean {
                var newInstance = SwitchEnvironmentDialog.newInstance(activity as AppCompatActivity)
                newInstance.showDialog()
//                DialogUtil.showLoadingDialog(activity as AppCompatActivity?)
                return true
            }


        }
    }
}