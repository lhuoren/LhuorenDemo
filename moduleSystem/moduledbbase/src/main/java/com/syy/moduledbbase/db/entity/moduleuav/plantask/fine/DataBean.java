package com.syy.moduledbbase.db.entity.moduleuav.plantask.fine;

import org.greenrobot.greendao.annotation.Convert;

import java.io.Serializable;
import java.util.List;

public class DataBean {
    /**
     * waypointlist : [{"camtarpos":{"lng":113.41336349784005,"alt":80.11207516091605,"lat":23.172915624850027},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗甲线 右地线挂点","uavpos":{"lng":113.41333710582299,"alt":80.11207516091605,"lat":23.172877657638402}},{"camtarpos":{"lng":113.41334540209772,"alt":75.40895061950684,"lat":23.172929253668816},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 上相小号侧I串","uavpos":{"lng":113.4133190100824,"alt":75.40895061950684,"lat":23.172891286454}},{"camtarpos":{"lng":113.41338751761698,"alt":75.4061000869751,"lat":23.17290288396145},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 上相大号侧I串","uavpos":{"lng":113.41336112559634,"alt":75.4061000869751,"lat":23.172864916754154}},{"camtarpos":{"lng":113.41333602333823,"alt":72.02774982910157,"lat":23.17291814721172},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 中相小号侧I串","uavpos":{"lng":113.41330963132789,"alt":72.02774982910157,"lat":23.172880179994877}},{"camtarpos":{"lng":113.41338634098305,"alt":71.97150021057129,"lat":23.172892756675992},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 中相大号侧I串","uavpos":{"lng":113.41335994896504,"alt":71.97150021057129,"lat":23.17285478946826}},{"camtarpos":{"lng":113.4133378913482,"alt":68.58864956359864,"lat":23.17292598528031},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 下相小号侧I串","uavpos":{"lng":113.41331149933562,"alt":68.58864956359864,"lat":23.172888018063986}},{"camtarpos":{"lng":113.41339041861445,"alt":68.42330008010865,"lat":23.17289686946641},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 下相大号侧I串","uavpos":{"lng":113.4133640265944,"alt":68.42330008010865,"lat":23.17285890225954}},{"camtarpos":null,"gimbalpitch":29.1736507144453,"flength":24,"takephoto":false,"aircraftyaw":31.778084624392854,"tarobjname":null,"uavpos":{"lng":113.41333147704931,"alt":85.5239,"lat":23.172866150879017}},{"camtarpos":null,"gimbalpitch":23.905901407459563,"flength":24,"takephoto":false,"aircraftyaw":211.77808462433202,"tarobjname":null,"uavpos":{"lng":113.41343829153432,"alt":85.5239,"lat":23.173019812659568}},{"camtarpos":{"lng":113.41339082580019,"alt":80.06249981384278,"lat":23.172949643563854},"gimbalpitch":30.160488797391334,"flength":24,"takephoto":true,"aircraftyaw":210.63113205969904,"tarobjname":"500kV增穗甲线 左地线挂点","uavpos":{"lng":113.41341657231655,"alt":82.98861123898158,"lat":23.172988364927697}},{"camtarpos":{"lng":113.41342369614011,"alt":75.5674999282837,"lat":23.172960054457153},"gimbalpitch":1.3898428017007624E-5,"flength":24,"takephoto":true,"aircraftyaw":197.03316485396923,"tarobjname":"500kV增穗甲线 上相大号侧I串","uavpos":{"lng":113.41343940698017,"alt":75.56750119380504,"lat":23.173004803806652}},{"camtarpos":{"lng":113.41338403748064,"alt":75.50870018463135,"lat":23.172983300851204},"gimbalpitch":-1.349934664190961E-5,"flength":24,"takephoto":true,"aircraftyaw":232.48268600797354,"tarobjname":"500kV增穗甲线 上相小号侧I串","uavpos":{"lng":113.41343021267501,"alt":75.50869879538399,"lat":23.17301496845968}},{"camtarpos":{"lng":113.41343318519782,"alt":72.06125002365113,"lat":23.172968031621274},"gimbalpitch":1.6221699278780337E-5,"flength":24,"takephoto":true,"aircraftyaw":194.81365037334592,"tarobjname":"500kV增穗甲线 中相大号侧I串","uavpos":{"lng":113.41344693912082,"alt":72.06125149059838,"lat":23.173013078521755}},{"camtarpos":{"lng":113.41338713281101,"alt":72.04685001831055,"lat":23.172991217610118},"gimbalpitch":-9.545479552082122E-6,"flength":24,"takephoto":true,"aircraftyaw":232.08495102967788,"tarobjname":"500kV增穗甲线 中相小号侧I串","uavpos":{"lng":113.41342942381168,"alt":72.04684911308183,"lat":23.173020644340035}},{"camtarpos":{"lng":113.41338175106097,"alt":68.62495022277832,"lat":23.172988683457845},"gimbalpitch":-1.6511196510205472E-5,"flength":24,"takephoto":true,"aircraftyaw":234.0638264485006,"tarobjname":"500kV增穗甲线 下相小号侧I串","uavpos":{"lng":113.41342645125897,"alt":68.62494861371019,"lat":23.17301760650564}},{"camtarpos":{"lng":113.41343298556939,"alt":68.56364994506836,"lat":23.172960981341824},"gimbalpitch":1.8899085298673496E-5,"flength":24,"takephoto":true,"aircraftyaw":191.6395977662458,"tarobjname":"500kV增穗甲线 下相大号侧I串","uavpos":{"lng":113.41344439501991,"alt":68.56365171220781,"lat":23.17300821300898}},{"camtarpos":null,"gimbalpitch":41.557773358804255,"flength":24,"takephoto":false,"aircraftyaw":211.77808462433202,"tarobjname":null,"uavpos":{"lng":113.41343829153432,"alt":90.5239,"lat":23.173019812659568}}]
     * lng : 113.41337875104445
     * topalt : 80.5239
     * name : N2耐张塔单元
     * alt : 42.986000000000004
     * towerid : 266114e26c2f35cb13105fa57eac17e6
     * lat : 23.172934158624873
     */

    private double lng;
    private double topalt;
    private String name;
    private double alt;
    private String towerid;
    private double lat;
    public boolean isSkip;
    @Convert(columnType = String.class, converter = WaypointlistBeanConverter.class)
    private List<WaypointlistBean> waypointlist;

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

    public List<WaypointlistBean> getWaypointlist() {
        return waypointlist;
    }

    public void setWaypointlist(List<WaypointlistBean> waypointlist) {
        this.waypointlist = waypointlist;
    }

}
