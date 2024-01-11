package com.syy.modulebase.http.livedata

import androidx.lifecycle.LiveData
import com.syy.modulebase.http.NetResult
import com.syy.modulebase.http.bean.HttpResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @className HttpLiveData
 * @description TODO
 * @author heyufei
 * @since 2021/8/16 2:10 下午
 * @version 1.0
 */
class HttpLiveData<T : Any> : LiveData<NetResult<T, HttpResult<T>>>() {
    private val atomBoolean = AtomicBoolean(false)
    private var lastCallBack: Call<HttpResult<T?>>? = null
    override fun onActive() {
        execuOnce()
    }

    private fun execuOnce() {
        while (atomBoolean.compareAndSet(false, true)) {
            lastCallBack?.enqueue(object : Callback<HttpResult<T?>> {
                override fun onResponse(
                    call: Call<HttpResult<T?>>,
                    response: Response<HttpResult<T?>>
                ) {
                    postValue(NetResult.create(response))
                }

                override fun onFailure(call: Call<HttpResult<T?>>, throwable: Throwable) {
                    postValue(NetResult.create(throwable))
                }
            })
        }
    }

    fun actCallBack(call: Call<HttpResult<T?>>) {
        atomBoolean.set(false)
        lastCallBack = call
        execuOnce()
    }
}