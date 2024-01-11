package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.PropertyValuesHolder
import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.graphics.Paint
import android.view.animation.LinearInterpolator

/**
 * Created by lhuoren on 2015/10/17.
 */
class BallPulseRiseIndicator : BaseIndicatorController() {
    override fun draw(canvas: Canvas, paint: Paint) {
        val radius = (width / 10).toFloat()
        canvas.drawCircle((width / 4).toFloat(), radius * 2, radius, paint)
        canvas.drawCircle((width * 3 / 4).toFloat(), radius * 2, radius, paint)
        canvas.drawCircle(radius, height - 2 * radius, radius, paint)
        canvas.drawCircle((width / 2).toFloat(), height - 2 * radius, radius, paint)
        canvas.drawCircle(width - radius, height - 2 * radius, radius, paint)
    }

    override fun createAnimation(): List<Animator> {
        val rotation6 = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(target, rotation6)
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = -1
        animator.duration = 1500
        animator.start()
        val animators: MutableList<Animator> = ArrayList()
        animators.add(animator)
        return animators
    }
}