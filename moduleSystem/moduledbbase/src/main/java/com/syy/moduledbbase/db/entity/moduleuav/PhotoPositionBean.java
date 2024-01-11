package com.syy.moduledbbase.db.entity.moduleuav;

public class PhotoPositionBean {

    /**
     * towerNumber : N1耐张塔单元
     * altitude : 6.071899890899658
     * latitude : 23.139153210645993
     * name : 35kV荔高线 左地线挂点
     * logicTowerId : 03120005616929
     * longitude : 113.22631481610944
     */

    private String towerNumber;
    private double altitude;
    private double latitude;
    private String name;
    private String logicTowerId;
    private double longitude;
    private String actionType;

    public String getTowerNumber() {
        return towerNumber;
    }

    public void setTowerNumber(String towerNumber) {
        this.towerNumber = towerNumber;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogicTowerId() {
        return logicTowerId;
    }

    public void setLogicTowerId(String logicTowerId) {
        this.logicTowerId = logicTowerId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
