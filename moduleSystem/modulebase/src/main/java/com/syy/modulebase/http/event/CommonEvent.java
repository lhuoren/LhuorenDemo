package com.syy.modulebase.http.event;

/**
 * @package： com.gac.common.event
 * @describe： 带参数的event
 * @author： liming
 * @time： 2019/4/23 12:46 PM
 * @e-mail： liming@gac-nio.com
 */
public class CommonEvent {
    private String enentType;
    private Object param;

    public CommonEvent(String enentType) {
        this.enentType = enentType;
    }

    public CommonEvent(String enentType, Object param) {
        this.enentType = enentType;
        this.param = param;
    }

    public String getEnentType() {
        return enentType;
    }

    public void setEnentType(String enentType) {
        this.enentType = enentType;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
