package com.gac.nioapp.test.bean;

import com.syy.modulebase.http.bean.UserBean;

/**
 * description:
 *
 * @author xyz
 * @email 826363590@qq.com
 * created on: 2019-06-18 13:52
 */
public class UserStatusBean {

    /**
     * praiseCount : 1
     * publicCount : 1
     * followCount : 1
     * fansCount : 1
     * user : {"userId":1,"nickname":"","gender":1,"avatar":"","country":"","province":"","city":""}
     * status : 1
     * blacklist : false
     * self : 1
     * "praiseCountName": "1.1",
     *     "publicCountName": "1.1",
     *     "followCountName": "1.1",
     *     "fansCountName": "1.1",
     *     "publicCountUnit": "w",
     *     "praiseCountUnit": "w",
     *     "followCountUnit": "w",
     *     "fansCountUnit": "w"
     */

    private int praiseCount;
    private int publicCount;
    private int followCount;
    private int fansCount;
    private int collectCount;
    private String praiseCountName;
    private String publicCountName;
    private String followCountName;
    private String fansCountName;
    private String collectCountName;
    private String publicCountUnit;
    private String praiseCountUnit;
    private String followCountUnit;
    private String fansCountUnit;
    private String collectCountUnit;
    private UserBean user;
    private UserBean userDto;
    private int status;
    private boolean blacklist;
    private int self;
    private int registerDays;
    public UserBean getUserDto() {
        return userDto != null ? userDto : user;
    }

    public void setUserDto(UserBean userDto) {
        this.userDto = userDto;
    }

    public UserBean getUser() {
        return user != null ? user : userDto;
    }
    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getPublicCount() {
        return publicCount;
    }

    public void setPublicCount(int publicCount) {
        this.publicCount = publicCount;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isBlacklist() {
        return blacklist;
    }

    public void setBlacklist(boolean blacklist) {
        this.blacklist = blacklist;
    }

    public int getSelf() {
        return self;
    }

    public void setSelf(int self) {
        this.self = self;
    }


    public void setPublicCountName(String publicCountName) {
        this.publicCountName = publicCountName;
    }

    public void setPraiseCountName(String praiseCountName) {
        this.praiseCountName = praiseCountName;
    }

    public void setFollowCountName(String followCountName) {
        this.followCountName = followCountName;
    }

    public void setFansCountName(String fansCountName) {
        this.fansCountName = fansCountName;
    }

    public void setFansCountUnit(String fansCountUnit) {
        this.fansCountUnit = fansCountUnit;
    }

    public void setFollowCountUnit(String followCountUnit) {
        this.followCountUnit = followCountUnit;
    }

    public void setPraiseCountUnit(String praiseCountUnit) {
        this.praiseCountUnit = praiseCountUnit;
    }

    public void setPublicCountUnit(String publicCountUnit) {
        this.publicCountUnit = publicCountUnit;
    }

    public String getPraiseCountUnit() {
        return praiseCountUnit;
    }

    public String getFansCountUnit() {
        return fansCountUnit;
    }

    public String getFollowCountUnit() {
        return followCountUnit;
    }

    public String getPublicCountUnit() {
        return publicCountUnit;
    }

    public String getFansCountName() {
        return fansCountName;
    }

    public String getFollowCountName() {
        return followCountName;
    }

    public String getPraiseCountName() {
        return praiseCountName;
    }

    public String getPublicCountName() {
        return publicCountName;
    }
    public String getUserId() {
        UserBean userBean = user != null ? user : userDto;
        if (userBean != null){
            return userBean.getUserId();
        }
        return "";
    }

    public String getNickname() {
        UserBean userBean = user != null ? user : userDto;
        if (userBean != null){
            return userBean.getNickName();
        }
        return "";
    }


    public void setCllectCountName(String cllectCountName) {
        this.collectCountName = cllectCountName;
    }

    public String getCllectCountName() {
        return collectCountName;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCountUnit(String collectCountUnit) {
        this.collectCountUnit = collectCountUnit;
    }

    public String getCollectCountUnit() {
        return collectCountUnit;
    }

    public int getRegisterDays() {
        return registerDays;
    }

    public void setRegisterDays(int registerDays) {
        this.registerDays = registerDays;
    }
}
