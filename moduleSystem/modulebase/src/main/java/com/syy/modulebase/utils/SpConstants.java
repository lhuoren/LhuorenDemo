package com.syy.modulebase.utils;

//import com.mapbox.mapboxsdk.geometry.LatLng;
//import com.syy.modulecommon.domian.entity.User;

import com.mapbox.mapboxsdk.geometry.LatLng;

/**
 * Created by smilelu on 2018/04/15.
 */
public class SpConstants {

    public static final int START_PAGE_NUM = 1;

    public static final int PER_PAGE_NUMBER = 3;

//    public static User user;

    //广州花都测试线
    //public static LatLng defaultLocation = new LatLng(23.33364244993486d, 113.111189040162d);
    //佛山恒州甲乙线
    //public static LatLng defaultLocation = new LatLng(23.0660161345855d, 112.8305028045046d);
    //佛山三兴线线
    //public static LatLng defaultLocation = new LatLng(22.87482456565482,112.8216541635313);
    //肇庆砚橄甲乙线
    //public static LatLng defaultLocation = new LatLng(23.02350225096468,112.6697881260089);
    //机巡中心
    public static LatLng defaultLocation = new LatLng(23.141578492460777d, 113.22773637197713d);
    public static String GoogleMapUrl = "http://mt2.google.cn/vt/lyrs=m@167000000&hl=zh-CN&gl=cn&x={x}&y={y}&z={z}&s=Galil";

    public static String OpenCycleMapUrl = "http://c.tile.thunderforest.com/cycle/{z}/{x}/{y}.png?apikey=c8f53a40ca6248ae904880dfde726c8d";

    public static String SatelliteAMapUrl = "http://wprd03.is.autonavi.com/appmaptile?style=6&x={x}&y={y}&z={z}&lang=zh_cn&size=1&scl=1";

//    public static String SatelliteAMapUrl = "http://wprd03.is.autonavi.com/appmaptile?style=6&x={x}&y={y}&z={z}&lang=zh_cn&size=1&scl=1";

    public static String SatelliteGoogleMapUrl = "http://mt2.google.cn/vt/lyrs=s,m@167000000&hl=zh-CN&gl=cn&x={x}&y={y}&z={z}&s=Galil";

    public static String AMapUrl = "http://wprd03.is.autonavi.com/appmaptile?style=7&x={x}&y={y}&z={z}&lang=zh_cn&size=1&scl=1";
//    public static String AMapUrl = "http://webrd01.is.autonavi.com/appmaptile?x=54658&y=26799&z=16&lang=zh_cn&size=1&scale=1&style=8";
//public static String AMapUrl = "https://webst01.is.autonavi.com/appmaptile?x={x}y={y}&z={z}&style=6";

    public static String OpenStreetMapUrl = "http://a.tile.openstreetmap.org/{z}/{x}/{y}.png";

//    public static String datamapMapUrl = "http://10.150.79.186:80/datamap-server/v2/satelitetilelayer/MapServer/tile/{z}/{y}/{x}";

    public static String datamapMapUrl = getRootUrl() + "/datamap-server/v2/satelitetilelayer/MapServer/tile/{z}/{y}/{x}";

    /**
     * 任务状态
     */
    public static class PatrolTaskStatus {
        public static final int UNDONE = 0;
        public static final int DONE = 1;

        public static String patrolTaskStatusDesc(int status) {
            String statusDesc = "";
            switch (status) {
                case UNDONE:
                    statusDesc = "未完成";
                    break;
                case DONE:
                    statusDesc = "已完成";
                    break;
            }
            return statusDesc;
        }
    }

    public static class RecordRequest {
        public static final int RECORD_REQUEST_CODE = 101;
        public static final int STORAGE_REQUEST_CODE = 102;
        public static final int AUDIO_REQUEST_CODE = 103;
    }

    public static class BundleName {
        public static final String SHOWPHOTO = "showPhoto";
        public static final String SHOWPHOTOFROMEXE = "TaskExecutionActivity";
        public static final String SHOWPHOTOFROMTASKMANAGER = "AirlineTaskLineAdapter";
        public static final String FLYTASKWORKID = "flyTaskWorkId";
        public static final String FLYTASKWORKCRUISEID = "flyTaskWorkCruiseId";
        public static final String RECORDID = "RECORD_ID";
        public static final String FARGETID = "fargetId";

        public static final String BUNDLEUAVX = "bundleUavx";
        public static final String BUNDLEPLAN = "bundlePlan";
        public static final String BUNDLEKML = "bundleKml";

        public static final String TOZIPFILEPATH = "toZipFilePath";
        public static final String FILEPATH = "filePath";
        public static final String KMLFILEPATH = "kmlFilePath";
        public static final String DATASOURCE = "dataSource";

        public static final String FROMTASKTYPE = "fromFlyTaskType";

    }

    public static class DataSource {
        public static final String FORMUAVX = "formUavx";
        public static final String FORMPLAN = "formPlan";
        public static final String FORMKML = "formKml";
    }

    public static class EventBusMapName {
        public static final String KMLFILEPATH = "kmlFilePath";
        public static final String UAVXFILEPATH = "uavxFilePath";
        public static final String TOZIPFILEPATH = "toZipFilePath";
        public static final String LIVEVIDEORESOLUTION = "liveVideoResolution";
        public static final String LIVEVIDEOBITRATE = "liveVideoBitRate";
        public static final String LIVEVIDEOFPS = "liveVideoFps";
    }

    public class ActivityRequest {
        public static final int SANC_REQUEST_CODE = 101;
        public static final int SANC_REQUEST_BIND_CODE = 102;
    }

    public static class SharedName {
        public static final String IS_ACTEDIT = "is_acredit";
        public static final String AT = "access_token";
        public static final String RT = "refresh_token";
        public static final String UN = "user_name";
        public static final String UP = "user_pwd";

        public static final String USER = "user";
        public static final String IS_LIVE = "is_live";
        public static final String IS_MAP_MOVE = "sw_map_is_move";
        public static final String IS_OPENCLOSEUPDATEIMAGE = "OpenCloseUpdateImage";

        public static final String LIVE_ADDRESS = "live_address";
        public static final String LIVE_PORT = "live_port";
        public static final String LIVE_RESOLUTION = "live_resolution";

        public static final String BUREAUNAME = "bureauName";
        public static final String BUREAUCODE = "bureauCode";
        public static final String BUREAUCODENAME = "bureauCodName";
        public static final String ISVPNLOGIN = "isVpnLogin";
        public static final String INFRARED = "Infrared";
        public static final String PERMISSION = "permission";
        public static final String LAND_POINT = "land_point";
        public static final String UAV_TRACKER = "uav_tracker";
        public static final String AIRBINDNUM = "air_bind_num";
        public static final String LATITUDE = "Latitude";
        public static final String LONGITUDE = "Longitude";
        public static final String TASKID = "taskId";
        public static final String ISHANDCONTROLTOWER = "IsHandControlTower";

        public static final String RTK_ADDRESS = "rtk_address";
        public static final String RTK_PORT = "rtk_port";
        public static final String RTK_MOUNT_POINT = "rtk_mount_point";
        public static final String RTK_USER = "rtk_user";
        public static final String RTK_PWD = "rtk_pwd";
        public static final String RTK_DYNAMICS_ADDRESS = "rtk_dynamics_address";
        public static final String RTK_DYNAMICS_PORT = "rtk_dynamics_port";
        public static final String RTK_DYNAMICS_MOUNT_POINT = "rtk_dynamics_mount_point";
        public static final String RTK_DYNAMICS_USER = "rtk_dynamics_user";
        public static final String RTK_DYNAMICS_PWD = "rtk_dynamics_pwd";
        public static final String RTK_MODE = "rtk_mode";

        public static final String AIRLINETASKID = "airLineTaskId";
        public static final String AIRLINEVERSION = "airlineVersion";

        public static final String FINEUAVXKMLFILEPATH = "fineUavxKmlFilePath";
        public static final String FINEKMLKMLFILEPATH = "fineKmlkmlFilePath";
        public static final String FINEPLANLINENAME = "finePlanLineName";

        public static final String CHANNELUAVXKMLFILEPATH = "channelUavxkmlFilePath";
        public static final String CHANNELKMLKMLFILEPATH = "channelKmlkmlFilePath";
        public static final String CHANNELPLANLINENAME = "channelPlanLineName";

        public static final String FAULTUAVXKMLFILEPATH = "faultUavxkmlFilePath";
        public static final String FAULTKMLKMLFILEPATH = "faultKmlkmlFilePath";
        public static final String FAULTPLANLINENAME = "faultPlanLineName";

        public static final String PANORAMAUAVXKMLFILEPATH = "panoramaUavxkmlFilePath";
        public static final String PANORAMAKMLKMLFILEPATH = "panoramaKmlkmlFilePath";
        public static final String PANORAMAPLANLINENAME = "panoramaPlanLineName";

