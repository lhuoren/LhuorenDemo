package com.syy.moduledbbase.db.entity.modulecommon;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Entity
public class FlyRecordDetail implements Serializable {

    private static final long serialVersionUID = 248139138137689953L;

    @Id(autoincrement = true)
    private Long id;

    private Long recordId;

    private double latitude;

    private double longitude;

    private double altitude;

    private double distance;

    private float horizontalSpeed;

    private float verticalSpeed;

    private int batteryLevel;

    private String gpsMode;

    private double angle;

    private Date createDate;

    private int gpsSatelliteCount;

    private int leftStickVerticalPos;

    private int leftStickHorizontalPos;

    private int rightStickVerticalPos;

    private int rightStickHorizontalPos;

    private double rtkLatitude;

    private double rtkLongitude;

    private double rtkAltitude;

    private boolean interrupt;

    private String rtkStatus;

    @Generated(hash = 762965626)
    public FlyRecordDetail(Long id, Long recordId, double latitude,
            double longitude, double altitude, double distance,
            float horizontalSpeed, float verticalSpeed, int batteryLevel,
            String gpsMode, double angle, Date createDate, int gpsSatelliteCount,
            int leftStickVerticalPos, int leftStickHorizontalPos,
            int rightStickVerticalPos, int rightStickHorizontalPos,
            double rtkLatitude, double rtkLongitude, double rtkAltitude,
            boolean interrupt, String rtkStatus) {
        this.id = id;
        this.recordId = recordId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.distance = distance;
        this.horizontalSpeed = horizontalSpeed;
        this.verticalSpeed = verticalSpeed;
        this.batteryLevel = batteryLevel;
        this.gpsMode = gpsMode;
        this.angle = angle;
        this.createDate = createDate;
        this.gpsSatelliteCount = gpsSatelliteCount;
        this.leftStickVerticalPos = leftStickVerticalPos;
        this.leftStickHorizontalPos = leftStickHorizontalPos;
        this.rightStickVerticalPos = rightStickVerticalPos;
        this.rightStickHorizontalPos = rightStickHorizontalPos;
        this.rtkLatitude = rtkLatitude;
        this.rtkLongitude = rtkLongitude;
        this.rtkAltitude = rtkAltitude;
        this.interrupt = interrupt;
        this.rtkStatus = rtkStatus;
    }

    @Generated(hash = 23544253)
    public FlyRecordDetail() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public float getHorizontalSpeed() {
        return this.horizontalSpeed;
    }

    public void setHorizontalSpeed(float horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    public float getVerticalSpeed() {
        return this.verticalSpeed;
    }

    public void setVerticalSpeed(float verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getGpsMode() {
        return this.gpsMode;
    }

    public void setGpsMode(String gpsMode) {
        this.gpsMode = gpsMode;
    }

    public double getAngle() {
        return this.angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getGpsSatelliteCount() {
        return this.gpsSatelliteCount;
    }

    public void setGpsSatelliteCount(int gpsSatelliteCount) {
        this.gpsSatelliteCount = gpsSatelliteCount;
    }

    public int getLeftStickVerticalPos() {
        return this.leftStickVerticalPos;
    }

    public void setLeftStickVerticalPos(int leftStickVerticalPos) {
        this.leftStickVerticalPos = leftStickVerticalPos;
    }

    public int getLeftStickHorizontalPos() {
        return this.leftStickHorizontalPos;
    }

    public void setLeftStickHorizontalPos(int leftStickHorizontalPos) {
        this.leftStickHorizontalPos = leftStickHorizontalPos;
    }

    public int getRightStickVerticalPos() {
        return this.rightStickVerticalPos;
    }

    public void setRightStickVerticalPos(int rightStickVerticalPos) {
        this.rightStickVerticalPos = rightStickVerticalPos;
    }

    public int getRightStickHorizontalPos() {
        return this.rightStickHorizontalPos;
    }

    public void setRightStickHorizontalPos(int rightStickHorizontalPos) {
        this.rightStickHorizontalPos = rightStickHorizontalPos;
    }

    public double getRtkLatitude() {
        return this.rtkLatitude;
    }

    public void setRtkLatitude(double rtkLatitude) {
        this.rtkLatitude = rtkLatitude;
    }

    public double getRtkLongitude() {
        return this.rtkLongitude;
    }

    public void setRtkLongitude(double rtkLongitude) {
        this.rtkLongitude = rtkLongitude;
    }

    public double getRtkAltitude() {
        return this.rtkAltitude;
    }

    public void setRtkAltitude(double rtkAltitude) {
        this.rtkAltitude = rtkAltitude;
    }

    public boolean getInterrupt() {
        return this.interrupt;
    }

    public void setInterrupt(boolean interrupt) {
        this.interrupt = interrupt;
    }

    public String getRtkStatus() {
        return this.rtkStatus;
    }

    public void setRtkStatus(String rtkStatus) {
        this.rtkStatus = rtkStatus;
    }
    

}
