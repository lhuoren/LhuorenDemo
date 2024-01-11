package com.syy.moduledbbase.db.entity.moduleuav.plantask.channel;

public class DataBean {
    /**
     * lng : 112.17492023259874
     * topalt : 312.4999
     * name : N01杆塔单元
     * alt : 267.48990000000003
     * towerid : 3de2423d26a5ef07acfc6bacfbb3d27f
     * waypoints : {"big2little":[{"camtarpos":{"lng":112.17492023259874,"alt":312.4999,"lat":24.700211299153647},"gimbalpitch":0.010000000000005116,"flength":24,"turningmode":"FIXEDTURN","name":"塔顶","takephoto":true,"aircraftyaw":0,"photoinfolist":null,"uavpos":{"lng":112.17492023259874,"alt":337.4999,"lat":24.700211299153647}}],"little2big":[{"camtarpos":{"lng":112.17492023259874,"alt":312.4999,"lat":24.700211299153647},"gimbalpitch":0.010000000000005116,"flength":24,"turningmode":"FIXEDTURN","name":"塔顶","takephoto":true,"aircraftyaw":0,"photoinfolist":null,"uavpos":{"lng":112.17492023259874,"alt":337.4999,"lat":24.700211299153647}}]}
     * lat : 24.700211299153647
     */

    private double lng;
    private double topalt;
    private String name;
    private double alt;
    private String towerid;
    private WaypointsBean waypoints;
    private double lat;
    public boolean isSkip;

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getTopalt() {
        return topalt;
    }

    public void setTopalt(double topalt) {
        this.topalt = topalt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public String getTowerid() {
        return towerid;
    }

    public void setTowerid(String towerid) {
        this.towerid = towerid;
    }

    public WaypointsBean getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(WaypointsBean waypoints) {
        this.waypoints = waypoints;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public boolean isSkip() {
        return isSkip;
    }

    public void setSkip(boolean skip) {
        isSkip = skip;
    }

}
