package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.ValueAnimator
import android.graphics.RectF
import android.graphics.Canvas
import android.graphics.Paint

/**
 * Created by lhuoren on 2015/10/16.
 */
class BallClipRotatePulseIndicator : BaseIndicatorController() {
    var scaleFloat1 = 0f
    var scaleFloat2 = 0f
    var degrees = 0f
    override fun draw(canvas: Canvas, paint: Paint) {
        val circleSpacing = 12f
        val x = (width / 2).toFloat()
        val y = (height / 2).toFloat()

        //draw fill circle
        canvas.save()
        canvas.translate(x, y)
        canvas.scale(scaleFloat1, scaleFloat1)
        paint.style = Paint.Style.FILL
        canvas.drawCircle(0f, 0f, x / 2.5f, paint)
        canvas.restore()
        canvas.translate(x, y)
        canvas.scale(scaleFloat2, scaleFloat2)
        canvas.rotate(degrees)
        paint.strokeWidth = 3f
        paint.style = Paint.Style.STROKE

        //draw two arc
        val startAngles = floatArrayOf(225f, 45f)
        for (i in 0..1) {
            val rectF =
                RectF(-x + circleSpacing, -y + circleSpacing, x - circleSpacing, y - circleSpacing)
            canvas.drawArc(rectF, startAngles[i], 90f, false, paint)
        }
    }

    override fun createAnimation(): List<Animator> {
        val scaleAnim = ValueAnimator.ofFloat(1f, 0.3f, 1f)
        scaleAnim.duration = 1000
        scaleAnim.repeatCount = -1
        scaleAnim.addUpdateListener { animation ->
            scaleFloat1 = animation.animatedValue as Float
            postInvalidate()
        }
        scaleAnim.start()
        val scaleAnim2 = ValueAnimator.ofFloat(1f, 0.6f, 1f)
        scaleAnim2.duration = 1000
        scaleAnim2.repeatCount = -1
        scaleAnim2.addUpdateListener { animation ->
            scaleFloat2 = animation.animatedValue as Float
            postInvalidate()
        }
        scaleAnim2.start()
        val rotateAnim = ValueAnimator.ofFloat(0f, 180f, 360f)
        rotateAnim.duration = 1000
        rotateAnim.repeatCount = -1
        rotateAnim.addUpdateListener { animation ->
            degrees = animation.animatedValue as Float
            postInvalidate()
        }
        rotateAnim.start()
        val animators: MutableList<Animator> = ArrayList()
        animators.add(scaleAnim)
        animators.add(scaleAnim2)
        animators.add(rotateAnim)
        return animators
    }
}