package com.lhuoren.custom.croppy.model

import com.lhuoren.custom.croppy.model.Edge.*

enum class Edge {
    NONE,
    LEFT,
    TOP,
    RIGHT,
    BOTTOM
}

fun Edge.opposite() {
    when (this) {
        LEFT -> RIGHT
        TOP -> BOTTOM
        RIGHT -> LEFT
        BOTTOM -> TOP
        NONE -> NONE
    }
}