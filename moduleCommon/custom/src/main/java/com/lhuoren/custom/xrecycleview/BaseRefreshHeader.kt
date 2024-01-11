package com.lhuoren.custom.xrecycleview

/**
 * Created by lhr on 15/11/22.
 */
internal interface BaseRefreshHeader {
    fun onMove(delta: Float)
    fun releaseAction(): Boolean
    fun refreshComplete()

    companion object {
        const val STATE_NORMAL = 0
        const val STATE_RELEASE_TO_REFRESH = 1
        const val STATE_REFRESHING = 2
        const val STATE_DONE = 3
    }
}