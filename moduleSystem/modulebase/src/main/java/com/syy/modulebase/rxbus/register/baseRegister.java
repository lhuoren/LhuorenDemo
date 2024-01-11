package com.syy.modulebase.rxbus.register;

import com.syy.modulebase.utils.LogUtils;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class baseRegister {

    private final ConcurrentHashMap<String, Disposable> concurrentHashMap = new ConcurrentHashMap<>();
    private CompositeDisposable mCompositeDisposable;

    protected void addDisposable(String type, Disposable d) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);

        concurrentHashMap.put(type, d);

    }

    protected void unDisposableWithType(String type) {
//        if (mCompositeDisposable !=null && concurrentHashMap.get(type) != null && !concurrentHashMap.get(type).isDisposed()) {
//            concurrentHashMap.get(type).dispose();
//            mCompositeDisposable.remove(concurrentHashMap.get(type));
//            concurrentHashMap.remove(type);
//        }

        if (!concurrentHashMap.containsKey(type)) {
            return;
        }
        try {
            if (concurrentHashMap.get(type) != null && !concurrentHashMap.get(type).isDisposed()) {
                Objects.requireNonNull(concurrentHashMap.get(type)).dispose();
                mCompositeDisposable.remove(Objects.requireNonNull(concurrentHashMap.get(type)));
                concurrentHashMap.remove(type);
            }
        } catch (Exception e) {
            LogUtils.e("baseRegister-unDisposableWithType", e.getMessage());
        }
    }

    public void unDisposable() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }
}
