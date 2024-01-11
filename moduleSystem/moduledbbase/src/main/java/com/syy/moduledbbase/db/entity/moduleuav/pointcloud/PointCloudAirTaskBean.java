package com.syy.moduledbbase.db.entity.moduleuav.pointcloud;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class PointCloudAirTaskBean implements Serializable {
    private static final long serialVersionUID = 156231455229512313L;

    @Id(autoincrement = true)
    private Long id;
    private int missiontype;
    @Unique
    private String airLineTaskId;
    private String airlineversion;
    private int taskMode;
    private int flyType;
    private double flySpeed;
    private double workSpeed;
    private double backSpeed;
    private double takeOffHeight;
    private int backMode;
    private int surroundDistance;
    private double rectifyLatitude;
    private double rectifyLongitude;
    private double rectifyAlt;
    @Convert(columnType = String.class, converter = PointCloudDataBeanConverter.class)
    private List<PointCloudDataBean> waypoint;
    private Date createDate;
    @Generated(hash = 1896642584)
    public PointCloudAirTaskBean(Long id, int missiontype, String airLineTaskId,
            String airlineversion, int taskMode, int flyType, double flySpeed,
            double workSpeed, double backSpeed, double takeOffHeight, int backMode,
            int surroundDistance, double rectifyLatitude, double rectifyLongitude,
            double rectifyAlt, List<PointCloudDataBean> waypoint, Date createDate) {
        this.id = id;
        this.missiontype = missiontype;
        this.airLineTaskId = airLineTaskId;
        this.airlineversion = airlineversion;
        this.taskMode = taskMode;
        this.flyType = flyType;
        this.flySpeed = flySpeed;
        this.workSpeed = workSpeed;
        this.backSpeed = backSpeed;
        this.takeOffHeight = takeOffHeight;
        this.backMode = backMode;
        this.surroundDistance = surroundDistance;
        this.rectifyLatitude = rectifyLatitude;
        this.rectifyLongitude = rectifyLongitude;
        this.rectifyAlt = rectifyAlt;
        this.waypoint = waypoint;
        this.createDate = createDate;
    }
    @Generated(hash = 935912559)
    public PointCloudAirTaskBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getMissiontype() {
        return this.missiontype;
    }
    public void setMissiontype(int missiontype) {
        this.missiontype = missiontype;
    }
    public String getAirLineTaskId() {
        return this.airLineTaskId;
    }
    public void setAirLineTaskId(String airLineTaskId) {
        this.airLineTaskId = airLineTaskId;
    }
    public String getAirlineversion() {
        return this.airlineversion;
    }
    public void setAirlineversion(String airlineversion) {
        this.airlineversion = airlineversion;
    }
    public int getTaskMode() {
        return this.taskMode;
    }
    public void setTaskMode(int taskMode) {
        this.taskMode = taskMode;
    }
    public int getFlyType() {
        return this.flyType;
    }
    public void setFlyType(int flyType) {
        this.flyType = flyType;
    }
    public double getFlySpeed() {
        return this.flySpeed;
    }
    public void setFlySpeed(double flySpeed) {
        this.flySpeed = flySpeed;
    }
    public double getWorkSpeed() {
        return this.workSpeed;
    }
    public void setWorkSpeed(double workSpeed) {
        this.workSpeed = workSpeed;
    }
    public double getBackSpeed() {
        return this.backSpeed;
    }
    public void setBackSpeed(double backSpeed) {
        this.backSpeed = backSpeed;
    }
    public double getTakeOffHeight() {
        return this.takeOffHeight;
    }
    public void setTakeOffHeight(double takeOffHeight) {
        this.takeOffHeight = takeOffHeight;
    }
    public int getBackMode() {
        return this.backMode;
    }
    public void setBackMode(int backMode) {
        this.backMode = backMode;
    }
    public int getSurroundDistance() {
        return this.surroundDistance;
    }
    public void setSurroundDistance(int surroundDistance) {
        this.surroundDistance = surroundDistance;
    }
    public double getRectifyLatitude() {
        return this.rectifyLatitude;
    }
    public void setRectifyLatitude(double rectifyLatitude) {
        this.rectifyLatitude = rectifyLatitude;
    }
    public double getRectifyLongitude() {
        return this.rectifyLongitude;
    }
    public void setRectifyLongitude(double rectifyLongitude) {
        this.rectifyLongitude = rectifyLongitude;
    }
    public double getRectifyAlt() {
        return this.rectifyAlt;
    }
    public void setRectifyAlt(double rectifyAlt) {
        this.rectifyAlt = rectifyAlt;
    }
    public List<PointCloudDataBean> getWaypoint() {
        return this.waypoint;
    }
    public void setWaypoint(List<PointCloudDataBean> waypoint) {
        this.waypoint = waypoint;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