        public static final String ISLATLNGPLAN = "isLatLngPlan";
        public static final String FLYHEIGHT = "fly_height";
        public static final String BACKHEIGHT = "back_height";
        public static final String CURPATROLTASK = "cur_patrol_task";
        public static final String ISOUTOFCOMTROL = "is_out_of_control";
        public static final String APPPLANTASKFINISHARGS = "appPlanTaskFinishArgs";
        public static final String CHANNELTASKMODE = "channelTaskMode";
        public static final String CHANNELINSPECTIONPOSITION = "channelInitInspectionPosition";
        public static final String CHANNELBACKMODE = "channelBackMode";
        public static final String CHANNELFLYMODE = "channelFlyMode";
        public static final String CHANNELPATHMODE = "channelPathMode";
        public static final String CHANNELSLANTINGANGLECUTNOW = "channelSlantingAngleCutNow";
        public static final String AIRPREFLIGHTSTATUS = "airPreflightStatus";
        public static final String PLANFINEAIRTASK = "PlanFineAirTask";
        public static final String PLANCHANNELAIRTASK = "PlanChannelAirTask";
        public static final String ISREMOTETAKEOFF = "is_remote_takeoff";
        public static final String DJIACCOUNT = "DJIAccount";
        public static final String PLANTASKLINETOWERS = "planTaskLineTowers";
        public static final String APPTASKARGS = "appTaskArgs";
        public static final String ISCUSTOMBUTTONC1 = "is_custom_button_c1";
        public static final String ISCUSTOMBUTTONC2 = "is_custom_button_c2";
        public static final String FLYRECORDSIZE = "flyRecordSize";
        public static final String RESOLUTION = "resolution";
        public static final String ISREMOTETRANSMISSIONMODE = "is_remote_transmission_mode";
        public static final String ISREMOTEFLYMODE = "is_remote_fly_mode";
        public static final String AIRSTATUS = "airStatus";
        public static final String CHANNELFLYSPEEDNOW = "channelFlySpeedNow";
        public static final String CHANNELFLYDISTANCENOW = "channelFlyDistanceNow";
        public static final String FINETASKMODE = "FineTaskMode";
        public static final String ISSIMPLEVERSION = "is_simple_version";
        public static final String FINEBACKMODE = "FineBackMode";
        public static final String FINEFLYMODE = "FineFlyMode";
        public static final String FAULTBACKSPEEDNOW = "faultBackSpeedNow";
        public static final String LIVEADDRESS = "liveAddress";
        public static final String LASTRECORDDATE = "last_record_date";
        public static final String CURFLYRECORD = "cur_fly_record";
        public static final String MAPTYPE = "map_type";
        public static final String LASTLOCATION = "last_location";
        public static final String ISAUTOOBSTACLE = "is_auto_obstacle";
        public static final String TAKEPHOTONUMBER = "takePhotoNumber";
        public static final String UPDATEPHOTONUMBER = "updatePhotoNumber";
        public static final String INDEXSDTOTALDOWN = "indexSDTotalDown";
        public static final String FLYRECORDCREATEDATE = "flyRecordCreateDate";
        public static final String ISSAFEBACK = "is_safe_back";
        public static final String THEME = "theme";
        public static final String ISFIRSTOPENAPP = "isFirstOpenApp";
        public static final String BASEURLTYPE = "BASEURLTYPE";

        public static final String PANORAMATASKMODE = "panoramaTaskMode";
        public static final String PANORAMABACKMODE = "panoramaBackMode";
        public static final String PANORAMAPATHMODE = "panoramaPathMode";
        public static final String PANORAMAFLYSPEEDNOW = "panoramaFlySpeedNow";
        public static final String PANORAMAFLYDISTANCENOW = "panoramaFlyDistanceNow";
        public static final String PANORAMASLANTINGANGLECUTNOW = "panoramaSlantingAngleCutNow";

        public static final String COLLECTIOMTOWERNUM = "collectionTowerNum";

        public static final String POINTCLOUDTASKMODE = "pointCloudTaskMode";
        public static final String POINTCLOUDFLYHEIGHT = "pointCloudFlyHeight";
        public static final String POINTCLOUDTAKEOFFHEIGHT = "pointCloudTakeOffHeight";
        public static final String POINTCLOUDLANDINGHEIGHT = "pointCloudLandingHeight";
        public static final String POINTCLOUDTOPHEIGHT = "pointCloudTopHeight";

        public static final String TAKEPHOTOTIME = "TakePhotoTime";

        public static final String UNZIPAIRLINEFILEPATH = "UNZIPAIRLINEFILEPATH";
        public static final String UNZIPAIRLINE_UAVXFILEPATH = "UNZIPAIRLINE_UAVXFILEPATH";

        public static final String UNZIPAIRLINE_AIRLINEFILE_TYPE = "UNZIPAIRLINE_AirlineType";

        public static final String RTKSTATE_MOBILESTATIONALTITUDE = "rtkstate_MobileStationAltitude";
    }

    public static class SharedValue {
        public static final String MAPBOXMAP = "MapBoxMap";
        public static final String WAPIMAP = "WapiMap";
        public static final String GOOLEMAP = "GoogleMap";
        public static final String AMAP = "AMap";
        public static final String OPENSTREETMAP = "OpenStreetMap";
        public static final String SATELLITEAMAP = "SatelliteAMap";
        public static final String FOLLOWSYSTEM = "FollowSystem";
        public static final String DARK = "Dark";
        public static final String LIGHT = "Light";

        public static final String FLYBEGINSTART = "起飞";
        public static final String FLYBEGINPAUSE = "暂停";
        public static final String FLYBEGINRESUME = "继续";

        public static final String LOGINTYPEVPN = "VPN";
        public static final String LOGINTYPEAPN = "APN";
        public static final String LOGINTYPEWAPI = "WAPI";
        public static final String LOGINTYPEOFFLINE = "OFFLINE";

    }

    public static class FlyTaskType {
        public static final String FINE = "精细巡检";
        public static final String CHANNEL = "通道巡检";
        public static final String TREEBARRIER = "树障巡检";
        public static final String INFRARED = "红外巡检";
        public static final String FAULT = "故障巡检";
        public static final String AUXILIARY = "手动飞行";
        public static final String COLLECTION = "杆塔采集";
        public static final String POINTCLOUDTO = "点云采集";
    }

    public static class RxJavaType {
//        public static final String OBSERVABLEJUSTONE = "one";

        public static final String BASEACTIVITY_SHOWPROGRESSDIALOG = "BaseActivity_showProgressDialog";
        public static final String BASEACTIVITY_SHOWPROGRESSDIALOG_WITH_TIME = "BaseActivity_showProgressDialogWithTime";
        public static final String BASEACTIVITY_CLOSEPROGRESSDIALOG = "BaseActivity_closeProgressDialog";

        public static final String PANORAMASETTINGPOPIPWINDOW_SETCONTENT = "PanoramaSettingPopupWindow_setContent";
        public static final String PANORAMASETTINGPOPIPWINDOW_SETPROGRESS = "PanoramaSettingPopupWindow_setProgress";

        public static final String UAVPROPUPWINDOW_SETCONTENT = "UavPopupWindow_setContent";
        public static final String UAVPROPUPWINDOW_SETPROGRESS = "UavPopupWindow_setProgress";

        public static final String SELECTLINEANDTOWERUTIL_METWORKREQUEST_GETAIRLINELIST = "SelectLineAndTowerUtil_networkRequest_getGetAirLineList";
        public static final String SELECTLINEANDTOWERUTIL_METWORKREQUEST_GETLINE = "SelectLineAndTowerUtil_networkRequest_getAirLine";
        public static final String SELECTLINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETAIRLINE_ONERROR_DETAILEDAIRLINE = "SelectLineAndTowerUtil_childConvertsMainThread_getGetAirLine_onError_DETAILEDAIRLINE";
        public static final String SELECTLINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETAIRLINE_ONERROR_SPANAIRLINE = "SelectLineAndTowerUtil_childConvertsMainThread_getGetAirLine_onError_SPANAIRLINE";

        public static final String SELECTLINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_SELECTCHILDROUTES = "SelectLineAndTowerUtil_childConvertsMainThread_selectChildRoutes";
        public static final String SELECTLINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETCHILDROUTES = "SelectLineAndTowerUtil_childConvertsMainThread_getChildRoutes";
        public static final String SELECTLINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETAIRLINE_DETAILEDAIRLINE = "SelectLineAndTowerUtil_childConvertsMainThread_getAirLine_DETAILEDAIRLINE";
        public static final String SELECTLINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETAIRLINE_SPANAIRLINE = "SelectLineAndTowerUtil_childConvertsMainThread_getAirLine_SPANAIRLINE";
        public static final String SELECTLINEANDTOWERUTIL_MAINTHREAD_GETCHILDROUTESTHREAD = "SelectLineAndTowerUtil_mianThread_getChildRoutesThread";

