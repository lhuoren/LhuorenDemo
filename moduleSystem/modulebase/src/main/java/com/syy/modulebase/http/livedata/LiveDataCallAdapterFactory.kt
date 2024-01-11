package com.gac.common.http.livedata

import androidx.lifecycle.LiveData
import com.syy.modulebase.http.NetResult
import com.syy.modulebase.http.livedata.LiveDataCallAdapter
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @className LiveDataCallAdapterFactory
 * @description TODO
 * @author heyufei
 * @since 2021/7/19 1:45 下午
 * @version 1.0
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    private val TAG = "LiveDataCallAdapterFactory"
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): LiveDataCallAdapter<*>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        if (rawObservableType != NetResult::class.java) {
            throw IllegalArgumentException("type must be a resource")
        }

        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }

        val bodyType = getParameterUpperBound(
            1,
            observableType
        )

        return LiveDataCallAdapter<Any>(bodyType)
    }
}