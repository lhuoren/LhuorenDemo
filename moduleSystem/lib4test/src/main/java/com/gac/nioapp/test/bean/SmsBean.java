package com.gac.nioapp.test.bean;

/**
 * @package： com.hycan.community.bean
 * @describe：
 * @author： liming
 * @time： 2020/6/11 11:53 AM
 * @e-mail： liming@gac-nio.com
 */
public class SmsBean {
    private String uuid;
    private String bgImgUrl;//背景图片路径
    private String slideImgUrl;//滑块图片路径
    private int picCode; //0-不需弹框，1-弹框

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBgImgUrl() {
        return bgImgUrl;
    }

    public void setBgImgUrl(String bgImgUrl) {
        this.bgImgUrl = bgImgUrl;
    }

    public String getSlideImgUrl() {
        return slideImgUrl;
    }

    public void setSlideImgUrl(String slideImgUrl) {
        this.slideImgUrl = slideImgUrl;
    }

    public int getPicCode() {
        return picCode;
    }

    public void setPicCode(int picCode) {
        this.picCode = picCode;
    }
}