        public static final String BACKSYNCSERVICE_CHILDCONVERTSMAINTHREAD_SAVEFLYRECORD = "BackSyncService_childConvertsMainThread_saveFlyRecord";
        public static final String BACKSYNCSERVICE_CHILDCONVERTSMAINTHREAD_GETLASTRECORDDATE = "BackSyncService_childConvertsMainThread_getLastRecordDate";
        public static final String BACKSYNCSERVICE_CHILDCONVERTSMAINTHREAD_UPLOADRECORD = "BackSyncService_childConvertsMainThread_uploadRecord";
        public static final String BACKSYNCSERVICE_CHILDCONVERTSMAINTHREAD_STOPFLYRECORD = "BackSyncService_childConvertsMainThread_stopFlyRecord";
        public static final String BACKSYNCSERVICE_CHILDCONVERTSMAINTHREAD_GETRTKINFO = "BackSyncService_childConvertsMainThread_getRtkInfo";
        public static final String BACKSYNCSERVICE_TIMER_REFRESHTOKENFORTIME = "BackSyncService_timer_refreshTokenForTime";

        public static final String STARTACTIVITY_CHILDTHREAD_GETAPPUPDATEINFO = "StartActivity_childThread_getAppUpdateInfo";
        public static final String STARTACTIVITY_MAINCONVERTSCHILDTHREAD_PREVERNTAGAINRUNONRESUME = "StartActivity_mainConvertsChildThread_preventAgainRunOnResume";
        public static final String STARTACTIVITY_CHILDCONVERTSMAINTHREAD_GETXXPERMISSIONS = "StartActivity_childThread_getXXPermissions";

        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_REPEATUPDATEIMAGE = "LazyFragment_childConvertsMainThread_repeatUpdateImage";
        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTORSTOPSIMULATOR = "LazyFragment_childConvertsMainThread_startOrStopSimulator";
        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_SETCAMERAPOSITION_LOCATION = "LazyFragment_childConvertsMainThread_setCameraPosition_location";
        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_SETCAMERAPOSITION_LOCATION_ZOOM = "LazyFragment_childConvertsMainThread_setCameraPosition_location_zoom";
        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_ONTASKSTART = "LazyFragment_childConvertsMainThread_onTaskStart";
        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_FETCHFILEDATA_SAVE_IMAGEINFO = "LazyFragment_childConvertsMainThread_fetchFileData_save_imageInfo";
        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_UPDATEFINISH = "LazyFragment_childConvertsMainThread_updateFinish";
        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_UPDATEUI = "LazyFragment_childConvertsMainThread_updateUI";
        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTUPDATEIMAGE = "LazyFragment_childConvertsMainThread_startUpdateImage";
        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_RESETNOTH = "LazyFragment_childConvertsMainThread_resetNorth";
        public static final String LAZYFRAGMENT_CHILDCONVERTSMAINTHREAD_RESETNOTH_DELAY = "LazyFragment_childConvertsMainThread_delay";

        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_SELECTTOWER = "FineFragment_childConvertsMainThread_selectTower";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLY = "FineFragment_childConvertsMainThread_startFly";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK = "FineFragment_childConvertsMainThread_startFlyAirToWork";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK_UPLOADWAYPOINTDJI = "FineFragment_childConvertsMainThread_startFlyAirToWork_uploadWayPointDJI";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK_UPLOADMISSIONCALLBACK_FAILED = "FineFragment_childConvertsMainThread_startFlyAirToWork_UploadMissionCallBack_failed";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK_UPLOADMISSIONCALLBACK_START = "FineFragment_childConvertsMainThread_startFlyAirToWork_UploadMissionCallBack_start";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK_UPLOADMISSIONCALLBACK_UPDATEPROGRESS = "FineFragment_childConvertsMainThread_startFlyAirToWork_UploadMissionCallBack_updateProgress";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK_UPLOADMISSIONCALLBACK_FINISH = "FineFragment_childConvertsMainThread_startFlyAirToWork_UploadMissionCallBack_finish";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK_UPLOADMISSIONCALLBACK_CLOSEDIALOG = "FineFragment_childConvertsMainThread_startFlyAirToWork_UploadMissionCallBack_closeDialog";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_LOADTASKKMLSHAPE = "FineFragment_childConvertsMainThread_loadTaskKmlShape";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_INITIALIZETASKDATA = "FineFragment_childConvertsMainThread_initializeTaskData";

        public static final String PLANTODOFRAGMENT_CHILDCONVERTSMAINTHREAD_GETUSERTASK_SUCCESS = "PlanToDoFragment_childConvertsMainThread_getUserTaskSuccess";

        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETURGENTSTOPMODEENABLED_ONSUCCESS = "FlightControlFragment_childConvertsMainThread_getUrgentStopModeEnabled_onSuccess";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETURGENTSTOPMODEENABLED_ONFAILURE = "FlightControlFragment_childConvertsMainThread_getUrgentStopModeEnabled_onFailure";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETURGENTSTOPMODEENABLED_ROCKERINOROUT = "FlightControlFragment_childConvertsMainThread_getUrgentStopModeEnabled_rockerInOrOut";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETURGENTSTOPMODEENABLED_MOINFLIGHTSTOPSALLOWED = "FlightControlFragment_childConvertsMainThread_setUrgentStopModeEnabled_NoIn-flightStopsAllowed";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETMULTIPLEFLIGHTMODEENABLED_ONSUCCESS = "FlightControlFragment_childConvertsMainThread_getMultipleFlightModeEnabled_onSuccess";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETMULTIPLEFLIGHTMODEENABLED_ONFAILURE = "FlightControlFragment_childConvertsMainThread_getMultipleFlightModeEnabled_onFailure";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_SETMULTIPLEFLIGHTMODEENABLED = "FlightControlFragment_childConvertsMainThread_getMultipleFlightModeEnabled_setMultipleFlightModeEnabled";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETAUTOMATICMOVEMENTENABLED_ONSUCCESS = "FlightControlFragment_childConvertsMainThread_getAutomaticMovementEnabled_onSuccess";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETAUTOMATICMOVEMENTENABLED_ONFAILURE = "FlightControlFragment_childConvertsMainThread_getAutomaticMovementEnabled_onFailure";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETCONNECTIONFAILSAFEBEHAVIOR_ONSUCCESS = "FlightControlFragment_childConvertsMainThread_getConnectionFailSafeBehavior_onSuccess";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETCONNECTIONFAILSAFEBEHAVIOR_ONFAILURE = "FlightControlFragment_childConvertsMainThread_getConnectionFailSafeBehavior_onFailure";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETMAXFLIGHTRADIUSLIMITATIONENABLED_ONSUCCESS = "FlightControlFragment_childConvertsMainThread_getMaxFlightRadiusLimitationEnabled_onSuccess";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETMAXFLIGHTRADIUSLIMITATIONENABLED_ONFAILURE = "FlightControlFragment_childConvertsMainThread_getMaxFlightRadiusLimitationEnabled_onFailure";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_SETMAXFLIGHTRADIUSLIMITATIONENABLED_ONSUCCESS = "FlightControlFragment_childConvertsMainThread_setMaxFlightRadiusLimitationEnabled_onSuccess";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GGETMAXFLIGHTRADIUS_ONSUCCESS = "FlightControlFragment_childConvertsMainThread_getMaxFlightRadius_onSuccess";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GGETMAXFLIGHTRADIUS_ONFAILURE = "FlightControlFragment_childConvertsMainThread_getMaxFlightRadius_onFailure";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETMAXFLIGHTHEIGHT_ONSUCCESS = "FlightControlFragment_childConvertsMainThread_getMaxFlightHeight_onSuccess";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETMAXFLIGHTHEIGHT_ONFAILURE = "FlightControlFragment_childConvertsMainThread_getMaxFlightHeight_onFailure";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETGOHOMEHEIGHTINMETERS_ONSUCCESS = "FlightControlFragment_childConvertsMainThread_getGoHomeHeightInMeters_onSuccess";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_GETGOHOMEHEIGHTINMETERS_ONFAILURE = "FlightControlFragment_childConvertsMainThread_getGoHomeHeightInMeters_onFailure";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_SETIMUSTATECALLBACK_ONUPDATE = "FlightControlFragment_childConvertsMainThread_setIMUStateCallback_onUpdate";
        public static final String FLIGHTCONTROLFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTADJUSTCOMPASS = "FlightControlFragment_childConvertsMainThread_StartAdjustCompass";

