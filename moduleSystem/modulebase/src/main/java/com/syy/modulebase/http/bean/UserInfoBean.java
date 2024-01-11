package com.syy.modulebase.http.bean;

import java.util.List;

/**
 * @Description：主要是保存我的基本资料
 * @Author：Sai
 * @Date：2019/3/15 11:17
 */
public class UserInfoBean extends UserBean {

    /**
     * userId : 1
     * phone : 17702081550
     * nickname : 小三
     * gender : 1
     * birthday :
     * intro :
     * email :
     */

    private String phone;

    private String birthday;
    protected boolean isHadBuyCarOne; //是否曾经的车主身份
    private String email;
    private List<Integer> carTypes; // 车主身份数组


    public String getPhone() {
        return phone;
    }

    public List<Integer> getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(List<Integer> carTypes) {
        this.carTypes = carTypes;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String intro;//个人简介

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 判断性别
     *
     * @return
     */
    public boolean isMan() {
        return getGender() == 1;
    }

    public boolean isHadBuyCarOne() {
        return isHadBuyCarOne;
    }

    public void setHadBuyCarOne(boolean hadBuyCarOne) {
        isHadBuyCarOne = hadBuyCarOne;
    }
}
