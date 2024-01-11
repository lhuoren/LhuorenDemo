package com.syy.modulebase.http.livedata

import android.os.Looper
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @className LiveDataObserverAdapter
 * @description
 * Observer -> LiveData
 * 异常时默认返回 null
 * @author heyufei
 * @since 2021/11/30 3:38 下午
 * @version 1.0
 */
abstract class BaseLiveDataObserverAdapter<T : Any, O : Any> : Observer<O>, LiveData<T>() {


    private var disposable: Disposable? = null


    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(value: O) {
        updateValue(transformO2T(value))
    }

    final override fun onError(e: Throwable) {
        updateValue(getErrorData(e))
    }

    /**
     * 失败时默认返回null
     */
    protected open fun getErrorData(e: Throwable): T? {
        return null
    }

    final override fun onComplete() {

    }

    override fun removeObserver(observer: androidx.lifecycle.Observer<in T>) {
        super.removeObserver(observer)
        if (true == disposable?.isDisposed) {
            disposable!!.dispose()
        }
    }

    /**
     * 数据更新，尽可能快
     */
    protected fun updateValue(value: T?) {
        if (isMainThread()) {
            setValue(value)
        } else {
            postValue(value)
        }
    }

    /**
     * 数据类型转换 O(Observer) -> T(LiveData)
     */
    abstract fun transformO2T(o: O?): T?

    private fun isMainThread() = Looper.getMainLooper() == Looper.myLooper()


}

/**
 * Observable ---> 转 LiveData
 * 使用该扩展方法可将一般的 Observable 转为LiveData
 */
fun <T : Any, O : Any> Observable<O>.transFormLiveData(adapter: BaseLiveDataObserverAdapter<T, O>): LiveData<T> {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(adapter)
    return adapter
}