package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.ValueAnimator
import com.lhuoren.custom.xrecycleview.indicator.LineScaleIndicator

/**
 * Created by lhuoren on 2015/10/19.
 */
class LineScalePulseOutRapidIndicator : LineScaleIndicator() {
    override fun createAnimation(): List<Animator> {
        val animators: MutableList<Animator> = ArrayList()
        val delays = longArrayOf(400, 200, 0, 200, 400)
        for (i in 0..4) {
            val scaleAnim = ValueAnimator.ofFloat(1f, 0.4f, 1f)
            scaleAnim.duration = 1000
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