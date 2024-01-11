package com.syy.modulebase.http.livedata

/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import androidx.lifecycle.LiveData
import com.syy.modulebase.http.NetResult
import com.syy.modulebase.http.bean.HttpResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean


class LiveDataCallAdapter<T : Any>(private val responseType: Type) :
    CallAdapter<HttpResult<T?>, LiveData<NetResult<T, HttpResult<T>>>> {

    private val TAG = "LiveDataCallAdapter"
    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<HttpResult<T?>>): LiveData<NetResult<T, HttpResult<T>>> {
        return object : LiveData<NetResult<T, HttpResult<T>>>() {
            var started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<HttpResult<T?>> {
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
        }
    }
}