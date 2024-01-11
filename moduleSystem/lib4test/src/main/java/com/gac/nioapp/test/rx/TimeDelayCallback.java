package com.gac.nioapp.test.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * description:
 *
 * @author xyz
 * @email 826363590@qq.com
 * created on: 2019-06-27 11:23
 */
public class TimeDelayCallback implements Observer {

    private  Disposable mDisposable;
    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(Object o) {
        onDelayTimeCome();
        onCountDownStep(o);
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }

    public void onDelayTimeCome() {
    }

    public void onCountDownStep(Object o) {
    }

    /**
     * 取消订阅
     */
    public void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            //Log.e("xyz","====Rx定时器取消======");
        }
    }
}
