package com.gac.nioapp.test.helper

import android.accounts.NetworkErrorException
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.multidex.BuildConfig
import com.syy.modulebase.BaseApplication
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @className ExceptionUtil
 * @description TODO
 * @author heyufei
 * @since 2022/7/1 19:47
 * @version 1.0
 */
/**
 * 异常工具类
 * @author ssq
 */
object ExceptionUtil {
    private val UTIL_HANDLER = Handler(Looper.getMainLooper())

    /**
     * 处理异常，toast提示错误信息
     */
    fun catchException(e: Throwable, needToastError: Boolean = BuildConfig.DEBUG) {
        e.printStackTrace()
        if (needToastError) {
            when (e) {
                is HttpException -> {
                    catchHttpException(e.code())
                }
                is SocketTimeoutException -> {
                    showToast("连接超时")
                }
                is UnknownHostException, is NetworkErrorException -> {
                    showToast("请求失败，请稍后再试")
                }
                is InterruptedIOException -> {
                    showToast("服务器连接失败，请稍后重试")
                }
                // 自定义接口异常
                is ApiException -> {
                    showToast(e.message ?: "")
                }
                is ConnectException -> {
                    showToast("连接服务器失败")
                }
                else -> {
                    showToast("未知异常：${e::class.java.name}")
                }
            }
        }
    }

    fun runOnUiThread(runnable: Runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run()
        } else {
            UTIL_HANDLER.post(runnable)
        }
    }

    /**
     * 处理网络异常
     */
    fun catchHttpException(errorCode: Int) {
        if (errorCode in 200 until 300) return// 成功code则不处理
        showToast(
            catchHttpExceptionCode(
                errorCode
            ), errorCode
        )
    }


    /**
     * toast提示
     */
    private fun showToast(errorMsg: String, errorCode: Int = -1) {
        if (errorCode == -1) {
            runOnUiThread {
                Toast.makeText(BaseApplication.getInstance(), errorMsg, Toast.LENGTH_SHORT).show()
            }
        } else {
            runOnUiThread {
                Toast.makeText(
                    BaseApplication.getInstance(),
                    "$errorMsg:$errorCode",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    /**
     * 处理网络异常
     */
    private fun catchHttpExceptionCode(errorCode: Int) = when (errorCode) {
        in 400..600 -> "请求失败，请稍后再试($errorCode)"
//        in 400 until 500 -> "请求失败，请稍后再试"
        else -> "请求失败，请稍后再试"
    }

}
