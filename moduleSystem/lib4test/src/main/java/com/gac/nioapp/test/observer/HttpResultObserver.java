package com.gac.nioapp.test.observer;

import android.accounts.NetworkErrorException;

import com.syy.modulebase.http.bean.HttpResult;
import com.syy.modulebase.http.constants.HttpCodeConstant;
import com.syy.modulebase.http.constants.HttpStatusConstants;
import com.tencent.mars.xlog.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import retrofit2.HttpException;

/**
 * 网络结果预处理
 * Created by Sai on 2018/3/15.
 */
public class HttpResultObserver<T> implements Observer<HttpResult<T>> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(HttpResult<T> result) {
        if (result == null) {
            //通常是服务器出错返回了非约定格式
            onHttpFail(HttpStatusConstants.CODE_DEFAULT, "网络错误,返回非正常格式，请稍后再试");
        } else {
            if (result.getCode() == HttpStatusConstants.CODE_SUCCESS) {
                //正确返回约定的CODE_SUCCESS码
                onHttpSuccess(result.getData(), result.getMsg());
            } else {
                //返回约定的其他类型码，可根据返回码进行相对应的操作,这里屏蔽掉被挤下线掉toast
                if (result.getCode() != HttpCodeConstant.HTTPCODE_STATUS_KICK) {
                    onHttpFail(result.getCode(), result.getMsg());
                }
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        try {

            if (e instanceof HycanBizNetworkException) {
                onHttpFail(((HycanBizNetworkException) e).code, ((HycanBizNetworkException) e).msg);
                return;
            }

            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof HttpException
                    || e instanceof UnknownHostException) {
                String msg = e.getMessage();
                //401token校验失败的，不toast
                if (msg != null && msg.contains("HTTP 401")) {
                    return;
                }//HTTP 503 Service Temporarily Unavailable，不toast
                if (msg != null && msg.contains("HTTP 503")) {
                    return;
                }
                Log.e("网络异常为什么弹出1", e.getMessage());
                Log.e("网络异常为什么弹出2", e.getLocalizedMessage());

                onNetWorkError("网络异常");
            } else if (e instanceof SocketTimeoutException) {
                onNetWorkError("网络连接超时");
            } else if (e instanceof CompositeException) {

                /**
                 * WriteArticlePresenter 在上传图片的时候，找不到图片会抛出FlieNofoundException
                 * 22-09-14 测试要求优化toast的内容，不能直接抛出异常，这里做处理
                 */
                int size = ((CompositeException) e).size();
                if (size > 0) {
                    onNetWorkError(((CompositeException) e).getExceptions().get(size - 1).getMessage());
                }

            } else {
                onNetWorkError(e.getMessage());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            e.printStackTrace();
            onComplete();
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 正常返回结果
     *
     * @param result 结果
     * @param msg    附带消息
     */
    public void onHttpSuccess(T result, String msg) {
    }

    /**
     * 正常返回但code不是CODE_SUCCESS
     *
     * @param code 约定的错误码
     * @param msg  附带消息
     */
    public void onHttpFail(int code, String msg) {
    }

    /**
     * 非正常返回，通常是网络异常问题
     *
     * @param msg 异常描述
     */
    public void onNetWorkError(String msg) {

    }
//    public abstract void onHttpComplete();


}
