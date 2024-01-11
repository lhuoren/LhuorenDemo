package com.syy.modulebase.manager.wrap

/**
 * @className ILoginService
 * @description 登陆模块，定义登陆业务的操作
 * 一键登陆集成
 * @author heyufei
 * @since 2021/11/18 2:19 下午
 * @version 1.0
 */
interface ILoginService {

    /**
     * 登录，弹窗展示
     * detailTimes: ms 支持指定时间后仅弹一次（目前只用在首次安装app）
     * 仅会触发一下，多次调用仅首次会触发
     */
    fun goLand4Dialog(detailTimes: Long, callBack: LandCallBack? = null)

    /**
     * 登录,Activity 方式呈现。
     *
     */
    fun goLand4Activity(callback: LandCallBack? = null)

    /**
     * 注销、退出登录
     */
    fun logOut()

    /**
     * 一键登陆界面关闭，如果有显示的话
     */
    fun dismissKey2Land()

    interface LandCallBack {
        fun landSuccess()
        fun landFail(errorMsg: String, errorCode: Int)
    }
}