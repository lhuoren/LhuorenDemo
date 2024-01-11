package com.gacnio.toolkit.public_component

import android.app.Application

/**
 * @className ModuleInitService
 * @description 组件需要全局初始化时可以实现该接口，并使用 @AutoService 注解标注
 * 借助AutoService实现控制反转
 * @author heyufei
 * @since 5/15/21 3:29 PM
 * @version 1.0
 */
interface IComponentInitializationService {

    /**
     * 注意：多进程时会触发多次
     * isMainProcess:当前调用是否发生在主进程
     * processName:当前调用进程名
     */
    fun init(application: Application, isMainProcess: Boolean, processName: String)
}