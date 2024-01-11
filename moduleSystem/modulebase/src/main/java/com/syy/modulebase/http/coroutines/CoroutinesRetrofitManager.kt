package com.syy.modulebase.http.coroutines

import android.util.Log
import com.syy.modulebase.http.interceptor.ErrorHandleInterceptor
import com.gac.common.http.livedata.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import com.syy.modulebase.http.HttpServiceGenerator
import com.syy.modulebase.http.adapter.NullStringToEmptyAdapterFactory
import com.syy.modulebase.http.constants.HttpUrlConstant
import com.syy.modulebase.http.convert.GsonConverterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * @className LiveDataRetrofitManager
 * @description TODO
 * @author heyufei
 * @since 2021/7/19 12:03 下午
 * @version 1.0
 */
@SuppressWarnings("unchecked")
object CoroutinesRetrofitManager {
    private val baseApiHost = HttpUrlConstant.getAPPURL()
    val gson = GsonBuilder().registerTypeAdapterFactory(NullStringToEmptyAdapterFactory<Any?>())
        .create()
    private val retrofit: Retrofit by lazy {


        Log.e("APPURL","APPURL:$baseApiHost")
        Retrofit.Builder()
            .client(
                HttpServiceGenerator.create().okHttpClient
                    .newBuilder()
                    .addInterceptor(ErrorHandleInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .baseUrl(baseApiHost)
            .build()
    }

    private val serviceCache = HashMap<Any, Any>()

    var mockRetrofit = retrofit


    /**
     * 获取Service
     *
     * @return Service
     */
    fun <T> create(service: Class<T>): T {
        HttpServiceGenerator.create()
        var cache = serviceCache[service]
        return if (cache == null) {
            cache = if (mockRetrofit == retrofit) {
                retrofit.create(service)
            } else {
                mockRetrofit.create(service)
            }
            serviceCache[service] = cache!!
            cache
        } else {
            cache as T
        }
    }

    private val serviceCacheTimeOut = HashMap<Any, Any>()

    fun <T> createWithTimeOut(service: Class<T>, timeOut: Long): T {
        var cache = serviceCacheTimeOut[service]
        return if (cache == null) {
            cache = Retrofit.Builder()
                .client(
                    HttpServiceGenerator.create()
                        .okHttpClient
                        .newBuilder()
                        .readTimeout(
                            timeOut,
                            TimeUnit.MILLISECONDS
                        )
                        .connectTimeout(
                            timeOut,
                            TimeUnit.MILLISECONDS
                        )
                        .writeTimeout(
                            timeOut,
                            TimeUnit.MILLISECONDS
                        )
                        .addInterceptor(ErrorHandleInterceptor())
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseApiHost)
                .build()
                .create(service)
            serviceCacheTimeOut[service] = cache!!
            cache
        } else {
            cache as T
        }
    }

}
