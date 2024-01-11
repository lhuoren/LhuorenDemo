package com.syy.modulebase.http.interceptor

import android.text.TextUtils
import androidx.multidex.BuildConfig
import com.syy.modulebase.utils.AppConstant
import com.syy.modulebase.utils.GateWaySignUtil
import com.syy.modulebase.utils.StringUtils
import com.syy.modulebase.utils.TimeAsyncManager
import com.tencent.mars.xlog.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset
import java.util.*


/**
 * Create by xiaojiang on 12/31/20
 */

class SignatureInterceptor : Interceptor {

    companion object {
        const val TAG = "SignatureInterceptor"
        const val JSONBODY = "jsonBody"
        private val UTF8 = Charset.forName("UTF-8")

        private val requestAppIdName = "signAppId"
        private var requestAppId = "gacnio-android"
        private val requestSecurityName = "signAppSecurity"
        private var requestSecurity = "android*&^%G"
        private var appRequestId = "appRequestId"
        private val requestTime = "appTimestamp"
        private var sign = "paramSign"

        fun signature(request: Request): Request {
            val url = request.url()

            val uuid = UUID.randomUUID().toString()
            val timestamp = TimeAsyncManager.getCurrentTimeMillis() / 1000

            val signatureStr = url.queryParameterNames()
                .sorted()
                .joinToString {
                    "$it=${url.queryParameter(it) ?: ""}"
                }

            if (BuildConfig.DEBUG) {
                Log.d("SignatureInterceptor", signatureStr)
            }

            var charset = UTF8
            val requestBody = request.body()
            var bodyContent = ""
            requestBody?.let {
                val buffer = Buffer()
                requestBody.writeTo(buffer)
                val contentType = requestBody.contentType()
                if (contentType != null) {
                    charset = contentType.charset(UTF8)
                }
                if (contentType != null && contentType.toString().contains("application/json")) {
                    bodyContent = buffer.readString(charset)
                }
            }
            requestSecurity = AppConstant.SIGN_SECURITY
            requestAppId = AppConstant.SIGN_ID
            val signature =
                createSign(request.method(), bodyContent, url, uuid, timestamp.toString())
            return request.newBuilder()
                .header(requestAppIdName, requestAppId)
                .header(requestSecurityName, requestSecurity)
                .header(appRequestId, uuid)
                .header(requestTime, timestamp.toString())
                .header(sign, signature)
                .build()
        }

        private fun createSign(
            methodName: String,
            bodyContent: String,
            url: HttpUrl,
            uuid: String,
            timestamp: String
        ): String {
            val list = mutableListOf<GateWaySignUtil.KeyValueEntity>()
            val keys = url.queryParameterNames()
            keys.forEach {
                list.add(GateWaySignUtil.KeyValueEntity(it, url.queryParameter(it)))
            }

            list.add(GateWaySignUtil.KeyValueEntity(requestAppIdName, requestAppId))
            list.add(GateWaySignUtil.KeyValueEntity(appRequestId, uuid))
            list.add(GateWaySignUtil.KeyValueEntity(requestTime, timestamp))
            if (!TextUtils.isEmpty(bodyContent)) {
                list.add(GateWaySignUtil.KeyValueEntity(JSONBODY, bodyContent))
            }
            val sign = signURLAndRequestParams(methodName, url.encodedPath(), list, requestSecurity)
            return sign
        }

        private fun signURLAndRequestParams(
            method: String?, path: String?, params: MutableList<GateWaySignUtil.KeyValueEntity>?,
            appSecret: String?
        ): String {
            val sign = StringBuilder().append(method).append(path)

            val paramString = linkSortedNoneEmptyArgs(params)

            if (!StringUtils.isEmpty(paramString)) {
                sign.append("?").append(paramString).append(appSecret)
            } else {
                sign.append("?").append(appSecret)
            }

            val signMd5 = GateWaySignUtil.md5(sign.toString())
            Log.v(TAG, "网关调用签名,sign:{${sign.toString()}},md5:{${signMd5}}")
            return signMd5
        }

        private fun linkSortedNoneEmptyArgs(params: MutableList<GateWaySignUtil.KeyValueEntity>?): String {
            if (null == params || params.isEmpty()) {
                Log.e(TAG, "没有参数")
                return ""
            }

            params.sortWith(object : Comparator<GateWaySignUtil.KeyValueEntity> {
                override fun compare(a: GateWaySignUtil.KeyValueEntity, b: GateWaySignUtil.KeyValueEntity): Int {
                    if (a.name == b.name) {
                        return a.value.compareTo(b.value)
                    }
                    return a.name.compareTo(b.name)
                }
            })

            val sb = java.lang.StringBuilder()
            params.forEach {
                sb.append(it.name).append("=").append(it.value).append("&")
            }
            if (sb.isNotEmpty()) {
                sb.deleteCharAt(sb.length - 1)
            }
            return sb.toString()
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(signature(request))
    }
}