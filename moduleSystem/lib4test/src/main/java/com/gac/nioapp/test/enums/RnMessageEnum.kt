package com.gac.nioapp.test.enums

/**
 * @ClassName RnMessageEnum
 * @Description TODO
 * @Author LiuZhiQiang
 * @Date 2022/3/8 15:40
 */
enum class RnMessageEnum(val value: String) {

    ACTION_ACTIVITY_RESTART("ReactNativeEventAtionActivityStart"),
    ACTION_ACTIVITY_STOP("ReactNativeEventAtionActivityStop"),
    ACTION_ACTIVITY_DESTROY("ACTION_ACTIVITY_DESTROY"),
    REACT_NATIVE_EVENT_LOGIN_SUCCESSFUL("ReactNativeEventLoginSuccessful"),
    REACT_NATIVE_EVENT_SIGN_OUT_LOGIN("ReactNativeEventSignOutLogin"),
    REACT_NATIVE_EVENT_REFRESH_E_DRIVE_TICKETS("ReactNativeEventRefreshEDriveTickets"),
    REACT_NATIVE_EVENT_NORMAL("ReactNativeEventNormal"),
    REACT_NATIVE_EVENT_PUBLISH_CONTENT("ReactNativeEventPublishContent"),
    REACT_NATIVE_EVENT_DISAPPEAR("ReactNativeEventDisappear"),
    REACT_NATIVE_EVENT_DOUBLE_CLICK_TAB("ReactNativeEventDoubleClickTab"),
    REACT_NATIVE_EVENT_LIKE_TO_REFRESH("ReactNativeEventLikeToRefresh"),
    REACT_NATIVE_EVENT_RECEIVE_CHAT_MESSAGE("ReactNativeEventReceiveChatMessage"),//收到聊天消息

    /** RN页面刷新通用通知,一般可用于不需要参数回调,不可调用触发通知 */
    REACT_NATIVE_EVENT_REFRESH_DEFAULT("ReactNativeEventRefreshDefault"),


    /**
     * 原生通知RN视频ugc上传状态
     */
    REACT_NATIVE_EVENT_VIDEO_UGC_UPLOAD_STATUS("ReactNativeEventVideoUGCUploadStatus"),

    /**
     * 原生通知RN视频自动播放设置更改
     */
    REACT_NATIVE_EVENT_VIDEO_UGC_AUTO_PLAY_MODIFY("ReactNativeEventVideoUGCAutoPlayModify"),


    /**
     * 网络可达状态改变
     */
    REACT_NATIVE_EVENT_NETWORK_REACH_ABILITY_STATUS_CHANGE("ReactNativeEventNetworkReachabilityStatusChange"),


    /**
     * 网络可达状态改变
     */
    //ReactOrderStateChangeEvent下划线命名
    REACT_NATIVE_EVENT_ORDER_STATE_CHANGE("ReactOrderStateChangeEvent"),
}