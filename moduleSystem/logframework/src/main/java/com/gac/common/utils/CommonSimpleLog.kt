package com.gac.common.utils

import com.gacnio.toolkit.xlog.XLog
import com.tencent.mars.xlog.Log

/**
 * @className FlySimpleLog
 * @description TODO
 * @author heyufei
 * @since 2021/6/17 2:30 下午
 * @version 2.0 增加label标签
 */

object CommonSimpleLog {

    private val openDebug = true
    private const val defaultName = "FlyLog"

    private val listeners = HashSet<LogListener>()

    @JvmStatic
    val LABEL_CRASH = LABEL.CRASH

    @JvmStatic
    val LABEL_GLOBAL = LABEL.GLOBAL

    @JvmStatic
    val LABEL_DROP = LABEL.DROP

    @JvmStatic
    val LABEL_SERVIC_ERROR = LABEL.SERVIC_ERROR

    @JvmStatic
    val LABEL_SYSTEME_RROR = LABEL.SYSTEME_RROR

    @JvmStatic
    val LABEL_BUSINESS = LABEL.BUSINESS

    @JvmStatic
    val LABEL_PAGE = LABEL.PAGE

    @JvmStatic
    val LABEL_NETWORK = LABEL.NETWORK

    @JvmStatic
    val LABEL_REACT_NATIVE = LABEL.REACT_NATIVE

    @JvmStatic
    val LABEL_WEB = LABEL.WEB

    @JvmStatic
    val LABEL_INGEEK = LABEL.INGEEK

    @JvmStatic
    val LABEL_MEGA = LABEL.MEGA


    @JvmStatic
    val LABEL_YF = LABEL.YF


    @JvmStatic
    val LABEL_PUSH = LABEL.PUSH

    @JvmStatic
    val LABEL_UPLOAD_BUGLY = LABEL.UPLOAD_BUGLY

    @JvmStatic
    fun d(
        tag: String,
        msg: String?,
        userName: String = defaultName,
        vararg labels: LABEL
    ) {
        if (openDebug && XLog.isInit) {
            val defLabel = defaultLabel(labels)
            Log.d(tag, "${defLabel ?: labels.map { it.value }}[$userName]:$msg")

            listeners.forEach {
                it.log_d(tag, msg, userName, labels)
            }
        }
    }

    @JvmStatic
    fun i(tag: String, msg: String?, userName: String = defaultName, vararg labels: LABEL) {
        if (openDebug && XLog.isInit) {
            val defLabel = defaultLabel(labels)
            Log.i(tag, "${defLabel ?: labels.map { it.value }}[$userName]:$msg")
            listeners.forEach {
                it.log_i(tag, msg, userName, labels)
            }
        }
    }

    @JvmStatic
    fun w(tag: String, msg: String?, userName: String = defaultName, vararg labels: LABEL) {
        if (openDebug && XLog.isInit) {
            val defLabel = defaultLabel(labels)
            Log.w(tag, "${defLabel ?: labels.map { it.value }}[$userName]:$msg")
            listeners.forEach {
                it.log_w(tag, msg, userName, labels)
            }
        }
    }

    @JvmStatic
    fun e(tag: String, msg: String?, userName: String = defaultName, vararg labels: LABEL) {
        if (openDebug && XLog.isInit) {
            val defLabel = defaultLabel(labels)
            Log.e(tag, "${defLabel ?: labels.map { it.value }}[$userName]:$msg")
            listeners.forEach {
                it.log_e(tag, msg, userName, labels)
            }
        }
    }

    @JvmStatic
    fun ke(
        tag: String,
        msg: String?,
        userName: String = defaultName,
        th: Throwable? = null,
        vararg labels: LABEL,
    ) {
        if (openDebug && XLog.isInit) {
            val defLabel = defaultLabel(labels)

            if (th != null) {
                val errorMsg = android.util.Log.getStackTraceString(th)
                Log.e(tag, "${defLabel ?: labels.map { it.value }}[$userName]:$msg,$errorMsg")
                listeners.forEach {
                    it.log_e(tag, "$msg:$errorMsg", userName, labels)
                }
            } else {
                Log.e(tag, "${defLabel ?: labels.map { it.value }}[$userName]:$msg")
                listeners.forEach {
                    it.log_e(tag, msg, userName, labels)
                }
            }
        }
    }

    @JvmStatic
    fun v(tag: String, msg: String?, userName: String = defaultName, vararg labels: LABEL) {
        if (openDebug && XLog.isInit) {
            val defLabel = defaultLabel(labels)
            Log.v(tag, "${defLabel ?: labels.map { it.value }}[$userName]:$msg")
            listeners.forEach {
                it.log_v(tag, msg, userName, labels)
            }
        }
    }

    @JvmStatic
    fun a(tag: String, msg: String?, userName: String = defaultName, vararg labels: LABEL) {
        if (openDebug && XLog.isInit) {
            val defLabel = defaultLabel(labels)
            Log.f(tag, "${defLabel ?: labels.map { it.value }.toString()}[$userName]:$msg")
            listeners.forEach {
                it.log_a(tag, msg, userName, labels)
            }
        }
    }

    @JvmStatic
    @Synchronized
    fun addListener(listener: LogListener) {
        listeners.add(listener)
    }

    @JvmStatic
    @Synchronized
    fun removeListener(listener: LogListener) {
        listeners.remove(listener)
    }

    private fun defaultLabel(labels: Array<out LABEL>): String? {
        val defLabel = if (labels.isNullOrEmpty()) {
            "[$LABEL_BUSINESS]"
        } else {
            null
        }
        return defLabel
    }

    sealed class LABEL(val value: String) {

        // 闪退、崩溃
        object CRASH : LABEL("crash")

        //全局变量切换、例如环境切换、黑夜模式切换、关闭推送等
        object GLOBAL : LABEL("global")

        //掉帧卡顿
        object DROP : LABEL("drop")

        //网络异常 catch捕捉到的
        object SERVIC_ERROR : LABEL("servicError")

        //系统异常 catch捕捉到的
        object SYSTEME_RROR : LABEL("systemError")

        //业务日志
        object BUSINESS : LABEL("business")

        //页面路径
        object PAGE : LABEL("page")

        //网络请求 网络环境变化
        object NETWORK : LABEL("network")

        //rn容器打印
        object REACT_NATIVE : LABEL("reactNative")

        //web容器打印
        object WEB : LABEL("web")

        //mega的sdk所打印的日志信息
        object MEGA : LABEL("mega")

        //推送
        object PUSH : LABEL("push")

        //远峰
        object YF : LABEL("digitalKey")

        //银基sdk所打印的日志信息
        object INGEEK : LABEL("ingeek")

        //上传bugly
        object UPLOAD_BUGLY : LABEL("upload_bugly")


        override fun toString(): String {
            return value
        }
    }
}