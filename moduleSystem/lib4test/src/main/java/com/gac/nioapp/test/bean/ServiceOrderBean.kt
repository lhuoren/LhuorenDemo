package com.gac.nioapp.test.bean

data class ServiceOrderBean(val id: Int,
                            val serviceType: Int,
                            val serviceTime: String? = null,
                            val title: String? = null,
                            val desc: String? = null,
                            val status: Int? = null,
                            val statusString: String? = null,
                            val orderId: String? = null,
                            val createTime: String? = null,
                            val tag: List<String>? = null ) {


}

//0-全部  1-预约维保 2-专属桩安装 3-其他 4-一键加电
enum class ServiceType(val type: Int, val typeName: String) {
    Whole(0, "全部"),
    Maintenance(1, "预约维保"),
    Charge(2, "充电桩"),
    Other(3, "其他"), //上牌服务
    Battery(4, "一键加电")
}