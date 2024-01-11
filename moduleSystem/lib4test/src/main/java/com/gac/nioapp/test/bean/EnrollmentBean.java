package com.gac.nioapp.test.bean;

import java.util.List;

/**
 * description:
 *
 * @author xyz
 * @email 826363590@qq.com
 * created on: 2019/5/21 1:45 PM
 */
public class EnrollmentBean {
    /**
     * id : 1
     * activityType : 1
     * activityTitle : 5.1春游
     * activityBannerUrl : http://www.baidu.com/img.jpg
     * startTime : 2019-05-01 10:00:00
     * activityPosition : 广州市越秀区流花湖公园
     * activityLon : 113.25642
     * activityLat : 23.142021
     * status : 0
     * activityNeedWriteFields : [{"fieldName":"姓名","fieldType":1,"fieldValue":""}]
     * ticketList : [{"ticketName":"单人票","ticketType":0,"ticketPrice":"500","remark":"双人票打","ticketId":1}]
     * remark : 活动温馨提示：需要自带水哦
     */

    private int id;
    private int activityType;
    private String activityTitle;
    private String activityBannerUrl;
    private String newActivityBannerUrl;
    private String startTime;
    private String endTime;
    private String signTime; //报名截止时间【3.2.5.5】
    private String activityPosition;
    private double activityLon;
    private double activityLat;
    private int status;
    private int auditStatus;
    private String remark;
    private List<ActivityNeedWriteFieldsBean> activityNeedWriteFields;
    private List<TicketListBean> ticketList;

    public String getNewActivityBannerUrl() {
        return newActivityBannerUrl;
    }

    public void setNewActivityBannerUrl(String newActivityBannerUrl) {
        this.newActivityBannerUrl = newActivityBannerUrl;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
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

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public List<ActivityNeedWriteFieldsBean> getActivityNeedWriteFields() {
        return activityNeedWriteFields;
    }

    public void setActivityNeedWriteFields(List<ActivityNeedWriteFieldsBean> activityNeedWriteFields) {
        this.activityNeedWriteFields = activityNeedWriteFields;
    }

    public List<TicketListBean> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketListBean> ticketList) {
        this.ticketList = ticketList;
    }

    /*public static class ActivityNeedWriteFieldsBean {
     */

    /**
     * fieldName : 姓名
     * fieldType : 1
     * fieldValue :
     *//*

        private String fieldName;
        private int fieldType;
        private String fieldValue;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public int getFieldType() {
            return fieldType;
        }

        public void setFieldType(int fieldType) {
            this.fieldType = fieldType;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }
    }*/

    public static class TicketListBean {
        /**
         * ticketName : 单人票
         * ticketType : 0
         * ticketPrice : 500
         * remark : 双人票打
         * ticketId : 1
         */

        private String ticketName;
        private int ticketType;
        private String ticketPrice;
        private String remark;
        private int ticketId;

        public String getTicketName() {
            return ticketName;
        }

        public void setTicketName(String ticketName) {
            this.ticketName = ticketName;
        }

        public int getTicketType() {
            return ticketType;
        }

        public void setTicketType(int ticketType) {
            this.ticketType = ticketType;
        }

        public String getTicketPrice() {
            return ticketPrice;
        }

        public void setTicketPrice(String ticketPrice) {
            this.ticketPrice = ticketPrice;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getTicketId() {
            return ticketId;
        }

        public void setTicketId(int ticketId) {
            this.ticketId = ticketId;
        }
    }
}
