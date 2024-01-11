package com.syy.modulebase.http.messge

import androidx.lifecycle.MutableLiveData
import com.syy.modulebase.http.messge.livedata.UnPeekLiveData

/**
 * @className LiveDataBus
 * @description LiveDataBus 事件总线
 * 观察者(LifecycleOwner) 可关联自身生命周期。
 * 参考：https://tech.meituan.com/2018/07/26/android-livedatabus.html
 *
 * 解决默认LiveData粘性问题
 * @author heyufei
 * @since 2021/7/1 9:32 上午
 * @version 1.0
 */
class LiveDataBus private constructor() {

    private var bus_un_peek: MutableMap<String, UnPeekLiveData<Any>>? = null
    private var bus_defaut: MutableMap<String, MutableLiveData<Any>>? = null
    private val obj = Any()

    init {
        bus_un_peek = HashMap()
        bus_defaut = HashMap()
    }

    private object SingletonHolder {
        val DATA_BUS = LiveDataBus()
    }

    companion object {
        @JvmStatic
        fun instance(): LiveDataBus {
            return SingletonHolder.DATA_BUS
        }
    }

    /**
     * type：期望的类型
     * 非粘性,数据不会倒灌
     */
    fun <T> withUnPeek(key: String, type: Class<T>): UnPeekLiveData<T> {
        synchronized(obj) {
            if (!bus_un_peek!!.containsKey(key)) {
                bus_un_peek!![key] =
                    UnPeekLiveData()
            }
        }
        return bus_un_peek!![key] as UnPeekLiveData<T>
    }

    /**
     * type：期望的类型
     * 粘性
     */
    fun <T> with(key: String, type: Class<T>): MutableLiveData<T> {
        synchronized(obj) {
            if (!bus_defaut!!.containsKey(key)) {
                bus_defaut!![key] = MutableLiveData()
            }
        }
        return bus_defaut!![key] as MutableLiveData<T>
    }

}