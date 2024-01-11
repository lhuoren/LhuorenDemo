package com.gac.nioapp.test.bean;

import android.os.Parcel;
import android.os.Parcelable;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-11-14 16:50
 ** @describe
 *******************************/
public class JumpUrlParamBean implements Parcelable {
    /**
     * name : id
     * param : 1
     */

    private String name;
    private String param;

    protected JumpUrlParamBean(Parcel in) {
        name = in.readString();
        param = in.readString();
    }

    public JumpUrlParamBean(String name, String param) {
        this.name = name;
        this.param = param;
    }

    public static final Creator<JumpUrlParamBean> CREATOR = new Creator<JumpUrlParamBean>() {
        @Override
        public JumpUrlParamBean createFromParcel(Parcel in) {
            return new JumpUrlParamBean(in);
        }

        @Override
        public JumpUrlParamBean[] newArray(int size) {
            return new JumpUrlParamBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(param);
    }
}