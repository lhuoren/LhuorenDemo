package com.gac.nioapp.test.bean;

/**
 * description:
 *
 * @author xyz
 * @email 826363590@qq.com
 * created on: 2019/5/24 4:25 PM
 */
public class CampaignOrderBean {

    /**
     * activityId : 1
     * activityTitle : 春游
     * activityBannerUrl : http://www.baidu.com
     * startTime : 2019-05-01 10:10:10
     * activityPosition : 广州市越秀区流花湖公园
     * activityLon : 113.25642
     * activityLat : 23.142021
     * signId : 1
     */

    private int activityId;
    private String activityTitle;
    private String activityBannerUrl;
    private String startTime;
    private String activityPosition;
    private String auditRemark;
    private double activityLon;
    private double activityLat;
    private int signId;
    private int auditStatus;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
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

    public String getActivityPosition() {
        return activityPosition;
    }

    public void setActivityPosition(String activityPosition) {
        this.activityPosition = activityPosition;
    }

    public double getActivityLon() {
        return activityLon;
    }

    public void setActivityLon(double activityLon) {
        this.activityLon = activityLon;
    }

    public double getActivityLat() {
        return activityLat;
    }

    public void setActivityLat(double activityLat) {
        this.activityLat = activityLat;
    }

    public int getSignId() {
        return signId;
    }

    public void setSignId(int signId) {
        this.signId = signId;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getAuditRemark() {
        return auditRemark;
    }
}
