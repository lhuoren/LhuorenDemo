package com.syy.modulebase.manager.helper

import com.syy.modulebase.manager.wrap.ILoginService
import java.lang.ref.WeakReference

/**
 * @className LoginListenerHelper
 * @description TODO
 * @author heyufei
 * @since 2021/11/18 3:21 下午
 * @version 1.0
 */
object LoginListenerHelper {
    private var callbackCache: WeakReference<ILoginService.LandCallBack>? = null


    fun getCallBack(): ILoginService.LandCallBack? = callbackCache?.get()

    fun updateCallBack(callback: ILoginService.LandCallBack?) {
        callbackCache = if (callback == null) {
            null
        } else {
            WeakReference(callback)
        }
    }

}