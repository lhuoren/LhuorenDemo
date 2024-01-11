package com.gac.nioapp.test.bean

data class G06SocLimitBean(
    val position: Int, //健康充电截止SOC：1=50 2=55 3=60 4=65 5=70 6=75 7=80 8=85 9=90 10=95 11=100；返回档位1-11
    val currentBattery: Float,//当前电池电量
    val isCharging: Boolean = false//是否正在充电,
)
