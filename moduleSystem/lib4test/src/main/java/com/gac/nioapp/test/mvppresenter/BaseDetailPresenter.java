package com.gac.nioapp.test.mvppresenter;

import com.gac.nioapp.test.mvpview.BaseDetailView;
import com.gac.nioapp.test.observer.HttpResultObserver;
import com.tencent.mars.xlog.Log;

import io.reactivex.Observable;

/**
 * Created by Sai on 2018/3/15.
 */
public abstract class BaseDetailPresenter<V extends BaseDetailView> extends BaseDataPresenter<V> {
    Object data;

    public void setData(Object data) {
        this.data = data;
        view.onHttpDataGet(data);
    }

    public Object getData() {
        return data;
    }

    /**
     * 刷新/加载数据
     */
    @Override
    public void onLoadData() {
        setRefreshing(true);
        //这里考虑到首次加载是 loading，以后加载是refresh 模式
        if (!isOnce()) {
            setStatusLoading();
        }
        Observable observable = onLoadDataHttpRequest();
        if (observable != null) {
            onCallHttpRequest(observable, callBack);
        }
    }

    public HttpResultObserver callBack = new HttpResultObserver<Object>() {
        @Override
        public void onHttpSuccess(Object resultData, String msg) {
            if (resultData != null) {
                setData(resultData);
            } else {
                setStatusEmpty(msg);
            }
        }

        @Override
        public void onHttpFail(int code, String msg) {
            Log.d("BaseDetailPresenter", "code= " + code + "  msg:" + msg);
            setStatusError(code, msg);
        }

        @Override
        public void onNetWorkError(String msg) {
            Log.d("BaseDetailPresenter", "msg:" + msg);
            // 这里需要过滤一下这个，不知道为什么会抛出： failed to connect to /139.9.48.179 (port 443) from /10.10.85.223 (port 46446) after 15000ms: isConnected failed: ETIMEDOUT (Connection timed out)
            String customMsg = "failed to connect to";
            if (!msg.startsWith(customMsg)) {
                setStatusNetworkError(msg);
            }
        }

        @Override
        public void onComplete() {
            setOnce(true);
            setRefreshing(false);
            onHttpCompleted();
        }
    };
}
