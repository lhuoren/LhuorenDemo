package com.syy.moduledbbase.db.entity.moduleuav;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class FineStep implements Serializable{

    private static final long serialVersionUID = -9099172303841460418L;
    @Id(autoincrement = true)
    private Long id;

    private int sort;
    private String lineName;
    private String towerName;
    private String targetName;
    private double slantingAngle;
    private double flyYaw;
    private double latitude;
    private double longitude;
    private double altitude;
    private double homeLatitude;
    private double homeLongitude;
    private double type;
    @Generated(hash = 469089278)
    public FineStep(Long id, int sort, String lineName, String towerName,
                    String targetName, double slantingAngle, double flyYaw, double latitude,
                    double longitude, double altitude, double homeLatitude,
                    double homeLongitude, double type) {
        this.id = id;
        this.sort = sort;
        this.lineName = lineName;
        this.towerName = towerName;
        this.targetName = targetName;
        this.slantingAngle = slantingAngle;
        this.flyYaw = flyYaw;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.homeLatitude = homeLatitude;
        this.homeLongitude = homeLongitude;
        this.type = type;
    }
    @Generated(hash = 1138019533)
    public FineStep() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getSort() {
        return this.sort;
    }
    public void setSort(int sort) {
        this.sort = sort;
    }
    public String getLineName() {
        return this.lineName;
    }
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }
    public String getTowerName() {
        return this.towerName;
    }
    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }
    public String getTargetName() {
        return this.targetName;
    }
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
    public double getSlantingAngle() {
        return this.slantingAngle;
    }
    public void setSlantingAngle(double slantingAngle) {
        this.slantingAngle = slantingAngle;
    }
    public double getFlyYaw() {
        return this.flyYaw;
    }
    public void setFlyYaw(double flyYaw) {
        this.flyYaw = flyYaw;
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
    public double getAltitude() {
        return this.altitude;
    }
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    public double getHomeLatitude() {
        return this.homeLatitude;
    }
    public void setHomeLatitude(double homeLatitude) {
        this.homeLatitude = homeLatitude;
    }
    public double getHomeLongitude() {
        return this.homeLongitude;
    }
    public void setHomeLongitude(double homeLongitude) {
        this.homeLongitude = homeLongitude;
    }
    public double getType() {
        return this.type;
    }
    public void setType(double type) {
        this.type = type;
    }

}
