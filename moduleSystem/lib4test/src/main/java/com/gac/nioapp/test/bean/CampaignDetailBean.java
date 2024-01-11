package com.gac.nioapp.test.bean;


import java.util.List;

/**
 * description:
 *
 * @author xyz
 * @email 826363590@qq.com
 * created on: 2019/5/15 4:39 PM
 */
public class CampaignDetailBean {

    /**
     * id : 1
     * activityType : 1
     * activityTitle : 5.1春游
     * activityBannerUrl : http://www.baidu.com/img.jpg
     * startTime : 2019-05-01 10:00:00
     * status : 0
     * activityTag : [{"tagName":"旅游"}]
     * activityOpenType : 0
     * endTime : 2019-05-01 23:00:00
     * activityPosition : 广州市越秀区流花湖公园
     * activityLon : 113.25642
     * activityLat : 23.142021
     * activityQuota : 100
     * activitySignNum : 10
     * signUpList : [{"userId":1,"avatar":"http://www.baidu.com/img.jpg"}]
     * activityContent : <img src="http://image.haoji.me/201810/20181013_173826_714_7935.png" /> <div>大家都知道，整个VSCode编辑器就是一张大的网页</div>
     * remark : 1、出门不要忘记带水；
     * activityPayScore : 500
     * confirmType : 2
     * signId : 1
     * activityProcess : 1
     * buttonName : 立即报名
     * buttonStatus : 1
     */

    private int id;
    private int activityType;
    private String activityTitle;
    private String activityBannerUrl;
    private String newActivityBannerUrl;
    private String startTime;
    private int status;
    private int activityOpenType;
    private String endTime;
    private String activityOpenTypesName; //开放对象：可能多选的拼接身份 eg:007意向金用户、Z03盲定车主
    private String activityPosition;
    private String activityLon;
    private String activityLat;
    private int activityQuota;
    private int activitySignNum;
    private String activityContent;
    private String remark;
    private int activityPayScore;
    private int confirmType;
    private int signId;
    private int activityProcess;
    private String buttonName;
    private int buttonStatus;
    private List<ActivityTagBean> activityTag;
    private List<SignUpListBean> signUpList;
    private String miniProgramShareUrl;
    private String htmlShareUrl;
    private String bgLon; //百度地图坐标系 经度
    private String bgLat; //百度地图坐标系 维度
    private String signTime; //报名截止时间【3.2.5.5新增】


    public String getNewActivityBannerUrl() {
        return newActivityBannerUrl;
    }

    public void setNewActivityBannerUrl(String newActivityBannerUrl) {
        this.newActivityBannerUrl = newActivityBannerUrl;
    }


    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getBgLon() {
        return bgLon;
    }

    public void setBgLon(String bgLon) {
        this.bgLon = bgLon;
    }

    public String getBgLat() {
        return bgLat;
    }

    public void setBgLat(String bgLat) {
        this.bgLat = bgLat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityBannerUrl() {
        return activityBannerUrl;
    }

    public void setActivityBannerUrl(String activityBannerUrl) {
        this.activityBannerUrl = activityBannerUrl;
    }

    public String getActivityOpenTypesName() {
        return activityOpenTypesName;
    }

    public void setActivityOpenTypesName(String activityOpenTypesName) {
        this.activityOpenTypesName = activityOpenTypesName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActivityOpenType() {
        return activityOpenType;
    }

    public void setActivityOpenType(int activityOpenType) {
        this.activityOpenType = activityOpenType;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActivityPosition() {
        return activityPosition;
    }

    public void setActivityPosition(String activityPosition) {
        this.activityPosition = activityPosition;
    }

    public String getActivityLon() {
        return activityLon;
    }

    public void setActivityLon(String activityLon) {
        this.activityLon = activityLon;
    }

    public String getActivityLat() {
        return activityLat;
    }

    public void setActivityLat(String activityLat) {
        this.activityLat = activityLat;
    }

    public int getActivityQuota() {
        return activityQuota;
    }

    public void setActivityQuota(int activityQuota) {
        this.activityQuota = activityQuota;
    }

    public int getActivitySignNum() {
        return activitySignNum;
    }

    public void setActivitySignNum(int activitySignNum) {
        this.activitySignNum = activitySignNum;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getActivityPayScore() {
        return activityPayScore;
    }

    public void setActivityPayScore(int activityPayScore) {
        this.activityPayScore = activityPayScore;
    }

    public int getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(int confirmType) {
        this.confirmType = confirmType;
    }

    public int getSignId() {
        return signId;
    }

    public void setSignId(int signId) {
        this.signId = signId;
    }

    public int getActivityProcess() {
        return activityProcess;
    }

    public void setActivityProcess(int activityProcess) {
        this.activityProcess = activityProcess;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public int getButtonStatus() {
        return buttonStatus;
    }

    public void setButtonStatus(int buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    public List<ActivityTagBean> getActivityTag() {
        return activityTag;
    }

    public void setActivityTag(List<ActivityTagBean> activityTag) {
        this.activityTag = activityTag;
    }

    public List<SignUpListBean> getSignUpList() {
        return signUpList;
    }

    public void setSignUpList(List<SignUpListBean> signUpList) {
        this.signUpList = signUpList;
    }

    public static class ActivityTagBean {
        /**
         * tagName : 旅游
         */

        private String tagName;

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }

    public static class SignUpListBean {
        /**
         * userId : 1
         * avatar : http://www.baidu.com/img.jpg
         */

        private int userId;
        private String avatar;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public String getMiniProgramShareUrl() {
        return miniProgramShareUrl;
    }

    public void setMiniProgramShareUrl(String miniProgramShareUrl) {
        this.miniProgramShareUrl = miniProgramShareUrl;
    }

    public String getHtmlShareUrl() {
        return htmlShareUrl;
    }

    public void setHtmlShareUrl(String htmlShareUrl) {
        this.htmlShareUrl = htmlShareUrl;
    }

}
