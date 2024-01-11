package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.ValueAnimator
import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.graphics.Paint

/**
 * Created by lhuoren on 2015/10/17.
 */
class BallRotateIndicator : BaseIndicatorController() {
    var scaleFloat = 0.5f
    override fun draw(canvas: Canvas, paint: Paint) {
        val radius = (width / 10).toFloat()
        val x = (width / 2).toFloat()
        val y = (height / 2).toFloat()
        canvas.save()
        canvas.translate(x - radius * 2 - radius, y)
        canvas.scale(scaleFloat, scaleFloat)
        canvas.drawCircle(0f, 0f, radius, paint)
        canvas.restore()
        canvas.save()
        canvas.translate(x, y)
        canvas.scale(scaleFloat, scaleFloat)
        canvas.drawCircle(0f, 0f, radius, paint)
        canvas.restore()
        canvas.save()
        canvas.translate(x + radius * 2 + radius, y)
        canvas.scale(scaleFloat, scaleFloat)
        canvas.drawCircle(0f, 0f, radius, paint)
        canvas.restore()
    }

    override fun createAnimation(): List<Animator> {
        val animators: MutableList<Animator> = ArrayList()
        val scaleAnim = ValueAnimator.ofFloat(0.5f, 1f, 0.5f)
        scaleAnim.duration = 1000
        scaleAnim.repeatCount = -1
        scaleAnim.addUpdateListener { animation ->
            scaleFloat = animation.animatedValue as Float
            postInvalidate()
        }
        scaleAnim.start()
        val rotateAnim = ObjectAnimator.ofFloat(target, "rotation", 0f, 180f, 360f)
        rotateAnim.duration = 1000
        rotateAnim.repeatCount = -1
        rotateAnim.start()
        animators.add(scaleAnim)
        animators.add(rotateAnim)
        return animators
    }
}