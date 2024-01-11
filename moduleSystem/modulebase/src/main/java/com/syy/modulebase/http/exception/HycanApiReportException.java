package com.syy.modulebase.http.exception;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

/**
 * @author： jack
 * @date： 2022/5/24 5:08 下午
 * @description： TODO
 */
public abstract class HycanApiReportException extends RuntimeException {
    public HycanApiReportException() {
        super();
    }

    public HycanApiReportException(Throwable cause) {
        super(cause);
    }

    /**
     * 备注
     *
     * @return
     */
    public abstract String tag();

    /**
     * 接口
     *
     * @return
     */
    public abstract String api();

    /**
     * 参数对象,最终会通过 Gson().toJson()
     *
     * @return
     */
    public Object requestBody() {
        return null;
    }

    /**
     * 返参,最终会通过 Gson().toJson()
     *
     * @return
     */
    public Object responseBody() {
        return null;
    }

    @Nullable
    @Override
    public String getMessage() {
        try {
            return "\ntag:[" + tag() + "]\napi:[" + api() + "]\nrequestBody:[" + new Gson().toJson(requestBody()) + "]\nresponseBody:[" + new Gson().toJson(responseBody()) + "]";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "print exception error.";
    }
}
