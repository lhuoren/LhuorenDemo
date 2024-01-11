package com.gac.nioapp.test.bean;

import java.util.List;

/**
 * description:
 *
 * @author xyz
 * @email 826363590@qq.com
 * created on: 2019/5/23 9:20 AM
 */
public class UserSignBean {
    /**
     * signId : 1
     * activityType : 1
     * activityTitle : 5.1春游
     * activityBannerUrl : http://www.baidu.com/img.jpg
     * startTime : 2019-05-01 10:00:00
     * activityPosition : 广州市越秀区流花湖公园
     * activityLon : 113.25642
     * activityLat : 23.142021
     * status : 0
     * activityNeedWriteFields : [{"fieldName":"姓名","fieldType":1,"fieldValue":"刘德华"}]
     * remark : 活动温馨提示：需要自带水哦
     * endTime : 2019-05-01 23:00:00
     * activityPayScore : 500
     * regisScore : 1000
     * ticketPayNum : 1
     * activityId : 1
     */

    private int signId;
    private int activityType;
    private String activityTitle;
    private String activityBannerUrl;
    private String newActivityBannerUrl;
    private String startTime;
    private String activityPosition;
    private double activityLon;
    private double activityLat;
    private int status;
    private String remark;
    private String endTime;
    private int activityPayScore;
    private int regisScore;
    private String ticketPayNum;
    private int auditStatus;
    private int activityId;
    private List<ActivityNeedWriteFieldsBean> activityNeedWriteFields;

    public int getSignId() {
        return signId;
    }

    public void setSignId(int signId) {
        this.signId = signId;
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

    public String getNewActivityBannerUrl() {
        return newActivityBannerUrl;
    }

    public void setNewActivityBannerUrl(String newActivityBannerUrl) {
        this.newActivityBannerUrl = newActivityBannerUrl;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getActivityPayScore() {
        return activityPayScore;
    }

    public void setActivityPayScore(int activityPayScore) {
        this.activityPayScore = activityPayScore;
    }

    public int getRegisScore() {
        return regisScore;
    }

    public void setRegisScore(int regisScore) {
        this.regisScore = regisScore;
    }

    public String getTicketPayNum() {
        return ticketPayNum;
    }

    public void setTicketPayNum(String ticketPayNum) {
        this.ticketPayNum = ticketPayNum;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public List<ActivityNeedWriteFieldsBean> getActivityNeedWriteFields() {
        return activityNeedWriteFields;
    }

    public void setActivityNeedWriteFields(List<ActivityNeedWriteFieldsBean> activityNeedWriteFields) {
        this.activityNeedWriteFields = activityNeedWriteFields;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }
}
