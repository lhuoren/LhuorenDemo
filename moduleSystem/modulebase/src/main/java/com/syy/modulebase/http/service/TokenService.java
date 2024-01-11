package com.syy.modulebase.http.service;

import com.syy.modulebase.http.bean.UserTokenBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author： jack
 * @date： 2022/1/29 10:56 上午
 * @description： TODO
 */
public interface TokenService {
    /**
     * 通过 refreshtoken 刷新token
     */
    @POST("auth/oauth/token")
    Observable<UserTokenBean> refreshToken(@QueryMap Map<String, Object> map);
}
