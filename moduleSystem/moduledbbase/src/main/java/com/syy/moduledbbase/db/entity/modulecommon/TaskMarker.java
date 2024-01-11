package com.syy.moduledbbase.db.entity.modulecommon;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by smilelu on 2018/4/16.
 */
@Entity
public class TaskMarker implements Serializable{

    private static final long serialVersionUID = -593141355978450439L;

    @Id(autoincrement = true)
    private Long id;

    private Long taskId;

    private int markerType;

    private double latitude;

    private double longitude;

    private double flightHeight; //航高（可变行高时记录航高）

    @Generated(hash = 1675846492)
    public TaskMarker(Long id, Long taskId, int markerType, double latitude,
            double longitude, double flightHeight) {
        this.id = id;
        this.taskId = taskId;
        this.markerType = markerType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.flightHeight = flightHeight;
    }

    @Generated(hash = 1164816568)
    public TaskMarker() {
    }

    @Override
    public String toString() {
        return "TaskMarker{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", markerType=" + markerType +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", flightHeight=" + flightHeight +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public int getMarkerType() {
        return this.markerType;
    }

    public void setMarkerType(int markerType) {
        this.markerType = markerType;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getFlightHeight() {
        return this.flightHeight;
    }

    public void setFlightHeight(double flightHeight) {
        this.flightHeight = flightHeight;
    }
}
