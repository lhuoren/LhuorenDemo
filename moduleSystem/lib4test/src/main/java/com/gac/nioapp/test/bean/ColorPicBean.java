package com.gac.nioapp.test.bean;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-08-13 15:06
 ** @describe
 *******************************/
public class ColorPicBean {


    /**
     * pic_1 : https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20200313161532%2F54d0d01df4914627affedb12e1966e69.png
     * pic_0 : https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20200313161532%2F54d0d01df4914627affedb12e1966e69.png
     * pic_3 : https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20200313161532%2F54d0d01df4914627affedb12e1966e69.png
     * pic_4 : https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20200313161532%2F54d0d01df4914627affedb12e1966e69.png
     * pic_5 : https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20200313161532%2F54d0d01df4914627affedb12e1966e69.png
     * pic_6 : https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20200313161532%2F54d0d01df4914627affedb12e1966e69.png
     * pic_7 : https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20200313161532%2F54d0d01df4914627affedb12e1966e69.png
     * pic_2 : https://test-azure-community.obs.cn-south-1.myhuaweicloud.com/20200313161532%2F54d0d01df4914627affedb12e1966e69.png
     */
    private String pic_0;//可控车辆列表里的车外观图
    private String pic_1;//爱车首页
    private String pic_2;//爱车首页-充电中
    private String pic_3;//车辆位置-鸣笛闪灯
    private String pic_4;//激活确认页（同车订单头图）
    private String pic_5;//桌面widget-车图
    private String pic_6;
    private String pic_7;
    //桌面widget-背景色-外观颜色类型6种：
    // 101=晨辉·白
    // 102=极夜·黑
    // 103=辉月·银
    // 104=赤霞·红
    // 105=雾隐·灰
    // 106=薄荷贝绿
    private int backColorCode;

    public String getPic_1() {
        return pic_1;
    }

    public void setPic_1(String pic_1) {
        this.pic_1 = pic_1;
    }

    public String getPic_0() {
        return pic_0;
    }

    public void setPic_0(String pic_0) {
        this.pic_0 = pic_0;
    }

    public String getPic_3() {
        return pic_3;
    }

    public void setPic_3(String pic_3) {
        this.pic_3 = pic_3;
    }

    public String getPic_4() {
        return pic_4;
    }

    public void setPic_4(String pic_4) {
        this.pic_4 = pic_4;
    }

    public String getPic_5() {
        return pic_5;
    }

    public void setPic_5(String pic_5) {
        this.pic_5 = pic_5;
    }

    public String getPic_6() {
        return pic_6;
    }

    public void setPic_6(String pic_6) {
        this.pic_6 = pic_6;
    }

    public String getPic_7() {
        return pic_7;
    }

    public void setPic_7(String pic_7) {
        this.pic_7 = pic_7;
    }

    public String getPic_2() {
        return pic_2;
    }

    public void setPic_2(String pic_2) {
        this.pic_2 = pic_2;
    }

    public void setBackColorCode(int backColorCode) {
        this.backColorCode = backColorCode;
    }

    public int getBackColorCode() {
        return backColorCode;
    }
}
