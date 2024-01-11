package com.syy.moduledbbase.db.entity.moduleuav.panorama;

import org.greenrobot.greendao.annotation.Convert;

public class PanoramaDataBean {
    /**
     * lng : 113.2262658849664
     * topalt : 3.983900012397923
     * name : N1
     * alt : -8.743099999999686
     * towerid : 5d0f066e212543dbb006fa9a2bf69e36
     * waypoints : {"big2little":[{"camtarpos":{"lng":113.22627391515958,"alt":3.983900012397923,"lat":23.139524185478695},"gimbalpitch":0.010000000000005116,"flength":24,"turningmode":"FIXEDTURN","name":"塔顶左侧","takephoto":true,"aircraftyaw":0,"photoinfolist":null,"uavpos":{"lng":113.22627391515958,"alt":7.983900012397923,"lat":23.139524185478695}}],"little2big":[{"camtarpos":{"lng":113.22625785477382,"alt":3.983900012397923,"lat":23.139513919731574},"gimbalpitch":0.010000000000005116,"flength":24,"turningmode":"FIXEDTURN","name":"塔顶右侧","takephoto":true,"aircraftyaw":0,"photoinfolist":null,"uavpos":{"lng":113.22625785477382,"alt":7.983900012397923,"lat":23.139513919731574}}]}
     * lat : 23.139519052605365
     */

    private double lng;
    private double topalt;
    private String name;
    private double alt;
    private String towerid;
    private double lat;
    @Convert(columnType = String.class, converter = WaypointBeanConverter.class)
    private WaypointsBean waypoints;

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


    /**
     * little2big : [{"name":"塔顶","uavpos":{"lng":113.22618937281635,"lat":23.13953725493251,"alt":24.646900000000002},"camtarpos":{"lng":113.22618937281635,"lat":23.13953725493251,"alt":14.6469},"turningmode":"FIXEDTURN","takephoto":true,"aircraftyaw":0,"flength":24,"gimbalpitch":0.010000000000005116,"photoinfolist":null},{"name":"跨域障碍物点","uavpos":{"lng":113.22620005173704,"lat":23.13943617697338,"alt":25.8199},"camtarpos":{"lng":113.22620005173704,"lat":23.13943617697338,"alt":15.8199},"turningmode":"FIXEDTURN","takephoto":true,"aircraftyaw":0,"flength":24,"gimbalpitch":0.010000000000005116,"photoinfolist":null}]
     * big2little : [{"name":"跨域障碍物点","uavpos":{"lng":113.22620005173704,"lat":23.13943617697338,"alt":25.8199},"camtarpos":{"lng":113.22620005173704,"lat":23.13943617697338,"alt":15.8199},"turningmode":"FIXEDTURN","takephoto":true,"aircraftyaw":0,"flength":24,"gimbalpitch":0.010000000000005116,"photoinfolist":null},{"name":"塔顶","uavpos":{"lng":113.22618937281635,"lat":23.13953725493251,"alt":24.646900000000002},"camtarpos":{"lng":113.22618937281635,"lat":23.13953725493251,"alt":14.6469},"turningmode":"FIXEDTURN","takephoto":true,"aircraftyaw":0,"flength":24,"gimbalpitch":0.010000000000005116,"photoinfolist":null}]
     * towerLongitude : 113.226189
     * towerLatitude : 23.139537
     * towerAltitude : 14.6469
     * towerFoot : -8.7431
     */

//    private double towerLongitude;
//    private double towerLatitude;
//    private double towerAltitude;
//    private double towerFoot;
//    @Convert(columnType = String.class, converter = Little2bigBeanConverter.class)
//    private List<Little2bigBean> little2big;
//    @Convert(columnType = String.class, converter = Big2littleBeanConverter.class)
//    private List<Big2littleBean> big2little;
//
//    public double getTowerLongitude() {
//        return towerLongitude;
//    }
//
//    public void setTowerLongitude(double towerLongitude) {
//        this.towerLongitude = towerLongitude;
//    }
//
//    public double getTowerLatitude() {
//        return towerLatitude;
//    }
//
//    public void setTowerLatitude(double towerLatitude) {
//        this.towerLatitude = towerLatitude;
//    }
//
//    public double getTowerAltitude() {
//        return towerAltitude;
//    }
//
//    public void setTowerAltitude(double towerAltitude) {
//        this.towerAltitude = towerAltitude;
//    }
//
//    public double getTowerFoot() {
//        return towerFoot;
//    }
//
//    public void setTowerFoot(double towerFoot) {
//        this.towerFoot = towerFoot;
//    }
//
//    public List<Little2bigBean> getLittle2big() {
//        return little2big;
//    }
//
//    public void setLittle2big(List<Little2bigBean> little2big) {
//        this.little2big = little2big;
//    }
//
//    public List<Big2littleBean> getBig2little() {
//        return big2little;
//    }
//
//    public void setBig2little(List<Big2littleBean> big2little) {
//        this.big2little = big2little;
//    }



}