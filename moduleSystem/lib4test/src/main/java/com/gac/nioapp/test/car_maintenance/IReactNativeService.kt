package com.gac.nioapp.test.car_maintenance

import androidx.fragment.app.Fragment
import com.gac.nioapp.test.enums.RnMessageEnum
import com.gac.nioapp.test.enums.ScreenEnum

/**
 * @className IReactService
 * @description 定义RN需要对外提供的功能
 * @author heyufei
 * @since 5/10/21 5:41 PM
 * @version 1.0
 */
interface IReactNativeService {


    fun startTranRnActivity(
        type: Int,
        map: Map<String, Any>?,
        jumpAfterFinish: Boolean
    )

    //启动RnActivity
    /**
     * jumpAfterFinish:跳转其它页面后是否finish掉自己
     */
    fun startRnActivity(
        type: Int,
        useStack: Boolean,
        map: Map<String, Any>? = null,
        jumpAfterFinish: Boolean = false
    )

    /**
     * jumpAfterFinish:跳转其它页面后是否finish掉自己
     */
    fun startRnActivityForResult(
        type: Int,
        useStack: Boolean,
        map: Map<String, Any>? = null,
        jumpAfterFinish: Boolean = false,
        requestCode: Int,
    )

    //向Rn发消息
    /**
     * command:消息标识，msg:消息体
     */
    fun sendEvent(command: RnMessageEnum, msg: Map<String, Any>? = null)


    fun createRNFragment(
        screenEnum: ScreenEnum,
        useStack: Boolean = true,//默认使用true，如果页面不需要导航栏在Rn代码定制，如果传递false，那么Rn页面的路由跳转将无法使用
        params: Map<String, Any>? = null,
    ): Fragment
}