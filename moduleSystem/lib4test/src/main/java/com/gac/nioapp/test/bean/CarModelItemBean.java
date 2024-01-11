package com.gac.nioapp.test.bean;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.utils.TextUtils;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2020-02-19 10:13
 ** @describe
 *******************************/
public class CarModelItemBean implements Cloneable {
    private String id;
    private String name;
    private String subTitle;
    private String imgUrl;
    private boolean isSelected = false;
    private String iccid;

    private String carType;

    private Object data;

    //数据是否源于网络
    private boolean isFromCloud = false;

    public boolean isFromCloud() {
        return isFromCloud;
    }

    public void setFromCloud(boolean fromCloud) {
        isFromCloud = fromCloud;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        CarModelItemBean nObj = new CarModelItemBean();
        if (TextUtils.isEmpty(carType)) {
            nObj.setCarType(carType);
        }
        if (TextUtils.isEmpty(id)) {
            nObj.setId(id);
        }
        if (TextUtils.isEmpty(name)) {
            nObj.setName(name);
        }
        if (TextUtils.isEmpty(subTitle)) {
            nObj.setSubTitle(subTitle);
        }
        if (TextUtils.isEmpty(imgUrl)) {
            nObj.setImgUrl(imgUrl);
        }
        if (TextUtils.isEmpty(iccid)) {
            nObj.setIccid(iccid);
        }

        if (data != null) {
            nObj.setData(data);
        }
        nObj.setFromCloud(isFromCloud);
        nObj.setSelected(isSelected);

        return nObj;
    }
}
