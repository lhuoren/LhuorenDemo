package com.syy.modulebase.eventbus;

/**
 * Created by lhuor on 2017/11/9.
 */

public class EventBusCode {
    // EventBus Code
    public static final class EventCode {
        public static final int DETAILAIRLINE = 0x111111; //精细化航线
        public static final int SAPNAIRLINE = 0x111112; //通道航线

        public static final int TAKEOFF = 0x111113; //起飞
        public static final int DOCHECKSTATE = 0x111114; //检查完成
        public static final int PAUSETASK = 0x111115; //暂停任务
        public static final int RESUMETASK = 0x111116; //重新执行任务
        public static final int DOBACKHOME = 0x111117; //返航


        //fragmet_title
        public static final int FIMEFRAGMENT = 0x111118; //精细
        public static final int CHANNELFRAGMENT = 0x111119; //通道
        public static final int POINTCLOUDFRAGMENT = 0x111120; //点云
        public static final int REMOTEFRAGMENT = 0x111121; //远程起飞


        public static final int DOWNTASK = 0x111122; //远程起飞

        public static final int CHANGEWIDGETSIZE = 0x111123; //改变控件大小

        public static final int SETAIRCRAFTLOCATION = 0x111124; //

        public static final int CONNECTIVITYCHANGE = 0x111125; //

        public static final int SETPRODUCT = 0x111126; //

        public static final int FLIGHTCONTROLLERSTATE = 0x111127; //flightControllerState

        public static final int AIRCRAFT_CONNECT = 0x111128; //
        public static final int FLIGHT_STATE = 0x111129; //
        public static final int GIMBAL_STATE = 0x111130; //
        public static final int CAMERA_MEDIA = 0x111131; //
        public static final int CAMERA_STATE = 0x111132; //
        public static final int BATTER_STATE = 0x111133; //
        public static final int FLY_STATE = 0x111134; //
        public static final int REMOTE_CONTROLLER_STATE = 0x111135; //

        public static final int MEDIAFILE = 0x111136; //mediaFile

        public static final int STARTDOFLY = 0x111137; //开始作业

        public static final int CHANGEWORKMODE = 0x111138; //changeMode
        public static final int CHANGETRANSMISSIONMODE = 0x111139; //changeTransmissionMode

        public static final int DOWNAIRPHOTOTHENUPIMAGE = 0x111140; //下载无人机照片并上次

        public static final int UNZIPAIRLINE = 0x111141; //解压uav航线

        public static final int OPENORCLOSEREMOTEFLY = 0x111142; //远程起飞开关

        public static final int PLAN_DETAILAIRLINE = 0x111143; //精细化航线

        public static final int PLAN_SAPNAIRLINE = 0x111144; //通道航线
        public static final int CHANGEMAPTYPE = 0x111145; //切换地图

        public static final int DJILIVEVIDEOINFO = 0x111146; //大疆实时推流比特率等信息

        public static final int RESTARTSTREAM = 0x111147; //大疆实时推流断图传重新开始推流

        public static final int AIRSTATUSWAIT = 0x111148; //远程起飞当状态不对的时候设置为wait状态

        public static final int FAULTFRAGMENT = 0x1111249; //faultFragment 故障巡检
        public static final int PANORAMAFRAGMENT = 0x111150; //panoramaFragment 通道全景巡检
        public static final int COLLECTIONFRAGMENT = 0x111151; //CollectionFragment 杆塔采集

        public static final int RTKFIXEDUNLOCK = 0x111152;

        public static final int KML_PANORAMA = 0x111153;

        public static final int PANORAMAMISSIONFINISH = 0x111154;

        public static final int KML_COLLECTION = 0x111155;

        public static final int KML_POINTCLOUD = 0x111156;

        public static final int SHOWSETTINGPARAMPOPUWINDOW = 0x111157;

        public static final int DISMISSSETTINGPARAMPOPUWINDOW = 0x111158;

        public static final int DOWNAPPPROGRESS = 0x111159;
    }

}
