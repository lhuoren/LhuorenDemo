package com.syy.modulebase.http.interceptor

import cn.gacnio.it.database.daohelper.CommonEntityDaoHelper
import com.gac.common.utils.CommonSimpleLog
import com.google.gson.Gson
import com.syy.modulebase.http.HttpServiceGenerator
import com.syy.modulebase.http.bean.HttpResult
import com.syy.modulebase.http.constants.HttpKeyConstant
import com.syy.modulebase.utils.AppInfoManager
import com.syy.modulebase.utils.NetWorkUtil
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

/**
 *
 * fix : retrofit2.KotlinExtensions$await$2$2.onResponse(KotlinExtensions.kt:53)
 */
class ErrorHandleInterceptor : Interceptor {
    private val gson = Gson()

    private val TAG = "ErrorHandleInterceptor"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var params = ""
        if ("POST" == request.method()) {
            val requestBody = request.body()
            if (requestBody != null) {
                val buffer = Buffer()
                requestBody.writeTo(buffer)
                params = buffer.readUtf8()
            }
        }
        val key: String = request.url().toString() + request.method() + params

        try {
            // 服务器处理异常
            val res = chain.proceed(request)
            if (!res.isSuccessful) {
                throw RuntimeException("server:${res.code()}, ${res.message()}")
            }
            return res
        } catch (e: Exception) {
            e.printStackTrace()
            val needEffectCache =
                request.header(HttpKeyConstant.EFFECTIVE_CACHE) != null && NetWorkUtil.isConnected(
                    AppInfoManager.getInstance().context
                )

            if (needEffectCache) {
                val cache = CommonEntityDaoHelper.get().getCommonData(
                    key, "http_cache",
                    HttpServiceGenerator.HttpCacheBean::class.java
                )
                if (cache != null && System.currentTimeMillis() - cache.createTime <= HttpKeyConstant.HTTP_CACHE_TIME_MILLIS) {
                    CommonSimpleLog.i(
                        "$TAG-HttpServiceGenerator",
                        "使用有效缓存数据04: $key",
                        "flyme"
                    )
                    return Response.Builder()
                        .request(request)
                        .code(200)
                        .header("NO-CACHE", "2233")
                        .message("")
                        .protocol(Protocol.HTTP_1_1)
                        .body(
                            ResponseBody.create(
                                MediaType.parse("application/json;charset=UTF-8"),
                                cache.response
                            )
                        )
                        .build()
                }
            }
            try {
                CommonSimpleLog.e(
                    TAG,
                    "netError: ${chain.request().url()},${e.message},$needEffectCache",
                    "flyme",
                    CommonSimpleLog.LABEL_NETWORK
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            val httpResult = HttpResult<String>().apply {
                code = 900  // 901， 902
                data = null
                msg = e.message ?: "client internal error"
            }
            val body = ResponseBody.create(null, gson.toJson(httpResult))
            return Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .message(httpResult.msg)
                .body(body)
                .code(200)
                .build()

        }
    }
}