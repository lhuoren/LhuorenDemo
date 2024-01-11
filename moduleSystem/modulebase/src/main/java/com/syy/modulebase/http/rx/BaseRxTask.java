package com.syy.modulebase.http.rx;

import io.reactivex.disposables.Disposable;

/**
 * author:     xyz
 * date:       2018/5/4
 * email:
 */
public interface BaseRxTask<T> {

    void onStart(Disposable disposable);

    void onSuccess(T t);

    void onFailed(Throwable e);

    void onComplete();

    T doWork() throws Exception;

}
