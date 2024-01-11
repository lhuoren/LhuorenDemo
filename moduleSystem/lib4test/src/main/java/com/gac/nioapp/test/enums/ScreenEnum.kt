package com.gac.nioapp.test.enums

/**
 * @package： com.hycan.community.bean
 * @describe：
 * @author： liming
 * @time： 3/18/21 2:06 PM
 * @e-mail： liming@gac-nio.com
 */
enum class ScreenEnum(var screenType: Int, var screenName: String) {
    vision(0, "首页"),
    right(1, "权益"),
    minePublish(2, "我的发布"),
    test(3, "测试页"),
    auto(4, "Z03USP"),
    demo(5, "实例工程"),
    webview(6, "webview"),
    joinus(7, "加入我们"),
    myOrderList(8, "我的订单"),
    activeOrderList(9, "活动订单列表"),
    autoGuideList(10, "用车指南"),
    autoGuideDetail(11, "用车指南详情"),
    pointDetail(12, "积分详情"),
    PointRule(13, "积分规则"),
    PointExchange(14, "积分兑换"),
    MineRecordScreen(15, "我的关注"),
    MineFansFollowerScreen(16, "我的粉丝"),
    GoodsOrderListScreen(17, "我的订单"),
    ServiceOrderList(18, "服务订单"),
    DeliverCarProvision(19, "取送车条款"),
    MaintenancePlan(20, "爱车保养计划"),
    MaintenanceCharges(21, "保养价格体系"),
    PostList(22, "自定义画布页面"),
    goodsList(24, "好物列表"),
    taskCenter(28, "任务中心"),
    carOrderList(23, "爱车订单列表"),
    carNetCertification(25, "车辆网实名认证"),
    parkingInstructions(26, "泊车说明页"),
    breathe(27, "空调换气"),
    channelCodeShare(29, "渠道码分享"),
    channelCodeUseRecords(30, "渠道码使用记录"),
    channelCode(31, "渠道码列表"),

    userManual(32, "用户手册"),
    essence(33, "精华页"),
    mine(34, "我的"),
    userStatus(35, "个人中心"),
    loveCar(36, "爱车页"),
    loveCar2(37, "心愿单"),
    remoteControlCarParking(38, "遥控泊车"),

    spaActivationPrompt(39, "蓝牙激活提示"),
    carBatteryManagement(40, "电池管理"),
    bookATrip(41, "预约出行"),
    airConditioningSetting(42, "空调设置"),
    seatWindHeat(43, "座椅通风加热"),
    disinfectClean(44, "消毒净化"),
    interior(45, "车内管理"),
    identityUnbinding(46, "实名解绑"),
    carGroupData(47, "车联数据"),
    drivingMonthlyReport(48, "月度驾驶报告"),
    networkServices(49, "网络服务(流量购买)"),
    disinfectionTips(50, "消毒提示"),
    custom(60, "社区自定义Tab"),
    userLogOutTips(62, "注销提醒页面"),
    userReasonForCancellation(63, "注销原因页面"),
    userReasonForCancellationStatusPage(64, "注销原因状态页"),

    g06BluetoothKeySet(72, "蓝牙设置"),
    solutionToThatPage(73, "解绑车辆说明页"),

    //G06（订单选配）申请退款
    refund(66, "申请退款"),
    refundDetail(104, "退订详情"),
    refundOrderDetail(105,"退订账号"),

    //车辆无网络说明页
    carExceptionPage(83, "车辆无网络"),
    g06OTA(84, "车辆软件版本"),

    registerToVote(67, "投票报名页"),
    votingRegistrationDetails(68, "投票报名页详情页"),
    votingHome(69, "投票报名首页"),
    invitePolitely(71, "邀请有礼页"),
    carControlError(86, "车控异常可能原因页"),


    medalpopup(87,"勋章提示页"),
    atlas(91, "车型图库，图册页"),

    spaFeatureListPage(106,"G06 SPA功能列表页"),
    spaAgreementPage(107,"06 SPA协议页"),
    videoAutoSetting(111, "视频自动播放"),

    //商城-团购详情页
    groupBuyingDetail(202, "商城-团购详情页"),

    creativeDetail(108,"潮创社区"),

    emptyView(85,"缺省页"),
    refrigeratorView(16003,"冰箱"),
    drivingWeeklyView(16005,"驾驶周报"),

    licensePlateStatusScreen(19001, "上牌进度"),

    partnerIndexScreen(5001, "合伙人首页"),


    turnSignalSetting(16007, "转向灯设置"),
}