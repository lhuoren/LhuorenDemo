package com.syy.moduledbbase.db.entity.moduleuav.fine;

import org.greenrobot.greendao.annotation.Convert;

import java.util.List;

public class FineDataBean {
    /**
     * waypointList : [{"gimbalPitch":33.585777281983646,"aircraftLocationLongitude":113.22633432526627,"flength":24,"waypointType":1,"aircraftLocationAltitude":14.461766483268764,"aircraftYaw":-68.16871282446982,"photoPositionList":[],"aircraftLocationLatitude":23.13915315804063},{"gimbalPitch":57.32774915959776,"aircraftLocationLongitude":113.22633617641391,"flength":24,"waypointType":0,"aircraftLocationAltitude":7.4752619990114075,"aircraftYaw":-91.42530401787457,"photoPositionList":[{"towerNumber":"N1耐张塔单元","altitude":6.071899890899658,"latitude":23.139153210645993,"name":"35kV荔高线 左地线挂点","logicTowerId":"03120005616929","longitude":113.22631481610944}],"aircraftLocationLatitude":23.13915340029461},{"gimbalPitch":66.78590134775547,"aircraftLocationLongitude":113.2263364451484,"flength":24,"waypointType":0,"aircraftLocationAltitude":6.726402500873513,"aircraftYaw":-96.34074468392697,"photoPositionList":[{"towerNumber":"N1耐张塔单元","altitude":5.774399995803833,"latitude":23.139152722113465,"name":"35kV荔高线 左相I串","logicTowerId":"03120005616929","longitude":113.22631487596854}],"aircraftLocationLatitude":23.139154630671896},{"gimbalPitch":75.98206237326643,"aircraftLocationLongitude":113.22633656279238,"flength":24,"waypointType":0,"aircraftLocationAltitude":6.685384226149238,"aircraftYaw":-102.80336998844922,"photoPositionList":[{"towerNumber":"N1耐张塔单元","altitude":6.113900184631348,"latitude":23.139172253557394,"name":"35kV荔高线 右地线挂点","logicTowerId":"03120005616929","longitude":113.22631470046831}],"aircraftLocationLatitude":23.139176524550944},{"gimbalPitch":54.20931848580398,"aircraftLocationLongitude":113.22633568751577,"flength":24,"waypointType":0,"aircraftLocationAltitude":7.405727326672041,"aircraftYaw":-100.95380614537436,"photoPositionList":[{"towerNumber":"N1耐张塔单元","altitude":5.789400100708008,"latitude":23.13917175173252,"name":"35kV荔高线 右相I串","logicTowerId":"03120005616929","longitude":113.22631414099676}],"aircraftLocationLatitude":23.139175293414254},{"gimbalPitch":62.42399103389415,"aircraftLocationLongitude":113.2263053634329,"flength":24,"waypointType":0,"aircraftLocationAltitude":7.0043080006649845,"aircraftYaw":-113.18423744179228,"photoPositionList":[{"towerNumber":"N1耐张塔单元","altitude":5.592900037765503,"latitude":23.139184453507237,"name":"35kV荔高线 上相I串","logicTowerId":"03120005616929","longitude":113.22628095695592}],"aircraftLocationLatitude":23.139193714668878},{"gimbalPitch":22.86861252763964,"aircraftLocationLongitude":113.22630216979866,"flength":24,"waypointType":1,"aircraftLocationAltitude":14.826105668073655,"aircraftYaw":-144.01563564708889,"photoPositionList":[],"aircraftLocationLatitude":23.139193473110016}]
     * workgroup : workgroup测试$
     * aircraftName : M210 RTK
     * creator : creator测试$
     * createdTime : 2019-02-25 12:28:56
     * company : company测试$
     * airRouteType  : 0
     * cameraName : X5S
     */

    private String workgroup;
    private String aircraftName;
    private String creator;
    private String createdTime;
    private String company;
    private int airRouteType;
    private String cameraName;
    private float flightSpeed;
    private String finishedAction;
    private String headingMode;
    private double towerLongitude;
    private double towerLatitude;
    private double towerAltitude;
    @Convert(columnType = String.class, converter = WayPointListBeanConverter.class)
    private List<WayPointListBean> waypointList;

    public String getWorkgroup() {
        return workgroup;
    }

    public void setWorkgroup(String workgroup) {
        this.workgroup = workgroup;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getAirRouteType() {
        return airRouteType;
    }

    public void setAirRouteType(int airRouteType) {
        this.airRouteType = airRouteType;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public float getFlightSpeed() {
        return flightSpeed;
    }

    public void setFlightSpeed(float flightSpeed) {
        this.flightSpeed = flightSpeed;
    }

    public String getFinishedAction() {
        return finishedAction;
    }

    public void setFinishedAction(String finishedAction) {
        this.finishedAction = finishedAction;
    }

    public String getHeadingMode() {
        return headingMode;
    }

    public void setHeadingMode(String headingMode) {
        this.headingMode = headingMode;
    }

    public double getTowerLongitude() {
        return towerLongitude;
    }

    public void setTowerLongitude(double towerLongitude) {
        this.towerLongitude = towerLongitude;
    }

    public double getTowerLatitude() {
        return towerLatitude;
    }

    public void setTowerLatitude(double towerLatitude) {
        this.towerLatitude = towerLatitude;
    }

    public double getTowerAltitude() {
        return towerAltitude;
    }

    public void setTowerAltitude(double towerAltitude) {
        this.towerAltitude = towerAltitude;
    }

    public List<WayPointListBean> getWaypointList() {
        return waypointList;
    }

    public void setWaypointList(List<WayPointListBean> waypointList) {
        this.waypointList = waypointList;
    }
}
