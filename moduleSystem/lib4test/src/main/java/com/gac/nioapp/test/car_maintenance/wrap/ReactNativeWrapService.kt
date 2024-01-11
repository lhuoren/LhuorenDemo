package com.gac.nioapp.test.car_maintenance.wrap

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gac.nioapp.test.car_maintenance.IReactNativeService
import com.gac.nioapp.test.enums.RnMessageEnum
import com.gac.nioapp.test.enums.ScreenEnum
import com.syy.modulebase.manager.ConstantsPath

/**
 * @className ReactNativeWrapService
 * @description TODO
 * @author heyufei
 * @since 5/11/21 3:34 PM
 * @version 1.0
 */
class ReactNativeWrapService private constructor() : IReactNativeService {

    @Autowired(name = ConstantsPath.PLATFORM_RN_PATH)
    protected lateinit var serviceIProvider: IReactNativeProviderService
    private val TAG = "ReactNativeWrapService"

    init {
        ARouter.getInstance().inject(this)
    }

    companion object {

        @JvmStatic
        val instance = Singleton.holder

        object Singleton {
            val holder = ReactNativeWrapService()
        }
    }

    override fun startTranRnActivity(
        type: Int,
        map: Map<String, Any>?,
        jumpAfterFinish: Boolean
    ) {
        if (::serviceIProvider.isInitialized) {
            serviceIProvider.startTranRnActivity(type, map, jumpAfterFinish)
        }
    }


    override fun startRnActivity(
        type: Int,
        useStack: Boolean,
        map: Map<String, Any>?,
        jumpAfterFinish: Boolean
    ) {
        if (::serviceIProvider.isInitialized) {
            serviceIProvider.startRnActivity(type, useStack, map, jumpAfterFinish)
        }
    }


    override fun startRnActivityForResult(
        type: Int,
        useStack: Boolean,
        map: Map<String, Any>?,
        jumpAfterFinish: Boolean,
        requestCode: Int,
    ) {
        if (::serviceIProvider.isInitialized) {
            serviceIProvider.startRnActivityForResult(
                type,
                useStack,
                map,
                jumpAfterFinish,
                requestCode
            )
        }
    }

    /**
     * command:消息标识，msg:消息体
     */
    override fun sendEvent(command: RnMessageEnum, msg: Map<String, Any>?) {
        if (::serviceIProvider.isInitialized) {
            serviceIProvider.sendEvent(command, msg)
        }
    }

    override fun createRNFragment(
        screenEnum: ScreenEnum,
        useStack: Boolean,
        params: Map<String, Any>?
    ): Fragment {
        if (::serviceIProvider.isInitialized) {
            return serviceIProvider.createRNFragment(screenEnum, useStack, params)
        }
        throw RuntimeException("serviceIProvider is not Initialized")
    }


}