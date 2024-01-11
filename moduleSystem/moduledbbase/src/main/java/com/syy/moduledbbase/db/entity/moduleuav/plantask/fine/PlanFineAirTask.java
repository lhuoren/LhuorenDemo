package com.syy.moduledbbase.db.entity.moduleuav.plantask.fine;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PlanFineAirTask implements Serializable {

    private static final long serialVersionUID = 156741115229543478L;

    /**
     * airlinetype : DETAILEDAIRLINE
     * data : [{"waypointlist":[{"camtarpos":{"lng":113.41336349784005,"alt":80.11207516091605,"lat":23.172915624850027},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗甲线 右地线挂点","uavpos":{"lng":113.41333710582299,"alt":80.11207516091605,"lat":23.172877657638402}},{"camtarpos":{"lng":113.41334540209772,"alt":75.40895061950684,"lat":23.172929253668816},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 上相小号侧I串","uavpos":{"lng":113.4133190100824,"alt":75.40895061950684,"lat":23.172891286454}},{"camtarpos":{"lng":113.41338751761698,"alt":75.4061000869751,"lat":23.17290288396145},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 上相大号侧I串","uavpos":{"lng":113.41336112559634,"alt":75.4061000869751,"lat":23.172864916754154}},{"camtarpos":{"lng":113.41333602333823,"alt":72.02774982910157,"lat":23.17291814721172},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 中相小号侧I串","uavpos":{"lng":113.41330963132789,"alt":72.02774982910157,"lat":23.172880179994877}},{"camtarpos":{"lng":113.41338634098305,"alt":71.97150021057129,"lat":23.172892756675992},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 中相大号侧I串","uavpos":{"lng":113.41335994896504,"alt":71.97150021057129,"lat":23.17285478946826}},{"camtarpos":{"lng":113.4133378913482,"alt":68.58864956359864,"lat":23.17292598528031},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 下相小号侧I串","uavpos":{"lng":113.41331149933562,"alt":68.58864956359864,"lat":23.172888018063986}},{"camtarpos":{"lng":113.41339041861445,"alt":68.42330008010865,"lat":23.17289686946641},"gimbalpitch":0,"flength":24,"takephoto":true,"aircraftyaw":31.778084624052255,"tarobjname":"500kV增穗乙线 下相大号侧I串","uavpos":{"lng":113.4133640265944,"alt":68.42330008010865,"lat":23.17285890225954}},{"camtarpos":null,"gimbalpitch":29.1736507144453,"flength":24,"takephoto":false,"aircraftyaw":31.778084624392854,"tarobjname":null,"uavpos":{"lng":113.41333147704931,"alt":85.5239,"lat":23.172866150879017}},{"camtarpos":null,"gimbalpitch":23.905901407459563,"flength":24,"takephoto":false,"aircraftyaw":211.77808462433202,"tarobjname":null,"uavpos":{"lng":113.41343829153432,"alt":85.5239,"lat":23.173019812659568}},{"camtarpos":{"lng":113.41339082580019,"alt":80.06249981384278,"lat":23.172949643563854},"gimbalpitch":30.160488797391334,"flength":24,"takephoto":true,"aircraftyaw":210.63113205969904,"tarobjname":"500kV增穗甲线 左地线挂点","uavpos":{"lng":113.41341657231655,"alt":82.98861123898158,"lat":23.172988364927697}},{"camtarpos":{"lng":113.41342369614011,"alt":75.5674999282837,"lat":23.172960054457153},"gimbalpitch":1.3898428017007624E-5,"flength":24,"takephoto":true,"aircraftyaw":197.03316485396923,"tarobjname":"500kV增穗甲线 上相大号侧I串","uavpos":{"lng":113.41343940698017,"alt":75.56750119380504,"lat":23.173004803806652}},{"camtarpos":{"lng":113.41338403748064,"alt":75.50870018463135,"lat":23.172983300851204},"gimbalpitch":-1.349934664190961E-5,"flength":24,"takephoto":true,"aircraftyaw":232.48268600797354,"tarobjname":"500kV增穗甲线 上相小号侧I串","uavpos":{"lng":113.41343021267501,"alt":75.50869879538399,"lat":23.17301496845968}},{"camtarpos":{"lng":113.41343318519782,"alt":72.06125002365113,"lat":23.172968031621274},"gimbalpitch":1.6221699278780337E-5,"flength":24,"takephoto":true,"aircraftyaw":194.81365037334592,"tarobjname":"500kV增穗甲线 中相大号侧I串","uavpos":{"lng":113.41344693912082,"alt":72.06125149059838,"lat":23.173013078521755}},{"camtarpos":{"lng":113.41338713281101,"alt":72.04685001831055,"lat":23.172991217610118},"gimbalpitch":-9.545479552082122E-6,"flength":24,"takephoto":true,"aircraftyaw":232.08495102967788,"tarobjname":"500kV增穗甲线 中相小号侧I串","uavpos":{"lng":113.41342942381168,"alt":72.04684911308183,"lat":23.173020644340035}},{"camtarpos":{"lng":113.41338175106097,"alt":68.62495022277832,"lat":23.172988683457845},"gimbalpitch":-1.6511196510205472E-5,"flength":24,"takephoto":true,"aircraftyaw":234.0638264485006,"tarobjname":"500kV增穗甲线 下相小号侧I串","uavpos":{"lng":113.41342645125897,"alt":68.62494861371019,"lat":23.17301760650564}},{"camtarpos":{"lng":113.41343298556939,"alt":68.56364994506836,"lat":23.172960981341824},"gimbalpitch":1.8899085298673496E-5,"flength":24,"takephoto":true,"aircraftyaw":191.6395977662458,"tarobjname":"500kV增穗甲线 下相大号侧I串","uavpos":{"lng":113.41344439501991,"alt":68.56365171220781,"lat":23.17300821300898}},{"camtarpos":null,"gimbalpitch":41.557773358804255,"flength":24,"takephoto":false,"aircraftyaw":211.77808462433202,"tarobjname":null,"uavpos":{"lng":113.41343829153432,"alt":90.5239,"lat":23.173019812659568}}],"lng":113.41337875104445,"topalt":80.5239,"name":"N2耐张塔单元","alt":42.986000000000004,"towerid":"266114e26c2f35cb13105fa57eac17e6","lat":23.172934158624873},{"waypointlist":[{"camtarpos":{"lng":113.41886907802541,"alt":86.06089954833985,"lat":23.17378320454973},"gimbalpitch":23.000000000622933,"flength":24,"takephoto":true,"aircraftyaw":349.05326886937667,"tarobjname":"500kV增穗甲线 右地线挂点","uavpos":{"lng":113.41887687646617,"alt":88.01455519078621,"lat":23.173742290729923}},{"camtarpos":{"lng":113.41886890687151,"alt":81.8145998046875,"lat":23.17377069394068},"gimbalpitch":23.000000000622933,"flength":24,"takephoto":true,"aircraftyaw":349.05326886937667,"tarobjname":"500kV增穗乙线 上相I串","uavpos":{"lng":113.418876705312,"alt":83.76825544713387,"lat":23.173729780120887}},{"camtarpos":{"lng":113.41887054098451,"alt":78.53890018920899,"lat":23.173766540460147},"gimbalpitch":23.000000000622933,"flength":24,"takephoto":true,"aircraftyaw":349.05326886937667,"tarobjname":"500kV增穗乙线 中相I串","uavpos":{"lng":113.4188783394244,"alt":80.49255583165535,"lat":23.17372562664032}},{"camtarpos":{"lng":113.41886847970663,"alt":74.66100155145914,"lat":23.17377018909965},"gimbalpitch":23.000000000622933,"flength":24,"takephoto":true,"aircraftyaw":349.05326886937667,"tarobjname":"500kV增穗乙线 下相I串","uavpos":{"lng":113.41887627814724,"alt":76.6146571939055,"lat":23.17372927527987}},{"camtarpos":null,"gimbalpitch":30.67887240937063,"flength":24,"takephoto":false,"aircraftyaw":349.0532688698584,"tarobjname":null,"uavpos":{"lng":113.41887916605066,"alt":91.524,"lat":23.173722118260116}},{"camtarpos":null,"gimbalpitch":30.312405715562587,"flength":24,"takephoto":false,"aircraftyaw":169.0532688695113,"tarobjname":null,"uavpos":{"lng":113.41885039496566,"alt":91.524,"lat":23.173873063107166}},{"camtarpos":{"lng":113.41886071076408,"alt":86.1142995880127,"lat":23.17381311899552},"gimbalpitch":23.000000000622933,"flength":24,"takephoto":true,"aircraftyaw":169.05326886937664,"tarobjname":"500kV增穗甲线 左地线挂点","uavpos":{"lng":113.41885291231631,"alt":88.06795523045906,"lat":23.173854032814944}},{"camtarpos":{"lng":113.4188591566608,"alt":81.84390049438477,"lat":23.17382370296493},"gimbalpitch":23.000000000622933,"flength":24,"takephoto":true,"aircraftyaw":169.05326886937664,"tarobjname":"500kV增穗甲线 上相I串","uavpos":{"lng":113.41885135821224,"alt":83.79755613683113,"lat":23.173864616784314}},{"camtarpos":{"lng":113.41885898765034,"alt":78.34845047454834,"lat":23.173828448228967},"gimbalpitch":23.000000000622933,"flength":24,"takephoto":true,"aircraftyaw":169.05326886937664,"tarobjname":"500kV增穗甲线 中相I串","uavpos":{"lng":113.4188511892016,"alt":80.30210611699471,"lat":23.17386936204835}},{"camtarpos":{"lng":113.41885949142815,"alt":75.02970009307862,"lat":23.17382436901199},"gimbalpitch":23.000000000622933,"flength":24,"takephoto":true,"aircraftyaw":169.05326886937664,"tarobjname":"500kV增穗甲线 下相I串","uavpos":{"lng":113.41885169297969,"alt":76.98335573552498,"lat":23.173865282831386}},{"camtarpos":null,"gimbalpitch":49.46220014637171,"flength":24,"takephoto":false,"aircraftyaw":169.0532688695113,"tarobjname":null,"uavpos":{"lng":113.41885039496566,"alt":96.524,"lat":23.173873063107166}}],"lng":113.41886488572315,"topalt":86.524,"name":"N3耐张塔单元","alt":46.073,"towerid":"8d443f06b4c5ec7fc01aa11c5bae7dda","lat":23.17379703871505},{"waypointlist":[{"camtarpos":{"lng":113.41985457460808,"alt":71.20930021209716,"lat":23.17393068105894},"gimbalpitch":23.000000000543615,"flength":24,"takephoto":true,"aircraftyaw":347.6160612094748,"tarobjname":"500kV增穗甲线 右地线挂点","uavpos":{"lng":113.41986348021261,"alt":73.16295585454353,"lat":23.17388996091759}},{"camtarpos":{"lng":113.41982999476316,"alt":66.81870000762939,"lat":23.173914869694173},"gimbalpitch":23.000000000543615,"flength":24,"takephoto":true,"aircraftyaw":347.6160612094748,"tarobjname":"500kV增穗乙线 上相小号侧I串","uavpos":{"lng":113.41983890037471,"alt":68.77235565007575,"lat":23.173874149553605}},{"camtarpos":{"lng":113.41983124996933,"alt":63.42070000572205,"lat":23.17390065263899},"gimbalpitch":23.000000000543682,"flength":24,"takephoto":true,"aircraftyaw":347.6160612094748,"tarobjname":"500kV增穗乙线 中相小号侧I串","uavpos":{"lng":113.41984015557998,"alt":65.37435564816842,"lat":23.17385993249839}},{"camtarpos":{"lng":113.41982567392306,"alt":59.88739996356964,"lat":23.173907378212636},"gimbalpitch":23.000000000543682,"flength":24,"takephoto":true,"aircraftyaw":347.6160612094748,"tarobjname":"500kV增穗乙线 下相小号侧I串","uavpos":{"lng":113.41983457953569,"alt":61.84105560601601,"lat":23.1738666580722}},{"camtarpos":null,"gimbalpitch":26.441267567106554,"flength":24,"takephoto":false,"aircraftyaw":347.6160612087057,"tarobjname":null,"uavpos":{"lng":113.41986839806754,"alt":76.59490000000001,"lat":23.173861478145135}},{"camtarpos":null,"gimbalpitch":26.49290657104693,"flength":24,"takephoto":false,"aircraftyaw":167.61606120894058,"tarobjname":null,"uavpos":{"lng":113.4198295332389,"alt":76.59490000000001,"lat":23.17403918427241}},{"camtarpos":{"lng":113.41984626538596,"alt":71.21309965057372,"lat":23.173970958416195},"gimbalpitch":23.000000000543615,"flength":24,"takephoto":true,"aircraftyaw":167.61606120947485,"tarobjname":"500kV增穗甲线 左地线挂点","uavpos":{"lng":113.41983735977314,"alt":73.16675529302009,"lat":23.17401167855708}},{"camtarpos":{"lng":113.4198178892106,"alt":66.86889999313354,"lat":23.173981231201626},"gimbalpitch":23.000000000543615,"flength":24,"takephoto":true,"aircraftyaw":167.61606120947485,"tarobjname":"500kV增穗甲线 上相小号侧I串","uavpos":{"lng":113.4198089835887,"alt":68.8225556355799,"lat":23.17402195134164}},{"camtarpos":{"lng":113.41981274287075,"alt":63.323100022506715,"lat":23.173989125597554},"gimbalpitch":23.000000000543682,"flength":24,"takephoto":true,"aircraftyaw":167.61606120947485,"tarobjname":"500kV增穗甲线 中相小号侧I串","uavpos":{"lng":113.41980383724696,"alt":65.27675566495309,"lat":23.17402984573741}},{"camtarpos":{"lng":113.41980935334297,"alt":59.85164993209839,"lat":23.173981863960186},"gimbalpitch":23.000000000543682,"flength":24,"takephoto":true,"aircraftyaw":167.61606120947485,"tarobjname":"500kV增穗甲线 下相小号侧I串","uavpos":{"lng":113.41980044771839,"alt":61.80530557454476,"lat":23.174022584099934}},{"camtarpos":null,"gimbalpitch":44.909734024824985,"flength":24,"takephoto":false,"aircraftyaw":167.61606120894052,"tarobjname":null,"uavpos":{"lng":113.4198295332389,"alt":81.59490000000001,"lat":23.17403918427241}}],"lng":113.41984894371453,"topalt":71.59490000000001,"name":"N4直线塔单元","alt":43.9393,"towerid":"21a4fc209de2bcf267cee2cb01029fc5","lat":23.173950431568134}]
     * name : 航线33
     * id : 58d4239a53caaa750e7b5cce7ca6c7cb
     * airlineversion : 3a60bed155ae4eb0a5dd4640be174398
     */

    @Id(autoincrement = true)
    private Long nId;
    private String airlinetype;
    private String name;
    private String id;
    private String airlineversion;
    private int fromtype;//0:SpliceLineAndTowerUtil 拼接航线 1:SelectLineAndTowerUtil 选择航线
    @Convert(columnType = String.class, converter = DataBeanConverter.class)
    private List<DataBean> data;
    @Generated(hash = 197885052)
    public PlanFineAirTask(Long nId, String airlinetype, String name, String id, String airlineversion, int fromtype, List<DataBean> data) {
        this.nId = nId;
        this.airlinetype = airlinetype;
        this.name = name;
        this.id = id;
        this.airlineversion = airlineversion;
        this.fromtype = fromtype;
        this.data = data;
    }
    @Generated(hash = 1803322434)
    public PlanFineAirTask() {
    }
    public Long getNId() {
        return this.nId;
    }
    public void setNId(Long nId) {
        this.nId = nId;
    }
    public String getAirlinetype() {
        return this.airlinetype;
    }
    public void setAirlinetype(String airlinetype) {
        this.airlinetype = airlinetype;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAirlineversion() {
        return this.airlineversion;
    }
    public void setAirlineversion(String airlineversion) {
        this.airlineversion = airlineversion;
    }
    public int getFromtype() {
        return this.fromtype;
    }
    public void setFromtype(int fromtype) {
        this.fromtype = fromtype;
    }
    public List<DataBean> getData() {
        return this.data;
    }
    public void setData(List<DataBean> data) {
        this.data = data;
    }



}
