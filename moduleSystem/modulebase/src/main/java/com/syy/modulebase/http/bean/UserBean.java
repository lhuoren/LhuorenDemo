package com.syy.modulebase.http.bean;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import com.syy.modulebase.http.constants.Constants;

/**
 * 添加字段注意在定义区域后面顺序增加，避免漏写Parcel 的write and read
 */
public class UserBean implements Parcelable, InsertData {

    /**
     * userId : 1
     * username :
     * avatar :
     */
    protected String userId;
    protected String username;
    protected String nickname;
    protected String account;
    protected String avatar;
    protected String uflag;
    protected String userSig;
    protected int userType;
    protected int carType;
    protected int carOwnerType; //车主类型--用于前端加V: 0为普通用户,1为预定车主,2为车主(Z03盲定和Z03大定都是预定车主)
    private int isHadBuyCar;//是否曾经的车主身份 0:不是 1：是
    private String country;
    private String province;
    private String city;
    private String remark;//包括员工简介与个人简介的结合
    private int gender;
    private int agent;//0普通人， 1：经纪人，2HYCAN START
    private int planner; //0普通身份，1是规划师
    private String mTag = Constants.USER_TAG;
    private String color = "#00B9EF";

    private GrowingDataCommunity growingDataCommunityDto;
    private String userRemark;

    // 用户身份添加新的用户身份标识信息bean
    private UserIconModel userIconModel;


    protected UserBean(Parcel in) {
        userId = in.readString();
        username = in.readString();
        nickname = in.readString();
        account = in.readString();
        avatar = in.readString();
        uflag = in.readString();
        userSig = in.readString();
        userType = in.readInt();
        carType = in.readInt();
        carOwnerType = in.readInt();
        isHadBuyCar = in.readInt();
        country = in.readString();
        province = in.readString();
        city = in.readString();
        remark = in.readString();
        gender = in.readInt();
        agent = in.readInt();
        planner = in.readInt();
        mTag = in.readString();
        color = in.readString();
        growingDataCommunityDto = in.readParcelable(GrowingDataCommunity.class.getClassLoader());
        userIconModel = in.readParcelable(Thread.currentThread().getContextClassLoader());
        userRemark = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(username);
        dest.writeString(nickname);
        dest.writeString(account);
        dest.writeString(avatar);
        dest.writeString(uflag);
        dest.writeString(userSig);
        dest.writeInt(userType);
        dest.writeInt(carType);
        dest.writeInt(carOwnerType);
        dest.writeInt(isHadBuyCar);
        dest.writeString(country);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(remark);
        dest.writeInt(gender);
        dest.writeInt(agent);
        dest.writeInt(planner);
        dest.writeString(mTag);
        dest.writeString(color);
        dest.writeParcelable(growingDataCommunityDto, flags);
        dest.writeParcelable(userIconModel, flags);
        dest.writeString(userRemark);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

    public int getGender() {
        return gender;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getIsHadBuyCarOne() {
        return isHadBuyCar;
    }

    public void setIsHadBuyCarOne(int isHadBuyCarOne) {
        this.isHadBuyCar = isHadBuyCarOne;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Deprecated
    public String getUsername() {
        return username;
    }

    @Deprecated
    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public boolean isEmployee() {
        return getUserType() == 1;
    }

    public boolean isHycanStar() {
        return getAgent() == 2;
    }

    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    public String getNickName() {
        return nickname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }

    public int getCarType() {
        //优先显示深V，2：深V，1：浅V
        if (this.carType == 2 || this.carOwnerType == 2) {
            return 2;
        } else if (this.carType == 1 || this.carOwnerType == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isCarOwner() {
        return getCarType() > 0 || getCarOwnerType() > 0;
    }

    public int getCarOwnerType() {
        return carOwnerType;
    }

    public void setCarOwnerType(int carOwnerType) {
        this.carOwnerType = carOwnerType;
    }

    public void setUflag(String uflag) {
        this.uflag = uflag;
    }

    public String getUflag() {
        return uflag;
    }

    public void setUserSig(String userSig) {
        this.userSig = userSig;
    }

    public String getUserSig() {
        return userSig;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public int getAgent() {
        return agent;
    }

    public void setAgent(int agent) {
        this.agent = agent;
    }

    public int getPlanner() {
        return planner;
    }

    public boolean isPlanner() {
        return planner == 1;
    }

    public void setPlanner(int planner) {
        this.planner = planner;
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    public String getTag() {
        return mTag;
    }

    public GrowingDataCommunity getGrowingDataCommunityDto() {
        return growingDataCommunityDto;
    }

    public void setGrowingDataCommunityDto(GrowingDataCommunity growingDataCommunityDto) {
        this.growingDataCommunityDto = growingDataCommunityDto;
    }

    public UserIconModel getUserIconModel() {
        return userIconModel;
    }

    public void setUserIconModel(UserIconModel userIconModel) {
        this.userIconModel = userIconModel;
    }


    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public UserBean() {
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBean user = (UserBean) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        return nickname != null ? nickname.equals(user.nickname) : user.nickname == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }

    @Override
    public CharSequence charSequence() {
        return "@" + nickname + " ";
    }

    @Override
    public FormatRange.FormatData formatData() {
        return new UserConvert(this);
    }

    @Override
    public int color() {
        return Color.parseColor(color);
    }


    private class UserConvert implements FormatRange.FormatData {


        private final UserBean user;

        public UserConvert(UserBean user) {
            this.user = user;
        }

        @Override
        public CharSequence formatCharSequence() {
            return String.format(TagBean.TAG_FORMART, TagBean.USER, user.getUserId(), user.getNickName(), "@" + user.getNickName());
        }
    }
}
