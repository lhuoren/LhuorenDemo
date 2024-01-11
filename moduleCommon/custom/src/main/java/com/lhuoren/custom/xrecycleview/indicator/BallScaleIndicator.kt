package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint
import android.view.animation.LinearInterpolator

/**
 * Created by lhuoren on 2015/10/19.
 */
open class BallScaleIndicator : BaseIndicatorController() {
    var scale = 1f
    var alpha = 255
    override fun draw(canvas: Canvas, paint: Paint) {
        val circleSpacing = 4f
        paint.alpha = alpha
        canvas.scale(scale, scale, (width / 2).toFloat(), (height / 2).toFloat())
        paint.alpha = alpha
        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            width / 2 - circleSpacing,
            paint
        )
    }

    override fun createAnimation(): List<Animator> {
        val animators: MutableList<Animator> = ArrayList()
        val scaleAnim = ValueAnimator.ofFloat(0f, 1f)
        scaleAnim.interpolator = LinearInterpolator()
        scaleAnim.duration = 1000
        scaleAnim.repeatCount = -1
        scaleAnim.addUpdateListener { animation ->
            scale = animation.animatedValue as Float
            postInvalidate()
        }
        scaleAnim.start()
        val alphaAnim = ValueAnimator.ofInt(255, 0)
        alphaAnim.interpolator = LinearInterpolator()
        alphaAnim.duration = 1000
        alphaAnim.repeatCount = -1
        alphaAnim.addUpdateListener { animation ->
            alpha = animation.animatedValue as Int
            postInvalidate()
        }
        alphaAnim.start()
        animators.add(scaleAnim)
        animators.add(alphaAnim)
        return animators
    }
}