        public static final String PERCEPTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_GETRTHOBSTACLEAVOIDANCEENABLE_ONSUCCESS = "PerceptionFragment_childConvertsMainThread_getRTHObstacleAvoidanceEnabled_onSuccess";
        public static final String PERCEPTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_GETRTHOBSTACLEAVOIDANCEENABLE_ONFAILURE = "PerceptionFragment_childConvertsMainThread_getRTHObstacleAvoidanceEnabled_onFailure";
        public static final String PERCEPTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_SETRTHOBSTACLEAVOIDANCE = "PerceptionFragment_childConvertsMainThread_setRTHObstacleAvoidanceEnabled";
        public static final String PERCEPTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_GETVISIONASSISTEDPOSITIONINGENABLE_ONSUCCESS = "PerceptionFragment_childConvertsMainThread_getVisionAssistedPositioningEnabled_onSuccess";
        public static final String PERCEPTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_GETVISIONASSISTEDPOSITIONINGENABLE_ONFAILURE = "PerceptionFragment_childConvertsMainThread_getVisionAssistedPositioningEnabled_onFailure";
        public static final String PERCEPTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_SETVISIONASSISTEDPOSITIONINGENABLED = "PerceptionFragment_childConvertsMainThread_setVisionAssistedPositioningEnabled";
        public static final String PERCEPTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_GETCOLLISIONAVOIDANCEENABLED_ONSUCCESS = "PerceptionFragment_childConvertsMainThread_getCollisionAvoidanceEnabled_onSuccess";
        public static final String PERCEPTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_GETCOLLISIONAVOIDANCEENABLED_ONFAILURE = "PerceptionFragment_childConvertsMainThread_getCollisionAvoidanceEnabled_onFailure";
        public static final String PERCEPTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_SETCOLLISIONAVOIDANCEENABLED = "PerceptionFragment_childConvertsMainThread_setCollisionAvoidanceEnabled";

        public static final String UPDATEWAPIIMAGEPANELUTIL_CHILDCONVERTSMAINTHREAD_DOWNAIRIMAGES = "UpdateWapiImagePanelUtil_downAirImages";
        public static final String UPDATEWAPIIMAGEPANELUTIL_CHILDCONVERTSMAINTHREAD_DOWNAIRIMAGES_ONMEDIAFILELISTGETBYLIST = "UpdateWapiImagePanelUtil_childConvertsMainThread_downAirImages_onMediaFileListGetByList";
        public static final String UPDATEWAPIIMAGEPANELUTIL_CHILDCONVERTSMAINTHREAD_DOWNAIRIMAGES_ONMEDIAFILELISTGETBYLIST_FETCHFILEDATA = "UpdateWapiImagePanelUtil_childConvertsMainThread_downAirImages_onMediaFileListGetByList_fetchFileData";
        public static final String UPDATEWAPIIMAGEPANELUTIL_CHILDCONVERTSMAINTHREAD_UPLOADIMAGE = "UpdateWapiImagePanelUtil_childConvertsMainThread_upLoadImage";
        public static final String UPDATEWAPIIMAGEPANELUTIL_CHILDCONVERTSMAINTHREAD_UPIMAGE = "UpdateWapiImagePanelUtil_childConvertsMainThread_upImage";

        public static final String CUSTOMPOPUWINDOW_CHILDCONVERTSMAINTHREAD_SETCONTENT = "CustomPopupWindow_childConvertsMainThread_setContent";
        public static final String CUSTOMPOPUWINDOW_CHILDCONVERTSMAINTHREAD_SETPROGRESS = "CustomPopupWindow_childConvertsMainThread_setProgress";

        public static final String FAULTSETTINGPOPUPWINDOW_CHILDCONVERTSMAINTHREAD_SETCONTENT = "FaultSettingPopupWindow_childConvertsMainThread_setContent";
        public static final String FAULTSETTINGPOPUPWINDOW_CHILDCONVERTSMAINTHREAD_SETPROGRESS = "FaultSettingPopupWindow_childConvertsMainThread_setProgress";

        public static final String POINTCLOUDSETTINGPOPUPWINDOW_CHILDCONVERTSMAINTHREAD_SETCONTENT = "PointCloudSettingPopupWindow_childConvertsMainThread_setContent";
        public static final String POINTCLOUDSETTINGPOPUPWINDOW_CHILDCONVERTSMAINTHREAD_SETPROGRESS = "PointCloudSettingPopupWindow_childConvertsMainThread_setProgress";

        public static final String RTKSTATEPANELUTIL_CHILDCONVERTSMAINTHREAD_INITRTKCHANNELSTATE = "RTKStatePanelUtil_childConvertsMainThread_initRtkChannelState";
        public static final String RTKSTATEPANELUTIL_INTERVAL_REFRESHUI = "RTKStatePanelUtil_interval_refreshUI";
        public static final String RTKSTATEPANELUTIL_CHILDCONVERTSMAINTHREAD_INITRTKCHANNELERROR = "RTKStatePanelUtil_childConvertsMainThread_initRtkChannelError";

        public static final String FINESETTINGPOPUPWINDOW_CHILDCONVERTSMAINTHREAD_SETCONTENT = "FineSettingPopupWindow_childConvertsMainThread_setContent";
        public static final String FINESETTINGPOPUPWINDOW_CHILDCONVERTSMAINTHREAD_SETPROGRESS = "FineSettingPopupWindow_childConvertsMainThread_setProgress";

        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_SELECTTOWER = "ChannelFragment_childConvertsMainThread_selectTower";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK = "ChannelFragment_childConvertsMainThread_startFlyAirToWork";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADWAYPOINTDJI = "ChannelFragment_childConvertsMainThread_uploadWayPointDJI";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_FAILED = "ChannelFragment_childConvertsMainThread_UploadMissionCallBack_failed";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_START = "ChannelFragment_childConvertsMainThread_UploadMissionCallBack_start";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_UPDATEPROGRESS = "ChannelFragment_childConvertsMainThread_UploadMissionCallBack_updateProgress";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_FINISH = "ChannelFragment_childConvertsMainThread_UploadMissionCallBack_finish";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_CLOSEDIALOG = "ChannelFragment_childConvertsMainThread_UploadMissionCallBack_closeDialog";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_LOADTASKKMLSHAPE = "ChannelFragment_childConvertsMainThread_loadTaskKmlShape";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_INITIALIZETASKDATA = "ChannelFragment_childConvertsMainThread_initializeTaskData";

        public static final String PREVIEWVIDEOACTIVITY_CHILDCONVERTSMAINTHREAD_FETCHPREVIEW = "PreviewVideoActivity_childConvertsMainThread_fetchPreview";
        public static final String PREVIEWVIDEOACTIVITY_CHILDCONVERTSMAINTHREAD_FETCHPREVIEW_ADDMEDIAUPDATEDVIDEOPLAYBACKSTATELISTENER = "PreviewVideoActivity_childConvertsMainThread_fetchPreview_addMediaUpdatedVideoPlaybackStateListener";

        public static final String SPLICELINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_INITDATA_REFRESHUI = "SpliceLineAndTowerUtil_childConvertsMainThread_initData_refresh_ui";
        public static final String SPLICELINEANDTOWERUTIL_METWORKREQUEST_GETCHILDROUTES_GETAIRLINELIST = "SpliceLineAndTowerUtil_networkRequest_getChildRoutes_getGetAirLineList";
        public static final String SPLICELINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETCHILDROUTES_GETAIRLINELIST_REFRESHUI = "SpliceLineAndTowerUtil_childConvertsMainThread_getChildRoutes_getGetAirLineList_refresh_ui";
        public static final String SPLICELINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETPUSHLINETASK = "SpliceLineAndTowerUtil_childConvertsMainThread_getPushLineTask";
        public static final String SPLICELINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETAIRLINE = "SpliceLineAndTowerUtil_childConvertsMainThread_getGetAirLine";
        public static final String SPLICELINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETAIRLINE_ONSUCCESS_DETAILEDAIRLINE = "SpliceLineAndTowerUtil_childConvertsMainThread_getGetAirLine_onSuccess_DETAILEDAIRLINE";
        public static final String SPLICELINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETAIRLINE_ONSUCCESS_SPANAIRLINE = "SpliceLineAndTowerUtil_childConvertsMainThread_getGetAirLine_onSuccess_SPANAIRLINE";
        public static final String SPLICELINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETAIRLINE_ONFAILURE = "SpliceLineAndTowerUtil_childConvertsMainThread_getGetAirLine_onFailure";
        public static final String SPLICELINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETAIRLINE_ONERROR_DETAILEDAIRLINE = "SpliceLineAndTowerUtil_childConvertsMainThread_getGetAirLine_onError_DETAILEDAIRLINE";
        public static final String SPLICELINEANDTOWERUTIL_CHILDCONVERTSMAINTHREAD_GETAIRLINE_ONERROR_SPANAIRLINE = "SpliceLineAndTowerUtil_childConvertsMainThread_getGetAirLine_onError_SPANAIRLINE";

