package com.gac.nioapp.test.bean;

/**
 * description:
 *
 * @author xyz
 * @email 826363590@qq.com
 * created on: 2019/5/14 4:48 PM
 */
public class CampaignBean {


    /**
     * id : 1
     * activityType : 1
     * activityTitle : 5.1春游
     * activityBannerUrl : http://www.baidu.com/img.jpg
     * startTime : 2019-05-01 10:00:00
     * status : 1
     * sort : 1
     * jumpType : 0
     * contentId : 1
     * activityProcess : 1
     * buttonName : 立即报名
     * buttonStatus : 1
     */
    private int id;
    private int activityType;
    private String activityTitle;
    private String activityBannerUrl;
    private String startTime;
    private int status;
    private int sort;
    private int jumpType;
    private int contentId;
    private int activityProcess;
    private String buttonName;
    private int buttonStatus;

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getJumpType() {
        return jumpType;
    }

    public void setJumpType(int jumpType) {
        this.jumpType = jumpType;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
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
}
