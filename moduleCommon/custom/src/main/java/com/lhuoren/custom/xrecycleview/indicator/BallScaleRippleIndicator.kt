package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint
import android.view.animation.LinearInterpolator
import com.lhuoren.custom.xrecycleview.indicator.BallScaleIndicator

/**
 * Created by lhuoren on 2015/10/19.
 */
class BallScaleRippleIndicator : BallScaleIndicator() {
    override fun draw(canvas: Canvas, paint: Paint) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        super.draw(canvas, paint)
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
        val alphaAnim = ValueAnimator.ofInt(0, 255)
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