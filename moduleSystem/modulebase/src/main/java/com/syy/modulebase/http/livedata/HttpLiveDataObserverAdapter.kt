package com.syy.modulebase.http.livedata

import androidx.lifecycle.LiveData
import com.syy.modulebase.http.NetResult
import com.syy.modulebase.http.bean.HttpResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @className HttpLiveDataObserverAdapter
 * @description
 *
 * 网络请求Observer 适配器 LiveData
 * 兼容RxJava
 *
 * @author heyufei"
 * @since 2021/11/30 4:11 下午
 * @version 1.0
 */
class HttpLiveDataObserverAdapter<D : Any> :
    BaseLiveDataObserverAdapter<NetResult<D, HttpResult<D>>, HttpResult<D>>() {

    override fun transformO2T(o: HttpResult<D>?): NetResult<D, HttpResult<D>>? {
        if (o == null) {
            return null
        }
        return NetResult.createNotNull(o)
    }

    override fun getErrorData(e: Throwable): NetResult<D, HttpResult<D>>? {
        return NetResult.create(e)
    }


}


/**
 * Observer ---> 转 LiveData
 * 注意 D需要非空，旧接口为null时需改为非null（去掉？）
 */
fun <D : Any> Observable<HttpResult<D>>.transFormHttpLiveData(): LiveData<NetResult<D, HttpResult<D>>> {
    val result = HttpLiveDataObserverAdapter<D>()
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result)
    return result
}