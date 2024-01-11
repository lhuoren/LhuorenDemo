package com.syy.modulebase.rxbus;

import android.annotation.SuppressLint;

import com.syy.modulebase.rxbus.message.MainAcFormCollectionFgRxBusMessage;
import com.syy.modulebase.utils.LogUtils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {

    private HashMap<String, CompositeDisposable> mSubscriptionMap;
    private static volatile RxBus mRxBus;
    private final Subject<Object> mSubject;

//    Single column mode
    public static RxBus getInstanceBus() {
        if (mRxBus == null) {
            synchronized (RxBus.class) {
                if (mRxBus == null) {
                    mRxBus = new RxBus();
                }
            }
            return mRxBus;
        }
        return mRxBus;
    }

//    @SuppressLint("StaticFieldLeak")
//    private static class Holder {
//        private static final RxBus rxBus = new RxBus();
//    }
//
//    public static RxBus getInstanceBus() {
//        return Holder.rxBus;
//    }


    public RxBus() {
        mSubject = PublishSubject.create().toSerialized();
    }

    public void post(Object o) {
        LogUtils.e("mSubject", "mSubject--post:" + o);
        mSubject.onNext(o);
    }

    /**
     * Returns a Flowable instance with backpressure of the specified type
     *
     * @param <T>
     * @param type
     * @return
     */
    public <T> Flowable<T> getObservable(Class<T> type) {
        return mSubject.toFlowable(BackpressureStrategy.BUFFER)
                .ofType(type);
    }

    /**
     * A default subscription method
     *
     * @param <T>
     * @param type
     * @param next
     * @param error
     * @return
     */
    public <T> Disposable doSubscribe(Class<T> type, Consumer<T> next, Consumer<Throwable> error) {
        return getObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .serialize()
                .subscribe(next, error);
    }

    /**
     * Is there an observer subscription?
     *
     * @return
     */
    public boolean hasObservers() {
        return mSubject.hasObservers();
    }

    /**
     * disposable after saving subscription
     *
     * @param o
     * @param disposable
     */
    public void addSubscription(Object o, Disposable disposable) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        String key = o.getClass().getName();

        if (mSubscriptionMap.get(key) != null) {
            try {
                mSubscriptionMap.get(key).add(disposable);
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            //One-time containers can hold multiple containers and provide additions and removals.
            CompositeDisposable disposables = new CompositeDisposable();
            disposables.add(disposable);
            mSubscriptionMap.put(key, disposables);
        }
    }

    /**
     * unsubscribe
     *
     * @param o
     */
    public void unSubscribe(Object o) {
        if (mSubscriptionMap == null) {
            return;
        }

        String key = o.getClass().getName();
        if (!mSubscriptionMap.containsKey(key)) {
            return;
        }
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).dispose();
        }

        mSubscriptionMap.remove(key);
    }

    //register
    public <T> void registerRxBus(Class<T> eventType, Consumer<T> action) {

        synchronized (this) {

            LogUtils.e("registerRxBus", "registerRxBusType:"+eventType);

            Disposable disposable = getInstanceBus().doSubscribe(eventType, action, new Consumer<Throwable>() {
                @Override
                public void accept(@NonNull Throwable throwable) throws Exception {
                    LogUtils.e("NewsMainPresenter", throwable.toString());
                }
            });

            getInstanceBus().addSubscription(eventType, disposable);
        }

    }

    public <T> void unregisterRxBus(Class<T> eventType) {
        getInstanceBus().unSubscribe(eventType);
    }

}
