package com.syy.modulebase.manager.wrap

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.syy.modulebase.manager.ConstantsPath

/**
 * @className LoginWrapService
 * @description TODO
 * @author heyufei
 * @since 2021/11/18 2:34 下午
 * @version 1.0
 */
class LoginWrapService private constructor() : ILoginService {

    @Autowired(name = ConstantsPath.LOGIN_PATH)
    protected lateinit var serviceIProvider: ILoginProviderService

    init {
        ARouter.getInstance().inject(this)
    }

    override fun goLand4Dialog(
        detailTimes: Long,
        callBack: ILoginService.LandCallBack?
    ) {
        serviceIProvider.goLand4Dialog(detailTimes, callBack)
    }

    override fun goLand4Activity(callback: ILoginService.LandCallBack?) {
        serviceIProvider.goLand4Activity(callback)
    }

    override fun logOut() {
        serviceIProvider.logOut()
    }

    override fun dismissKey2Land() {
        serviceIProvider.dismissKey2Land()
    }

    companion object {
        @JvmStatic
        val instance by lazy {
            LoginWrapService()
        }
    }
}