        public static final String ACCESSORYAGGREGATIONUTIL_CHILDCONVERTSMAINTHREAD_SPOTLIGHT = "AccessoryAggregationUtil_childConvertsMainThread_spotlight";
        public static final String ACCESSORYAGGREGATIONUTIL_CHILDCONVERTSMAINTHREAD_SPOTLIGHT_ONSUCCESS = "AccessoryAggregationUtil_childConvertsMainThread_spotlight_onSuccess";
        public static final String ACCESSORYAGGREGATIONUTIL_CHILDCONVERTSMAINTHREAD_SPOTLIGHT_ONSUCCESS_ONUPDATE = "AccessoryAggregationUtil_childConvertsMainThread_spotlight_onSuccess_onUpdate";
        public static final String ACCESSORYAGGREGATIONUTIL_CHILDCONVERTSMAINTHREAD_SPOTLIGHT_ONFAILURE = "AccessoryAggregationUtil_childConvertsMainThread_spotlight_onFailure";
        public static final String ACCESSORYAGGREGATIONUTIL_CHILDCONVERTSMAINTHREAD_BEACON_ONSUCCESS = "AccessoryAggregationUtil_childConvertsMainThread_beacon_onSuccess";
        public static final String ACCESSORYAGGREGATIONUTIL_CHILDCONVERTSMAINTHREAD_BEACON_ONFAILURE = "AccessoryAggregationUtil_childConvertsMainThread_beacon_onFailure";

        public static final String CHANNELSETTINGPOPUPWINDOW_CHILDCONVERTSMAINTHREAD_SETCONTENT = "ChannelSettingPopupWindow_childConvertsMainThread_setContent";
        public static final String CHANNELSETTINGPOPUPWINDOW_CHILDCONVERTSMAINTHREAD_SETPROGRESS = "ChannelSettingPopupWindow_childConvertsMainThread_setProgress";

        public static final String INTELLIGENTBATTERYFRAGMENT_CHILDCONVERTSMAINTHREAD_GETLEVEL2CELLVOLTAGETHRESHOLD_ONSUCCESS = "IntelligentBatteryFragment_childConvertsMainThread_getLevel2CellVoltageThreshold_onSuccess";
        public static final String INTELLIGENTBATTERYFRAGMENT_CHILDCONVERTSMAINTHREAD_GETLEVEL2CELLVOLTAGETHRESHOLD_ONFAILURE = "IntelligentBatteryFragment_childConvertsMainThread_getLevel2CellVoltageThreshold_onFailure";
        public static final String INTELLIGENTBATTERYFRAGMENT_CHILDCONVERTSMAINTHREAD_GETSELDISCHARGEINDAYS_ONSUCCESS = "IntelligentBatteryFragment_childConvertsMainThread_getSelfDischargeInDays_onSuccess";
        public static final String INTELLIGENTBATTERYFRAGMENT_CHILDCONVERTSMAINTHREAD_GETSELDISCHARGEINDAYS_ONFAILURE = "IntelligentBatteryFragment_childConvertsMainThread_getSelfDischargeInDays_onFailure";
        public static final String INTELLIGENTBATTERYFRAGMENT_CHILDCONVERTSMAINTHREAD_GETLEVEL1CELLVOLTAGETHRESHOLD_ONSUCCESS = "IntelligentBatteryFragment_childConvertsMainThread_getLevel1CellVoltageThreshold_onSuccess";
        public static final String INTELLIGENTBATTERYFRAGMENT_CHILDCONVERTSMAINTHREAD_GETLEVEL1CELLVOLTAGETHRESHOLD_ONFAILURE = "IntelligentBatteryFragment_childConvertsMainThread_getLevel1CellVoltageThreshold_onFailure";

        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_SELECTTOWER = "FaultFragment_childConvertsMainThread_selectTower";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK = "FaultFragment_childConvertsMainThread_startFlyAirToWork";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK_UPLOADWAYPOITDJI = "FaultFragment_childConvertsMainThread_startFlyAirToWork_uploadWayPointDJI";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_FAILED = "FaultFragment_childConvertsMainThread_UploadMissionCallBack_failed";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_START = "FaultFragment_childConvertsMainThread_UploadMissionCallBack_start";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_UPDATEPROGRESS = "FaultFragment_childConvertsMainThread_UploadMissionCallBack_updateProgress";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_FINISH = "FaultFragment_childConvertsMainThread_UploadMissionCallBack_finish";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_CLOSEDIALOG = "FaultFragment_childConvertsMainThread_UploadMissionCallBack_closeDialog";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_LOADTASKKMLSHAPE = "FaultFragment_childConvertsMainThread_loadTaskKmlShape";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_INITALIALIZETASKDATA = "FaultFragment_childConvertsMainThread_initializeTaskData";

        public static final String PREVIEWPHOTOACTIVITY_CHILDCONVERTSMAINTHREAD_ONCREATE = "PreviewPhotoActivity_childConvertsMainThread_onCreate";
        public static final String PREVIEWPHOTOACTIVITY_CHILDCONVERTSMAINTHREAD_FETCHPREVIEW = "PreviewPhotoActivity_childConvertsMainThread_fetchPreview";
        public static final String PREVIEWPHOTOACTIVITY_CHILDCONVERTSMAINTHREAD_ONBACKPRESSED = "PreviewPhotoActivity_childConvertsMainThread_onBackPressed";

        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_SELECTTOWER = "PanoramaFragment_childConvertsMainThread_selectTower";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_SETSHOOTPHOTOMODE_UPLOADWAYPOITDJI_FAILED = "PanoramaFragment_childConvertsMainThread_setShootPhotoMode_uploadWayPointDJI_failed";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_SETSHOOTPHOTOMODE_UPLOADWAYPOITDJI_TIMELINEMISSION = "PanoramaFragment_childConvertsMainThread_setShootPhotoMode_uploadWayPointDJI_timeLineMission";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADWAYPOITDJI_SETSHOOTPHOTOMODE_DJIERROR = "PanoramaFragment_childConvertsMainThread_uploadWayPointDJI_setShootPhotoMode_djiError";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_SATRTFLYAIRTOWORK = "PanoramaFragment_childConvertsMainThread_startFlyAirToWork";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_FAILED = "PanoramaFragment_childConvertsMainThread_UploadMissionCallBack_failed";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_TIMELINEMISSION_DOTIMELINEMISSIONCALLBACK_FAILED = "PanoramaFragment_childConvertsMainThread_timeLineMission_DoTimeLineMissionCallBack_failed";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_START = "PanoramaFragment_childConvertsMainThread_UploadMissionCallBack_start";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_UPDATEPROGRESS = "PanoramaFragment_childConvertsMainThread_UploadMissionCallBack_updateProgress";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_FINISH = "PanoramaFragment_childConvertsMainThread_UploadMissionCallBack_finish";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_CLOSEDIALOG = "PanoramaFragment_childConvertsMainThread_UploadMissionCallBack_closeDialog";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_LOATASKKMLSHAPE = "PanoramaFragment_childConvertsMainThread_loadTaskKmlShape";
        public static final String PANORAMAFRAMENT_CHILDCONVERTSMAINTHREAD_INITIALIZETASKDATA = "PanoramaFragment_childConvertsMainThread_initializeTaskData";

        public static final String UPDATEIMAGEPANELUTIL_CHILDCONVERTSMAINTHREAD_DOWNLOADPROGRESS = "UpdateImagePanelUtil_childConvertsMainThread_downloadProgress";
        public static final String UPDATEIMAGEPANELUTIL_CHILDCONVERTSMAINTHREAD_IMAGEUPLOAD_SUCCESS = "UpdateImagePanelUtil_childConvertsMainThread_ImageUpload_success";

        public static final String FLYZONEUTIL_CHILDCONVERTSMAINTHREAD_GETFLYZONESINSURROUNDINGAREA_ONSUCCESS = "FlyZoneUtil_childConvertsMainThread_getFlyZonesInSurroundingArea_onSuccess";
        public static final String FLYZONEUTIL_CHILDCONVERTSMAINTHREAD_GETUNLOCKFLYZONE = "FlyZoneUtil_childConvertsMainThread_getUnlockFlyZone";

        public static final String REMOTEFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_FAILED = "RemoteFragment_childConvertsMainThread_UploadMissionCallBack_failed";
        public static final String REMOTEFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_START = "RemoteFragment_childConvertsMainThread_UploadMissionCallBack_start";
        public static final String REMOTEFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_UPDATEPROGRESS = "RemoteFragment_childConvertsMainThread_UploadMissionCallBack_updateProgress";
        public static final String REMOTEFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_FINISH = "RemoteFragment_childConvertsMainThread_UploadMissionCallBack_finish";
        public static final String REMOTEFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_CLOSEDIALOG = "RemoteFragment_childConvertsMainThread_UploadMissionCallBack_closeDialog";
        public static final String REMOTEFRAMENT_CHILDCONVERTSMAINTHREAD_STARTFLYTOWORK = "RemoteFragment_childConvertsMainThread_startFlyToWork";
        public static final String REMOTEFRAMENT_CHILDCONVERTSMAINTHREAD_UPLOADWAYPOINTDJI = "RemoteFragment_childConvertsMainThread_uploadWayPointDJI";
        public static final String REMOTEFRAMENT_CHILDCONVERTSMAINTHREAD_RETIFYCOORDINATE = "RemoteFragment_childConvertsMainThread_rectifyCoordinate";

