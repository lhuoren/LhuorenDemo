package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.PropertyValuesHolder
import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.animation.LinearInterpolator
import com.lhuoren.custom.xrecycleview.indicator.BaseIndicatorController

/**
 * Created by lhuoren on 2015/10/20.
 */
class TriangleSkewSpinIndicator : BaseIndicatorController() {
    override fun draw(canvas: Canvas, paint: Paint) {
        val path = Path()
        path.moveTo((width / 5).toFloat(), (height * 4 / 5).toFloat())
        path.lineTo((width * 4 / 5).toFloat(), (height * 4 / 5).toFloat())
        path.lineTo((width / 2).toFloat(), (height / 5).toFloat())
        path.close()
        canvas.drawPath(path, paint)
    }

    override fun createAnimation(): List<Animator> {
        val animators: MutableList<Animator> = ArrayList()
        val rotation5 = PropertyValuesHolder.ofFloat("rotationX", 0f, 180f, 180f, 0f, 0f)
        val rotation6 = PropertyValuesHolder.ofFloat("rotationY", 0f, 0f, 180f, 180f, 0f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(target, rotation6, rotation5)
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = -1
        animator.duration = 2500
        animator.start()
        animators.add(animator)
        return animators
    }
}