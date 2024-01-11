package com.syy.modulebase.http.livedata

import android.util.Log
import com.gac.common.http.livedata.LiveDataCallAdapterFactory
import com.syy.modulebase.http.HttpServiceGenerator
import com.syy.modulebase.http.constants.HttpUrlConstant
import com.syy.modulebase.http.convert.GsonConverterFactory
import retrofit2.Retrofit

/**
 * @className LiveDataRetrofitManager
 * @description TODO
 * @author heyufei
 * @since 2021/7/19 12:03 下午
 * @version 1.0
 */
object LiveDataRetrofitManager {
    private val retrofit: Retrofit by lazy {

        val baseApiHost = HttpUrlConstant.getAPPURL()

        Log.e("APPURL","APPURL:$baseApiHost")
        Retrofit.Builder()
            .client(HttpServiceGenerator.create().okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
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

}
