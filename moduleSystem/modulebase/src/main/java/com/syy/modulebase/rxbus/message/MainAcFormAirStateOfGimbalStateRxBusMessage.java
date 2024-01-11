package com.syy.modulebase.rxbus.message;

public class MainAcFormAirStateOfGimbalStateRxBusMessage<T> {
    private int code;
    private T data;

    public MainAcFormAirStateOfGimbalStateRxBusMessage(int code) {
        this.code = code;
    }

    public MainAcFormAirStateOfGimbalStateRxBusMessage(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
