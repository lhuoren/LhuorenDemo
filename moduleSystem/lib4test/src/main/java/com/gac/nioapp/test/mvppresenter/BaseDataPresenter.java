package com.gac.nioapp.test.mvppresenter;

import com.gac.nioapp.test.mvpview.BaseDataView;
import com.gac.nioapp.test.rx.MainThread;
import com.gac.nioapp.test.rx.RxBus;
import com.gac.nioapp.test.rx.RxJavaUtils;
import com.syy.modulebase.http.bean.HttpResult;
import com.trello.rxlifecycle3.RxLifecycle;
import com.trello.rxlifecycle3.android.ActivityEvent;

import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Sai on 2018/3/15.
 */

public abstract class BaseDataPresenter<V extends BaseDataView> extends BasePresenter<V> {
    protected final BehaviorSubject lifecycleSubject = BehaviorSubject.create();

    //刷新状态
    private boolean refreshing = false;
    //加载中状态
    private boolean statusLoading = false;
    //控制loading状态只有一次,对于列表的loading概念，就是首次加载数据，其余加载是刷新
    private boolean once = false;

    public boolean isOnce() {
        return once;
    }

    public void setOnce(boolean once) {
        this.once = once;
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    public void setRefreshing(boolean refreshing) {
        if (view == null) return;
        this.refreshing = refreshing;
        view.onRefreshing(refreshing);
    }

    public void setStatusEmpty(String msg) {
        view.onHttpEmptySuccess(msg);
    }

    public boolean isStatusLoading() {
        return statusLoading;
    }

    public void setStatusLoading() {
        this.statusLoading = true;
        if (view != null) {
            view.onStatusLoading();
        }
    }

    public void setStatusError(int code, String msg) {
        view.onHttpError(code, msg);
        view.showToast(msg);
    }

    public void setStatusNetworkError(String msg) {
        view.onHttpNetworkError(msg);
        view.showToast(msg);
    }

    public void onHttpCompleted() {
        if (view != null) {
            view.onHttpCompleted();
        }
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }

    /**
     * 网络请求
     *
     * @return
     */
    public abstract <Data> Observable<HttpResult<Data>> onLoadDataHttpRequest();

    public abstract void onLoadData();

    public void onCallHttpRequest(Observable observable, Observer callBack) {
        observable.compose(MainThread.io()).compose(RxLifecycle.bindUntilEvent(this.lifecycleSubject, ActivityEvent.DESTROY)).subscribe(callBack);
    }

    public <T> void toObservable(final Class<T> eventType, Consumer<? super T> onNext) {
        RxBus.getInstance().toObservable(eventType).compose(MainThread.io()).compose(RxLifecycle.bindUntilEvent(this.lifecycleSubject, ActivityEvent.DESTROY)).subscribe(onNext);
    }

    public void post(Object event) {
        RxBus.getInstance().post(event);
    }

    public void onCallObservableDelay(int seconds, final Observer callBack) {
        RxJavaUtils.observableTimeDelay(seconds)
                .compose(RxLifecycle.bindUntilEvent(this.lifecycleSubject, ActivityEvent.DESTROY))
                .subscribe(callBack);
    }

    public void onCallObservableDelay(int time, TimeUnit unit, final Observer callBack) {
        RxJavaUtils.observableTimeDelay(time, unit)
                .compose(RxLifecycle.bindUntilEvent(this.lifecycleSubject, ActivityEvent.DESTROY))
                .subscribe(callBack);
    }

    public void onCallObservableCountdown(int seconds, final Observer callBack) {
        if (seconds <= 0) {
            return;
        }
        RxJavaUtils.observableCountdown(seconds)
                .compose(RxLifecycle.bindUntilEvent(this.lifecycleSubject, ActivityEvent.DESTROY))
                .subscribe(callBack);
    }

}
