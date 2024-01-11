package com.gac.nioapp.test.observer;


import com.syy.modulebase.http.bean.HttpResult;
import com.syy.modulebase.http.constants.HttpStatusConstants;

import java.util.function.Function;

/**
 * Author:ltf
 * Email:15975026890@163.com
 * Date:2022/12/6
 * Name:HttpResultCheckFunction
 * Description:
 * 有些地方不是直接subscribe ， 所以用不上 HttpResultObserver 来检查结果
 * 检查业务code是否成功 , 抛出  HycanBizNetworkException
 *
 * 用法
 * observable.map(new HttpResultCheckFunction<T>())
 **/
public class HttpResultCheckFunction<T> implements Function<HttpResult<T>, HttpResult<T>> {


    @Override
    public HttpResult<T> apply(HttpResult<T> tHttpResult) {
        if (tHttpResult.getCode() != HttpStatusConstants.CODE_SUCCESS) {
            throw new HycanBizNetworkException(tHttpResult.getCode(), tHttpResult.getMsg());
        }
        return tHttpResult;
    }


}
