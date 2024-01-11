package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.ValueAnimator
import com.lhuoren.custom.xrecycleview.indicator.LineScaleIndicator

/**
 * Created by lhuoren on 2015/10/19.
 */
class LineScalePulseOutIndicator : LineScaleIndicator() {
    override fun createAnimation(): List<Animator> {
        val animators: MutableList<Animator> = ArrayList()
        val delays = longArrayOf(500, 250, 0, 250, 500)
        for (i in 0..4) {
            val scaleAnim = ValueAnimator.ofFloat(1f, 0.3f, 1f)
            scaleAnim.duration = 900
            scaleAnim.repeatCount = -1
            scaleAnim.startDelay = delays[i]
            scaleAnim.addUpdateListener { animation ->
                scaleYFloats[i] = animation.animatedValue as Float
                postInvalidate()
            }
            scaleAnim.start()
            animators.add(scaleAnim)
        }
        return animators
    }
}