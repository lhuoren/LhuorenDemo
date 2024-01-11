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
import com.gac.common.utils.CommonSimpleLog
import com.syy.modulebase.http.bean.HttpResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * 新增，相同请求多次调用返回一个LiveData数据流
 */
class LiveDataCallAdapter2<T : Any>(private val responseType: Type) :
    CallAdapter<HttpResult<T?>, LiveData<NetResult<T, HttpResult<T>>>> {

    private val httpResult = HttpLiveData<T>()

    private val TAG = "LiveDataCallAdapter"
    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<HttpResult<T?>>): LiveData<NetResult<T, HttpResult<T>>> {
        httpResult.actCallBack(call)
        CommonSimpleLog.d(TAG, "adapt: $responseType----->$httpResult", "flyme")
        return httpResult
    }
}