        public static final String APPUPDATESERVICE_CHILDCONVERTSMAINTHREAD_OKHTTPDOWNLOADTHREAD = "AppUpdateService_childConvertsMainThread_OkHttpDownLoadThread";
        public static final String APPUPDATESERVICE_CHILDCONVERTSMAINTHREAD_OKHTTPDOWNLOADAPK_ONFINISHED = "AppUpdateService_childConvertsMainThread_okHttpDownLoadApk_onFinished";
        public static final String APPUPDATESERVICE_CHILDCONVERTSMAINTHREAD_OKHTTPDOWNLOADAPK_ONPROGRESS = "AppUpdateService_childConvertsMainThread_okHttpDownLoadApk_onProgress";
        public static final String APPUPDATESERVICE_CHILDCONVERTSMAINTHREAD_OKHTTPDOWNLOADAPK_ONCANCEL = "AppUpdateService_childConvertsMainThread_okHttpDownLoadApk_onCancel";

        public static final String GENERALFRAGMENT_CHILDCONVERTSMAINTHREAD_INITCACHESIZETASKTHREAD = "GeneralFragment_childConvertsMainThread_initCacheSizeTaskThread";
        public static final String GENERALFRAGMENT_CHILDCONVERTSMAINTHREAD_ISLOGINSUCCESS = "GeneralFragment_childConvertsMainThread_isLoginSuccess";
        public static final String GENERALFRAGMENT_CHILDCONVERTSMAINTHREAD_ISLOGINSUCCESS_2 = "GeneralFragment_childConvertsMainThread_isLoginSuccess_2";
        public static final String GENERALFRAGMENT_CHILDCONVERTSMAINTHREAD_LOGOUTUNBINDDEVICEERROR = "GeneralFragment_childConvertsMainThread_logoutUnBindDeviceError";

        public static final String SENSORCHECKGRAGMENT_CHILDCONVERTSMAINTHREAD_SETIMUSTATECALLBACK = "SensorCheckFragment_childConvertsMainThread_setIMUStateCallback";
        public static final String SENSORCHECKGRAGMENT_CHILDCONVERTSMAINTHREAD_GETCALIBRATIONSTATE = "SensorCheckFragment_childConvertsMainThread_getCalibrationState";

        public static final String GIMBALFRAGMENT_CHILDCONVERTSMAINTHREAD_GETCONTROLLERSMOOTHINGFACTOR = "GimbalFragment_childConvertsMainThread_getControllerSmoothingFactor";
        public static final String GIMBALFRAGMENT_CHILDCONVERTSMAINTHREAD_GETPITCHRANGEEXTENSIONENABLED_ONSUCCESS = "GimbalFragment_childConvertsMainThread_getPitchRangeExtensionEnabled_onSuccess";
        public static final String GIMBALFRAGMENT_CHILDCONVERTSMAINTHREAD_GETPITCHRANGEEXTENSIONENABLED_ONFAILURE = "GimbalFragment_childConvertsMainThread_getPitchRangeExtensionEnabled_onFailure";

        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INISETCHANNELNUMBER_OCUSYNCLINK = "TransferImageFragment_childConvertsMainThread_initSetChannelNumber_ocuSyncLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INISETCHANNELNUMBER_LIGHTBRIDGELINK = "TransferImageFragment_childConvertsMainThread_initSetChannelNumber_lightbridgeLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_SETCHANNELTYPE_OCUSYNCLINK = "TransferImageFragment_childConvertsMainThread_setChannelType_ocuSyncLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_SETCHANNELTYPE_LIGHTBRIDGELINK = "TransferImageFragment_childConvertsMainThread_setChannelType_lightbridgeLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INICHANNELBANDWITH_OCUSYNCLINK_ONSUCCESS = "TransferImageFragment_childConvertsMainThread_initChannelBandwidth_ocuSyncLink_onSuccess";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INICHANNELBANDWITH_OCUSYNCLINK_ONFAILURE = "TransferImageFragment_childConvertsMainThread_initChannelBandwidth_ocuSyncLink_onFailure";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INICHANNELBANDWITH_CHANNELBANDWITH20_OCUSYNCLINK = "TransferImageFragment_childConvertsMainThread_ChannelBandwidth20_ocuSyncLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INICHANNELBANDWITH_CHANNELBANDWITH10_OCUSYNCLINK = "TransferImageFragment_childConvertsMainThread_ChannelBandwidth20_ocuSyncLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_GETBANDWITHALLOCATIONFORHDMIVIDDOINPUTPORT_ONSUCCESS = "TransferImageFragment_childConvertsMainThread_getBandwidthAllocationForHDMIVideoInputPort_onSuccess";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_GETBANDWITHALLOCATIONFORHDMIVIDDOINPUTPORT_ONFAILURE = "TransferImageFragment_childConvertsMainThread_getBandwidthAllocationForHDMIVideoInputPort_onFailure";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_GETEXTVIDEOINPUTPORTENABLED_ONSUCCESS = "TransferImageFragment_childConvertsMainThread_getEXTVideoInputPortEnabled_onSuccess";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_GETEXTVIDEOINPUTPORTENABLED_ONFAILURE = "TransferImageFragment_childConvertsMainThread_getEXTVideoInputPortEnabled_onFailure";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_SETUPLINKSIGNALQUALITYCALLBACK = "TransferImageFragment_childConvertsMainThread_setUpLinkSignalQualityCallback";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_GETCHANNELNUMBERVALIDRANGE = "TransferImageFragment_childConvertsMainThread_getChannelNumberValidRange";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_GETCHANNELSELECTIONMODE = "TransferImageFragment_childConvertsMainThread_getChannelSelectionMode";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_GETCHANNELRANGE = "TransferImageFragment_childConvertsMainThread_getChannelRange";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_GETCHNNELSELECTIONMODE_LIGHTBRIDGELINK = "TransferImageFragment_childConvertsMainThread_getChannelSelectionMode_lightbridgeLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_SETCHANNELSELECTIONMODE_OCUSYNCLINK = "TransferImageFragment_childConvertsMainThread_setChannelSelectionMode_ocuSyncLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_SETCHANNELSELECTIONMODE_LIGHTBRIDGELINK = "TransferImageFragment_childConvertsMainThread_setChannelSelectionMode_lightbridgeLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_CHANNELCOSTOM_OCUSYNCLINK = "TransferImageFragment_childConvertsMainThread_ChannelCustom_ocuSyncLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_CHANNELCOSTOM_LIGHTBRIDGELINK = "TransferImageFragment_childConvertsMainThread_ChannelCustom_lightbridgeLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INITFREQUENCYBAND_OCUSYNCLINK = "TransferImageFragment_childConvertsMainThread_initFrequencyBand_ocuSyncLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INITFREQUENCYBAND_LIGHTBRIDGELINK = "TransferImageFragment_childConvertsMainThread_initFrequencyBand_lightbridgeLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INITFREQUENCYBAND_LIGHTBRIDGELINK_GETFREQUENCYBAND = "TransferImageFragment_childConvertsMainThread_initFrequencyBand_lightbridgeLink_getFrequencyBand";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INITFREQUENCYBAND_SETFREQUENCYBAND_OCUSYNCLINK = "TransferImageFragment_childConvertsMainThread_setFrequencyBand_ocuSyncLink";
        public static final String TRANSFERIMAGEFRAGMENT_CHILDCONVERTSMAINTHREAD_INITFREQUENCYBAND_SETFREQUENCYBAND_LIGHTBRIDGELINK = "TransferImageFragment_childConvertsMainThread_setFrequencyBand_lightbridgeLink";

        public static final String LOGINACTIVITY_CHILDCONVERTSMAINTHREAD_PSSWORDEDITTEXT = "LoginActivity_childConvertsMainThread_passwordEditText";

        public static final String FLYRECORDINGACTIVITY_CHILDCONVERTSMAINTHREAD_UPLOADRECORD = "FlyRecordingActivity_childConvertsMainThread_uploadRecord";
        public static final String FLYRECORDINGACTIVITY_CHILDCONVERTSMAINTHREAD_REPRATUPLOADFLYRECORD = "FlyRecordingActivity_childConvertsMainThread_repeatUploadFlyRecord";
        public static final String FLYRECORDINGACTIVITY_CHILDTHREAD_UPLOADRECORD = "FlyRecordingActivity_childThread_uploadRecord";
        public static final String FLYRECORDINGACTIVITY_CHILDTHREAD_REPEATUPLOADFLYRECORDTHREAD = "FlyRecordingActivity_childThread_repeatUploadFlyRecordThread";

