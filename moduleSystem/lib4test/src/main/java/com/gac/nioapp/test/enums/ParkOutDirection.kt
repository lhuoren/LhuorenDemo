package com.gac.nioapp.test.enums

/**
 * @className ParkOutDirection
 * @description 泊车方向
 * @author heyufei
 * @since 2021/8/2 5:52 下午
 * @version 1.0
 */
enum class ParkOutDirection(val direction: Int, val describe: String) {
    CROSS_LEFT_OUT(1, "Cross left out"),
    PARALLEL_LEFT_OUT(2, "Parallel left out"),
    FRONT_OUT(3, "Front out"),
    CROSS_RIGHT_OUT(4, "Cross right out"),
    PARALLEL_RIGHT_OUT(5, "Parallel right out"),
    REAR_OUT(6, "Rear out"),
}