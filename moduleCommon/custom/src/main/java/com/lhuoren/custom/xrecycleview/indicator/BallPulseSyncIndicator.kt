package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint

/**
 * Created by lhuoren on 2015/10/19.
 */
class BallPulseSyncIndicator : BaseIndicatorController() {
    var translateYFloats = FloatArray(3)
    override fun draw(canvas: Canvas, paint: Paint) {
        val circleSpacing = 4f
        val radius = (width - circleSpacing * 2) / 6
        val x = width / 2 - (radius * 2 + circleSpacing)
        for (i in 0..2) {
            canvas.save()
            val translateX = x + radius * 2 * i + circleSpacing * i
            canvas.translate(translateX, translateYFloats[i])
            canvas.drawCircle(0f, 0f, radius, paint)
            canvas.restore()
        }
    }

    override fun createAnimation(): List<Animator> {
        val animators: MutableList<Animator> = ArrayList()
        val circleSpacing = 4f
        val radius = (width - circleSpacing * 2) / 6
        val delays = intArrayOf(70, 140, 210)
        for (i in 0..2) {
            val scaleAnim = ValueAnimator.ofFloat(
                (height / 2).toFloat(),
                height / 2 - radius * 2,
                (height / 2).toFloat()
            )
            scaleAnim.duration = 600
            scaleAnim.repeatCount = -1
            scaleAnim.startDelay = delays[i].toLong()
            scaleAnim.addUpdateListener { animation ->
                translateYFloats[i] = animation.animatedValue as Float
                postInvalidate()
            }
            scaleAnim.start()
            animators.add(scaleAnim)
        }
        return animators
    }
}