        public static final String EASYCONTROLAIRREMOTEFLY_CHILDCONVERTSMAINTHREAD_POLLINGHEARTBEATTOINFO = "EasyControlAirRemoteFly_childConvertsMainThread_pollingHeartBeatToInfo";
        public static final String EASYCONTROLAIRREMOTEFLY_CHILDCONVERTSMAINTHREAD_POSTPOLLINGHEARBEATINFO_DETAILAIRLINE = "EasyControlAirRemoteFly_childConvertsMainThread_postPollingHeartBeatInfo_DETAILAIRLINE";
        public static final String EASYCONTROLAIRREMOTEFLY_CHILDCONVERTSMAINTHREAD_POSTPOLLINGHEARBEATINFO_SAPNAIRLINE = "EasyControlAirRemoteFly_childConvertsMainThread_postPollingHeartBeatInfo_SAPNAIRLINE";
        public static final String EASYCONTROLAIRREMOTEFLY_CHILDCONVERTSMAINTHREAD_INITUAVSTATIONINFO = "EasyControlAirRemoteFly_childConvertsMainThread_initUavStationInfo";
        public static final String EASYCONTROLAIRREMOTEFLY_CHILDCONVERTSMAINTHREAD_GETPRODUCTSERIALNUMBER = "EasyControlAirRemoteFly_childConvertsMainThread_getProductSerialNumber";
        public static final String EASYCONTROLAIRREMOTEFLY_CHILDCONVERTSMAINTHREAD_STARTLIVESHOW = "EasyControlAirRemoteFly_childConvertsMainThread_startLiveShow";
        public static final String EASYCONTROLAIRREMOTEFLY_CHILDCONVERTSMAINTHREAD_SENDLIVEVIDEORESOLUTION = "EasyControlAirRemoteFly_childConvertsMainThread_sendLiveVideoResolution";

        public static final String TASKMANAGEMENTACTIVITY_CHILDCONVERTSMAINTHREAD_GETTASKS = "TaskManagementActivity_childConvertsMainThread_getTasks";

        public static final String TOASTUTIL_CHILDCONVERTSMAINTHREAD_TOASTUTIL_SHOWTOAST = "ToastUtil_childConvertsMainThread_ToastUtil_showToast";

        public static final String RTKFACTORY_CHILDCONVERTSMAINTHREAD_SETREFERENCESTATIONSOURCE = "RTKFactory_childConvertsMainThread_setReferenceStationSource";

        public static final String LOGINDJIACCOUNTMACHINE_CHILDCONVERTSMAINTHREAD_CHECKAPPVERSION = "LoginDJIAccountMachine_childConvertsMainThread_checkAppVersion";

        public static final String STARTMACHINE_CHILDCONVERTSMAINTHREAD_GETLOGIN = "StartMachine_childConvertsMainThread_getLogin";
        public static final String STARTMACHINE_CHILDCONVERTSMAINTHREAD_CHECKAPPVERSION = "StartMachine_childConvertsMainThread_checkAppVersion";

        public static final String FLYRECORDINGMACHINE_CHILDCONVERTSMAINTHREAD_GETLASTRECORDDATE = "FlyRecordingMachine_childConvertsMainThread_getLastRecordDate";
        public static final String FLYRECORDINGMACHINE_CHILDCONVERTSMAINTHREAD_SAVEFLYRECORD = "FlyRecordingMachine_childConvertsMainThread_saveFlyRecord";

        public static final String PLANTODOMACHINE_CHILDCONVERTSMAINTHREAD_PUSHLINETASK = "PlanToDoMachine_childConvertsMainThread_getUserTask";
        public static final String PLANTODOMACHINE_CHILDCONVERTSMAINTHREAD_GETUSERTASK = "PlanToDoMachine_childConvertsMainThread_getUserTask";

        public static final String GENERAMACHINE_CHILDCONVERTSMAINTHREAD_CHECKAPPVERSION = "GeneralMachine_childConvertsMainThread_checkAppVersion";
        public static final String GENERAMACHINE_CHILDCONVERTSMAINTHREAD_LOGOUTDEVICE = "GeneralMachine_childConvertsMainThread_logoutDevice";
        public static final String GENERAMACHINE_CHILDCONVERTSMAINTHREAD_GETAPPUPDATEINFO = "GeneralMachine_childConvertsMainThread_getAppUpdateInfo";

        public static final String BACKSYNCMACHINE_CHILDCONVERTSMAINTHREAD_GETLASTRECORDDATE = "BackSyncMachine_childConvertsMainThread_getLastRecordDate";
        public static final String BACKSYNCMACHINE_CHILDCONVERTSMAINTHREAD_SAVEFLYRECORD = "BackSyncMachine_childConvertsMainThread_saveFlyRecord";
        public static final String BACKSYNCMACHINE_CHILDCONVERTSMAINTHREAD_SAVEFLYDATA = "BackSyncMachine_childConvertsMainThread_saveFlyData";
        public static final String BACKSYNCMACHINE_CHILDCONVERTSMAINTHREAD_FETRTKINFO = "BackSyncMachine_childConvertsMainThread_getRtkInfo";
        public static final String BACKSYNCMACHINE_CHILDCONVERTSMAINTHREAD_REFRESHTK = "BackSyncMachine_childConvertsMainThread_refreshTk";
        public static final String BACKSYNCMACHINE_CHILDCONVERTSMAINTHREAD_STARTFLYRECORDTHREAD = "startFlyRecordThread_childThread_startFlyRecordThread";

        public static final String AIRCRAFTSTATESERVICE_CHILDCONVERTSMAINTHREAD_REFRESH_AIR_STATE = "AircraftStateService_interval_refresh_air_state";

        public static final String LOGINMACHINE_CHILDCONVERTSMAINTHREAD_GETLOGJINID = "LoginMachine_childConvertsMainThread_getLoginId";
        public static final String LOGINMACHINE_CHILDCONVERTSMAINTHREAD_DOWNCODE = "LoginMachine_childConvertsMainThread_downCode";
        public static final String LOGINMACHINE_CHILDCONVERTSMAINTHREAD_AT = "LoginMachine_childConvertsMainThread_aToken";
        public static final String LOGINMACHINE_CHILDCONVERTSMAINTHREAD_USERINFO = "LoginMachine_childConvertsMainThread_userInfo";
        public static final String LOGINMACHINE_CHILDCONVERTSMAINTHREAD_CHECKAPPVERSION = "LoginMachine_childConvertsMainThread_checkAppVersion";

        public static final String DJICHECKPRETAKEOFFSTATUS_CHILDCONVERTSMAINTHREAD_CHECKDJILOGINSTATUS = "DJICheckPreTakeOffStatus_childConvertsMainThread_CHECKDJILOGINSTATUS";

        public static final String IMAGEUPLOAD_CHILDCONVERTSMAINTHREAD_UAVMODULE_UPIMAGE = "ImageUpload_childConvertsMainThread_uavmodule_upImage";

        public static final String LiveStreamManagerFactory_CHILDCONVERTSMAINTHREAD_STARTLIVESHOW = "LiveStreamManagerFactory_childConvertsMainThread_startLiveShow";

        public static final String UPTHOTOSERVICE_CHILDCONVERTSMAINTHREAD_STARTDOWNIMAGE = "UpPhotoService_childConvertsMainThread_startDownImages";

        public static final String TIMELINEMISSIONMODE_CHILDCONVERTSMAINTHREAD_STARTPANNORAMAMISSION = "TimelineMissionMode_childConvertsMainThread_startPanoramaMission";

        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_SELECTTOWER = "PointCloudFragment_childConvertsMainThread_selectTower";
        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_STARTFLYAIRTOWORK = "PointCloudFragment_childConvertsMainThread_startFlyAirToWork";
        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADWAYPOINTDJI = "PointCloudFragment_childConvertsMainThread_uploadWayPointDJI";
        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_FAILED = "PointCloudFragment_childConvertsMainThread_UploadMissionCallBack_failed";
        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_START = "PointCloudFragment_childConvertsMainThread_UploadMissionCallBack_start";
        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_UPDATEPROGRESS = "PointCloudFragment_childConvertsMainThread_UploadMissionCallBack_updateProgress";
        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_FINISH = "PointCloudFragment_childConvertsMainThread_UploadMissionCallBack_finish";
        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_UPLOADMISSIONCALLBACK_CLOSEDIALOG = "PointCloudFragment_childConvertsMainThread_UploadMissionCallBack_closeDialog";
        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_LOADTASKKMLSHAPE = "PointCloudFragment_childConvertsMainThread_loadTaskKmlShape";
        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_INITIALIZETASKDATA = "PointCloudFragment_childConvertsMainThread_initializeTaskData";
        public static final String POINTCLOUNDFRAGMENT_CHILDCONVERTSMAINTHREAD_THINKAGAIN = "PointCloudFragment_childConvertsMainThread_thinkAgain";

