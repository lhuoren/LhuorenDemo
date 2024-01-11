package com.syy.modulebase.http

import androidx.multidex.BuildConfig
import com.gac.common.utils.CommonSimpleLog
import com.syy.modulebase.BaseApplication
import com.syy.modulebase.http.bean.HttpResult
import com.syy.modulebase.utils.NetWorkUtil
import retrofit2.Response
import java.lang.Exception

/**
 * @className NetResult
 * @description
 * 网络请求结果集的封装
 * @author heyufei
 * @since 2021/7/17 5:01 下午
 * @version 1.0
 */


sealed class NetResult<D, T : HttpResult<D>> {
    companion object {
        private val TAG = "NetResult"

        fun <D : Any> create(error: Throwable): NetFail<D> {
            return NetFail(-1024, error.message ?: "unknown error(24)!")
        }

        fun <D : Any> create(response: Response<HttpResult<D?>>): NetResult<D, HttpResult<D>> {
            return if (response.isSuccessful) {
                return create(response.body())
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                if (!NetWorkUtil.isAvailable(BaseApplication.getInstance())) {
                    NetFail(-1028, "当前网络不可用", null)
                } else {
                    if (BuildConfig.DEBUG) {
                        NetFail(-1026, errorMsg ?: "unknown error(26)!")
                    } else {
                        NetFail(-1026, "网络异常请稍后再试(26)")
                    }
                }

            }
        }


        fun <D : Any> create(result: HttpResult<D?>?): NetResult<D, HttpResult<D>> {
            return try {
                if (result == null) {
                    CommonSimpleLog.e(
                        TAG,
                        "create: body is empty",
                        "flyme",
                        CommonSimpleLog.LABEL_NETWORK
                    )
                    NetFail(-1025, "body is empty!")
                } else {
                    if (result.code != 0) {
                        CommonSimpleLog.e(
                            TAG,
                            "error: $result",
                            "flyme",
                            CommonSimpleLog.LABEL_NETWORK
                        )
                        NetFail(result.code, result.msg?:"", result.data)
                    } else {
                        NetSuccesses(result.data, result.code)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                CommonSimpleLog.e(
                    "NetResult",
                    "create: ${e.message}",
                    "flyme",
                    CommonSimpleLog.LABEL_NETWORK
                )
                NetFail(-1027, "unknown error(27)!")
            }
        }

        /**
         * HttpResult<D!!>
         */
        fun <D : Any> createNotNull(result: HttpResult<D>?): NetResult<D, HttpResult<D>> {
            return try {
                if (result == null) {
                    CommonSimpleLog.e(
                        TAG,
                        "create: body is empty",
                        "flyme",
                        CommonSimpleLog.LABEL_NETWORK
                    )
                    NetFail(-1025, "body is empty!")
                } else {
                    if (result.code != 0) {
                        CommonSimpleLog.e(
                            TAG,
                            "error: $result",
                            "flyme",
                            CommonSimpleLog.LABEL_NETWORK
                        )
                        NetFail(result.code, result.msg, result.data)
                    } else {
                        NetSuccesses(result.data, result.code)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                CommonSimpleLog.e(
                    "NetResult",
                    "create: ${e.message}",
                    "flyme",
                    CommonSimpleLog.LABEL_NETWORK
                )
                NetFail(-1027, "unknown error(27)!")
            }
        }
    }
}

data class NetSuccesses<D : Any>(val data: D?, val code: Int = 0) : NetResult<D, HttpResult<D>>()
data class NetFail<D : Any>(val errorCode: Int, val errorMsg: String = "", val data: D? = null) :
    NetResult<D, HttpResult<D>>()



