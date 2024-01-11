package com.gac.nioapp.test.observer;

/**
 * Author:ltf
 * Email:15975026890@163.com
 * Date:2022/12/6
 * Name:HycanBizNetworkException
 * Description:
 **/
public class HycanBizNetworkException extends RuntimeException{

    int code;
    String msg;

    public HycanBizNetworkException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
