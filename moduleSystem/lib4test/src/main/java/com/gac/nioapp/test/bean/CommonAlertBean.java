package com.gac.nioapp.test.bean;

/**
 * @package： com.gac.common.bean
 * @describe：
 * @author： liming
 * @time： 2020/7/24 9:55 AM
 * @e-mail： liming@gac-nio.com
 */
public class CommonAlertBean {
    private boolean popup;//true:弹窗 false:不弹
    private int id;//弹窗ID,有些场景需要用到

    public boolean isPopup() {
        return popup;
    }

    public void setPopup(boolean popup) {
        this.popup = popup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
