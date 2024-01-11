package com.gac.nioapp.test.bean;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2020-03-06 18:30
 ** @describe
 *******************************/
public class SelectOrderCarsBean {

    /**
     * carImage :
     * carName :
     * carOrderStatus : 1
     * carOrderType : 1
     * carOrderBaseId :
     * versionName :
     */

    private String carImage = "";
    private String carName = "";
    private int carOrderStatus;
    private int carOrderType = -1; //订单类型：1-订单，2-心愿单,3-未转化订单
    private String carOrderBaseId = "";
    private String versionName = "";
    private int carWishId = -1;
    private String carOrderStatusStr = "";


    private boolean isSelected;//本地字段

    private int refreshOption = 1;//0-屏蔽重新配置(年款北京限定迭代)  1-开启重新配置

    private int reselectStatus;//V3.2.12重新选配状态，0：非重新选配；1：重新选配

    public int getRefreshOption() {
        return refreshOption;
    }

    public void setRefreshOption(int refreshOption) {
        this.refreshOption = refreshOption;
    }

    public String getCarOrderStatusStr() {
        if (reselectStatus == 1) return "待确认选配";
        return carOrderStatusStr;
    }

    public void setCarOrderStatusStr(String carOrderStatusStr) {
        this.carOrderStatusStr = carOrderStatusStr;
    }

    public int getCarWishId() {
        return carWishId;
    }

    public void setCarWishId(int carWishId) {
        this.carWishId = carWishId;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarOrderStatus() {
        return carOrderStatus;
    }

    public void setCarOrderStatus(int carOrderStatus) {
        this.carOrderStatus = carOrderStatus;
    }

    public int getCarOrderType() {
        return carOrderType;
    }

    public void setCarOrderType(int carOrderType) {
        this.carOrderType = carOrderType;
    }

    public String getCarOrderBaseId() {
        return carOrderBaseId;
    }

    public void setCarOrderBaseId(String carOrderBaseId) {
        this.carOrderBaseId = carOrderBaseId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getReselectStatus() {
        return reselectStatus;
    }

    public void setReselectStatus(int reselectStatus) {
        this.reselectStatus = reselectStatus;
    }
}
