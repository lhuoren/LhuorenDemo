package com.gac.nioapp.test.mvpview;

/**
 * Created by Sai on 2018/3/15.
 */

public interface BaseDataView extends IBaseView {
    /**
     * 网络请求之前，走这里
     *
     * @param refreshing
     */
    void onRefreshing(boolean refreshing);

    /**
     * {
     * "code": 0,
     * "msg": "保存成功",
     * "data": {}
     * } data 里面没数据返回的，走这里
     *
     * @param msg 服务器返回的tib
     */
    void onHttpEmptySuccess(String msg);

    void onStatusLoading();

    /**
     * 网络正常返回您，但是返回的code！=0 的其他code的时候，走这里
     *
     * @param code
     * @param msg
     */
    void onHttpError(int code, String msg);

    /**
     * 网络异常，走这里，比如500之类
     *
     * @param msg
     */
    void onHttpNetworkError(String msg);

    /**
     * {
     * "code": 0,
     * "msg": "保存成功",
     * "data": {}
     * } data 里面有数据返回的，走这里
     *
     * @param data
     */
    void onHttpDataGet(Object data);

    /**
     * 不管成功与否，都会走这里 onHttpCompleted
     */
    void onHttpCompleted();

    /**
     * 目前 onHttpNetworkError和onHttpError 会自动toast出对应的msg，不用在各自的回调中手动toast
     *
     * @param msg
     */
    void showToast(String msg);
    /**
     * 目前 onHttpNetworkError和onHttpError 会自动toast出对应的msg，不用在各自的回调中手动toast
     *
     * @param msg
     */
    void showToastLong(String msg);
}
