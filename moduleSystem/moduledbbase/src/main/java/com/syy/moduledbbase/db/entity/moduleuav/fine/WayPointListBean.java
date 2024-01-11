package com.syy.moduledbbase.db.entity.moduleuav.fine;


import com.syy.moduledbbase.db.entity.moduleuav.PhotoPositionBean;
import com.syy.moduledbbase.db.entity.moduleuav.PhotoPositionBeanConverter;

import org.greenrobot.greendao.annotation.Convert;

import java.util.List;

public class WayPointListBean {
    /**
     * gimbalPitch : 33.585777281983646
     * aircraftLocationLongitude : 113.22633432526627
     * flength : 24
     * waypointType : 1
     * aircraftLocationAltitude : 14.461766483268764
     * aircraftYaw : -68.16871282446982
     * photoPositionList : []
     * aircraftLocationLatitude : 23.13915315804063
     */

    private double gimbalPitch;
    private double aircraftLocationLongitude;
    private int flength;
    private int waypointType;
    private double aircraftLocationAltitude;
    private double aircraftYaw;
    private double aircraftLocationLatitude;
    private int heading;
    private String turnMode;
    private double slantingAngle;
    @Convert(columnType = String.class, converter = PhotoPositionBeanConverter.class)
    private List<PhotoPositionBean> photoPositionList;

    public double getGimbalPitch() {
//        if (gimbalPitch > 90) {
//            return 90D - gimbalPitch;
//        } else {
//            return gimbalPitch;
//        }
        return gimbalPitch;
    }

    public void setGimbalPitch(double gimbalPitch) {
        this.gimbalPitch = gimbalPitch;
    }

    public double getAircraftLocationLongitude() {
        return aircraftLocationLongitude;
    }

    public void setAircraftLocationLongitude(double aircraftLocationLongitude) {
        this.aircraftLocationLongitude = aircraftLocationLongitude;
    }

    public int getFlength() {
        return flength;
    }

    public void setFlength(int flength) {
        this.flength = flength;
    }

    public int getWaypointType() {
        return waypointType;
    }

    public void setWaypointType(int waypointType) {
        this.waypointType = waypointType;
    }

    public double getAircraftLocationAltitude() {
        return aircraftLocationAltitude;
    }

    public void setAircraftLocationAltitude(double aircraftLocationAltitude) {
        this.aircraftLocationAltitude = aircraftLocationAltitude;
    }

    public double getAircraftYaw() {
        if (aircraftYaw > 180) {
            return aircraftYaw - 360;
        } else {
            return aircraftYaw;
        }
    }

    public void setAircraftYaw(double aircraftYaw) {
        this.aircraftYaw = aircraftYaw;
    }

    public double getAircraftLocationLatitude() {
        return aircraftLocationLatitude;
    }

    public void setAircraftLocationLatitude(double aircraftLocationLatitude) {
        this.aircraftLocationLatitude = aircraftLocationLatitude;
    }

    public List<PhotoPositionBean> getPhotoPositionList() {
        return photoPositionList;
    }

    public void setPhotoPositionList(List<PhotoPositionBean> photoPositionList) {
        this.photoPositionList = photoPositionList;
    }

    public int getHeading() {
        if (heading > 180) {
            return heading - 360;
        } else {
            return heading;
        }
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public String getTurnMode() {
        return turnMode;
    }

    public void setTurnMode(String turnMode) {
        this.turnMode = turnMode;
    }

    public double getSlantingAngle() {
        return slantingAngle;
    }

    public void setSlantingAngle(double slantingAngle) {
        this.slantingAngle = slantingAngle;
    }

}
