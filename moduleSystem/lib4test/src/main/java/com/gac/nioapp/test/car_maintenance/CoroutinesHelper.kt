package com.gac.nioapp.test.car_maintenance

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @className CoroutinesHelper
 * @description TODO
 * @author heyufei
 * @since 2022/6/24 18:17
 * @version 1.0
 */

fun <T> coroutines2Java(func: suspend () -> Flow<T>, callback: Coroutines2JavaCallback<T>) {
    MainScope().launch {
        func().catch {
            callback.result(null)
        }.collect { item ->
            callback.result(item)
        }
    }
}

interface Coroutines2JavaCallback<T> {
    fun result(data: T?)
}

