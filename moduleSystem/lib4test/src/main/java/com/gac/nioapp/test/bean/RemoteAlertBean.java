package com.gac.nioapp.test.bean;

/**
 * @package： com.gac.common.bean
 * @describe：
 * @author： liming
 * @time： 2020/7/24 9:55 AM
 * @e-mail： liming@gac-nio.com
 */
public class RemoteAlertBean {
    private int isShow; //是否弹窗 ，0弹框，1弹窗
    private String titleImg; //图片地址
    private JumpUrlContentBean jumpContent;

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public JumpUrlContentBean getJumpContent() {
        return jumpContent;
    }

    public void setJumpContent(JumpUrlContentBean jumpContent) {
        this.jumpContent = jumpContent;
    }
}
