package com.gac.nioapp.test.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.syy.modulebase.http.constants.HttpKeyConstant;

/**
 * @Description：描述信息
 * @Author：Sai
 * @Date：2019/3/27 11:12
 */
public class AddressBean implements Parcelable {

    /**
     * id : 4
     * userId : 1
     * receiverName : 1qwewqe23
     * receiverPhoneNo : 7891234
     * receiverMobileNo : 1
     * provinceId : 1
     * provinceName : 123
     * cityId : 1
     * cityName : 123
     * districtName : 1
     * districtId : 1
     * streetId : 123
     * streetName : 123
     * detailAddress : 123
     * receiverZipCode : 537300
     * defaultAddress : 1
     * creator : 用户1
     * creatorId : 123
     * createdTime : 2019-03-10T22:48:09.000+0000
     * lastOperator : admin
     * lastOperatorId : 1
     */

    private String id;
    private String userId;
    private String receiverName;
    private String receiverPhoneNo;
    private String receiverMobileNo;
    private long provinceId;
    private String provinceName;
    private long cityId;
    private String cityName;
    private String districtName;
    private long districtId;
    private long streetId;
    private String streetName;
    private String detailAddress;
    private String receiverZipCode;
    private int defaultAddress = HttpKeyConstant.ADDRESSNOTDEFAULT;

    @SerializedName("isEffective")
    private Boolean effective;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhoneNo() {
        return receiverPhoneNo;
    }

    public void setReceiverPhoneNo(String receiverPhoneNo) {
        this.receiverPhoneNo = receiverPhoneNo;
    }

    public String getReceiverMobileNo() {
        return receiverMobileNo;
    }

    public void setReceiverMobileNo(String receiverMobileNo) {
        this.receiverMobileNo = receiverMobileNo;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public long getStreetId() {
        return streetId;
    }

    public void setStreetId(long streetId) {
        this.streetId = streetId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getReceiverZipCode() {
        return receiverZipCode;
    }

    public void setReceiverZipCode(String receiverZipCode) {
        this.receiverZipCode = receiverZipCode;
    }

    public int getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(int defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Boolean isEffective() {
        return effective != null && effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective;
    }

    public void clone(AddressBean other) {
        id = other.id;
        userId = other.userId;
        receiverName = other.receiverName;
        receiverPhoneNo = other.receiverPhoneNo;
        receiverMobileNo = other.receiverMobileNo;
        provinceId = other.provinceId;
        provinceName = other.provinceName;
        cityId = other.cityId;
        cityName = other.cityName;
        districtName = other.districtName;
        districtId = other.districtId;
        streetId = other.streetId;
        streetName = other.streetName;
        detailAddress = other.detailAddress;
        receiverZipCode = other.receiverZipCode;
        defaultAddress = other.defaultAddress;
        effective = other.effective;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.userId);
        dest.writeString(this.receiverName);
        dest.writeString(this.receiverPhoneNo);
        dest.writeString(this.receiverMobileNo);
        dest.writeLong(this.provinceId);
        dest.writeString(this.provinceName);
        dest.writeLong(this.cityId);
        dest.writeString(this.cityName);
        dest.writeString(this.districtName);
        dest.writeLong(this.districtId);
        dest.writeLong(this.streetId);
        dest.writeString(this.streetName);
        dest.writeString(this.detailAddress);
        dest.writeString(this.receiverZipCode);
        dest.writeInt(this.defaultAddress);
        dest.writeByte(this.effective != null && this.effective ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readString();
        this.userId = source.readString();
        this.receiverName = source.readString();
        this.receiverPhoneNo = source.readString();
        this.receiverMobileNo = source.readString();
        this.provinceId = source.readLong();
        this.provinceName = source.readString();
        this.cityId = source.readLong();
        this.cityName = source.readString();
        this.districtName = source.readString();
        this.districtId = source.readLong();
        this.streetId = source.readLong();
        this.streetName = source.readString();
        this.detailAddress = source.readString();
        this.receiverZipCode = source.readString();
        this.defaultAddress = source.readInt();
        this.effective = source.readByte() != 0;
    }

    public AddressBean() {
    }

    protected AddressBean(Parcel in) {
        this.id = in.readString();
        this.userId = in.readString();
        this.receiverName = in.readString();
        this.receiverPhoneNo = in.readString();
        this.receiverMobileNo = in.readString();
        this.provinceId = in.readLong();
        this.provinceName = in.readString();
        this.cityId = in.readLong();
        this.cityName = in.readString();
        this.districtName = in.readString();
        this.districtId = in.readLong();
        this.streetId = in.readLong();
        this.streetName = in.readString();
        this.detailAddress = in.readString();
        this.receiverZipCode = in.readString();
        this.defaultAddress = in.readInt();
        this.effective = in.readByte() != 0;
    }

    public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel source) {
            return new AddressBean(source);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };
}
