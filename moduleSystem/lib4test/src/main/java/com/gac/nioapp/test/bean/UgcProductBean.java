package com.gac.nioapp.test.bean;

/**
 * @package： com.hycan.community.bean
 * @describe：
 * @author： liming
 * @time： 2019-11-29 17:10
 * @e-mail： liming@gac-nio.com
 */
public class UgcProductBean {
    private int procuctId;
    private String productImg;
    private String productName;

    public int getProcuctId() {
        return procuctId;
    }

    public void setProcuctId(int procuctId) {
        this.procuctId = procuctId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
