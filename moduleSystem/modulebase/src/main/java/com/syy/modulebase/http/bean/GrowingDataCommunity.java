package com.syy.modulebase.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @package： com.gac.common.bean
 * @describe：
 * @author： liming
 * @time： 2020/10/26 2:02 PM
 * @e-mail： liming@gac-nio.com
 */
public class GrowingDataCommunity implements Parcelable {
    private int userLevel;

    public GrowingDataCommunity() {
    }

    protected GrowingDataCommunity(Parcel in) {
        userLevel = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userLevel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GrowingDataCommunity> CREATOR = new Creator<GrowingDataCommunity>() {
        @Override
        public GrowingDataCommunity createFromParcel(Parcel in) {
            return new GrowingDataCommunity(in);
        }

        @Override
        public GrowingDataCommunity[] newArray(int size) {
            return new GrowingDataCommunity[size];
        }
    };

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
}