        public static final String COLLECTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_LOADTASKKMLSHAPE = "CollectionFragment_childConvertsMainThread_loadTaskKmlShape";

        public static final String PLANTASKADAPTER_CHILDCONVERTSMAINTHREAD_FINISHPLANTASK = "PlanTaskAdapter_childConvertsMainThread_finishPlanTask";
        public static final String PLANTASKADAPTER_CHILDCONVERTSMAINTHREAD_COMMITPLANTASK = "PlanTaskAdapter_childConvertsMainThread_commitPlanTask";

        public static final String MEDIAFILEUTIL_CHILDCONVERTSMAINTHREAD_REFRESHFILELISTOFSTORAGELOCATION = "MediaFileUtil_childConvertsMainThread_refreshFileListOfStorageLocation";

        public static final String FLYIMAGEADAPTER_CHILDCONVERTSMAINTHREAD_SETIMAGEBITMAP = "FlyImageAdapter_childConvertsMainThread_setImageBitmap";
        public static final String PREVIEWMEDIAADAPTER_CHILDCONVERTSMAINTHREAD_SETIMAGEBITMAP = "PreviewMediaAdapter_childConvertsMainThread_setImageBitmap";

        public static final String UAVTASKFRAGMENT_CHILDCONVERTSMAINTHREAD_ISCLICKBUTTON = "UavTaskFragment_childConvertsMainThread_isClickButton";

        public static final String MAINACTIVITY_CHILDTHREAD_UNZIPAIRLINE = "MainActivity_childThread_UNZIPAIRLINE";

        public static final String TODOAIRLINEMISSION_CHILDCONVERTSMAINTHREAD_UPLOADM300RTKMISSION = "ToDoAirLineMission_childConvertsMainThread_uploadM300RTKMission";

        public static final String LOGINACTIVITY_CHILDCONVERTSMAINTHREAD_FINISHLOGINACTIVITY = "LoginActivity_childConvertsMainThread_finishLoginActivity";

        public static final String AIRLINEFILE_CHILDCONVERTSMAINTHREAD_UNPACKANDOVERWRITE = "AirlineFile_childConvertsMainThread_UnPackAndOverWrite";

//        public static final String MAINACREGISTER_CHILDCONVERTSMAINTHREAD_ACCEPTMAINACREGISTERCONSUMER = "MainAcRegister_childConvertsMainThread_acceptMainAcRegisterConsumer";

        public static final String POSTRXBUSMESSAGEFACTORY_CHILDCONVERTSMAINTHREAD_POSTMAINACRXBUSMESSAGE = "postRxBusMessageFactory_childConvertsMainThread_postMainAcRxBusMessage";

        public static final String MAINACTIVITY_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE = "MainActivity_childConvertsMainThread_setFlightState";
        public static final String MAINACTIVITY_CHILDCONVERTSMAINTHREAD_SETGIMBALSTATE = "MainActivity_childConvertsMainThread_setGimbalState";
        public static final String MAINACTIVITY_CHILDCONVERTSMAINTHREAD_SETBATTERSTATE = "MainActivity_childConvertsMainThread_setBatterState";
        public static final String MAINACTIVITY_CHILDCONVERTSMAINTHREAD_SETCAMERAMEDIA = "MainActivity_childConvertsMainThread_setCameraMedia";
        public static final String MAINACTIVITY_CHILDCONVERTSMAINTHREAD_SETCAMERASTATE = "MainActivity_childConvertsMainThread_setCameraState";

        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETAIRCRAFTLOCATION = "ChannelFragment_childConvertsMainThread_setFlightState_setAirCraftLocation";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETFLIGHTVIEW = "ChannelFragment_childConvertsMainThread_setFlightState_setFlightView";
        public static final String CHANNELFRAGMENT_CHILDCONVERTSMAINTHREAD_THINKAGAIN = "ChannelFragment_childConvertsMainThread_thinkAgain";

        public static final String COLLECTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETAIRCRAFTLOCATION = "CollectionFragment_childConvertsMainThread_setFlightState_setAirCraftLocation";
        public static final String COLLECTIONFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETFLIGHTVIEW = "CollectionFragment_childConvertsMainThread_setFlightState_setFlightView";

        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETAIRCRAFTLOCATION = "FaultFragment_childConvertsMainThread_setFlightState_setAirCraftLocation";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETFLIGHTVIEW = "FaultFragment_childConvertsMainThread_setFlightState_setFlightView";
        public static final String FAULTFRAGMENT_CHILDCONVERTSMAINTHREAD_THINKAGAIN = "FaultFragment_childConvertsMainThread_thinkAgain";

        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETAIRCRAFTLOCATION = "FineFragment_childConvertsMainThread_setFlightState_setAirCraftLocation";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETFLIGHTVIEW = "FineFragment_childConvertsMainThread_setFlightState_setFlightView";
        public static final String FINEFRAGMENT_CHILDCONVERTSMAINTHREAD_THINKAGAIN = "FaultFragment_childConvertsMainThread_thinkAgain";

        public static final String PANORAMAFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETAIRCRAFTLOCATION = "PanoramaFragment_childConvertsMainThread_setFlightState_setAirCraftLocation";
        public static final String PANORAMAFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETFLIGHTVIEW = "PanoramaFragment_childConvertsMainThread_setFlightState_setFlightView";
        public static final String PANORAMAFRAGMENT_CHILDCONVERTSMAINTHREAD_THINKAGAIN = "PanoramaFragment_childConvertsMainThread_thinkAgain";

        public static final String POINTCLOUDFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETAIRCRAFTLOCATION = "PointCloudFragment_childConvertsMainThread_setFlightState_setAirCraftLocation";
        public static final String POINTCLOUDFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETFLIGHTVIEW = "PointCloudFragment_childConvertsMainThread_setFlightState_setFlightView";

        public static final String REMOTEFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETAIRCRAFTLOCATION = "RemoteFragment_childConvertsMainThread_setFlightState_setAirCraftLocation";
        public static final String REMOTEFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_SETFLIGHTVIEW = "RemoteFragment_childConvertsMainThread_setFlightState_setFlightView";

        public static final String REMOTEFRAGMENT_CHILDCONVERTSMAINTHREAD_SETFLIGHTSTATE_DJILIVEVIDEOINFO = "RemoteFragment_childConvertsMainThread_setFlightState_djiLiveVideoInfo";

        public static final String UPLOADWAYPOINT_CHILDTHREAD_UPLOADWAYPOINTDJITHREAD = "UploadWayPoint_childThread_uploadWayPointDJIThread";
    }

    public static String updateAppInfo =
            "1.【增加】电网管理平台派工任务负责人提交按钮及逻辑\n" +
                    "2.【修改】电网管理平台派工任务完成任务按钮及逻辑\n" +
                    "3.【增加】UAVX任务增加搜索航线功能\n" +
                    "4.【增加】RTK面板增加展示RTK是否连接飞机状态";

    public static String warmPrompt =
            "1.机巡通暂只支持大疆御2系列，精灵系列无人机，图片上传功能目前只支持御2系列无人机\n" +
                    "2.RTK无法固定时RTK诊断面板显示无人机坐标为N/A或者基站为0.00000时请将无人机关闭并静置3-5分钟换个位置再重启无人机\n" +
                    "3.精灵4RTK，御2进阶版恢复空中加载航线作业请注意飞行安全\n" +
                    "4.第一次安装app请先使用用外网登录，加载地图和初始化大疆SDK，再退出登录使用内网登录\n" +
                    "5.刷新返航点修改为无人机静置地面点击定位按钮刷新\n" +
                    "6.精细化逆序飞行请先设置逆序参数在选择杆塔进行巡检";

    public static boolean HAS_CONTROL_PANEL = true;

    private static String ROOTURL = "https://";//移动应用平台

    public static String getRootUrl() {

//        if (StringUtil.isEmpty(SharedPrefaceUtils.getString("BASEURLTYPE"))) return ROOTURL;

//        if (StringUtil.isEmpty(SharedPrefaceUtils.getString("BASEURLTYPE"))) return  AbAppConfig.APN;
//
//        if ("VPN".equals(SharedPrefaceUtils.getString(SharedName.BASEURLTYPE))) {
//            ROOTURL = AbAppConfig.VPN;//移动应用平台
//        } else if ("APN".equals(SharedPrefaceUtils.getString(SharedName.BASEURLTYPE))) {
//            ROOTURL = AbAppConfig.APN; //DMZ
//        } else if ("WAPI".equals(SharedPrefaceUtils.getString(SharedName.BASEURLTYPE))) {
//            ROOTURL = AbAppConfig.WAPI; //wapi
//        }
//        return ROOTURL;

        return AbAppConfig.APN;
    }

}