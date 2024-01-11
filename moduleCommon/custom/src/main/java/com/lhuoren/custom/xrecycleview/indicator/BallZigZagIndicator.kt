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
open class BallZigZagIndicator : BaseIndicatorController() {
    var translateX = FloatArray(2)
    var translateY = FloatArray(2)
    override fun draw(canvas: Canvas, paint: Paint) {
        for (i in 0..1) {
            canvas.save()
            canvas.translate(translateX[i], translateY[i])
            canvas.drawCircle(0f, 0f, (width / 10).toFloat(), paint)
            canvas.restore()
        }
    }

    override fun createAnimation(): List<Animator> {
        val animators: MutableList<Animator> = ArrayList()
        val startX = (width / 6).toFloat()
        val startY = (width / 6).toFloat()
        for (i in 0..1) {
            var translateXAnim =
                ValueAnimator.ofFloat(startX, width - startX, (width / 2).toFloat(), startX)
            if (i == 1) {
                translateXAnim = ValueAnimator.ofFloat(
                    width - startX,
                    startX,
                    (width / 2).toFloat(),
                    width - startX
                )
            }
            var translateYAnim =
                ValueAnimator.ofFloat(startY, startY, (height / 2).toFloat(), startY)
            if (i == 1) {
                translateYAnim = ValueAnimator.ofFloat(
                    height - startY,
                    height - startY,
                    (height / 2).toFloat(),
                    height - startY
                )
            }
            translateXAnim.duration = 1000
            translateXAnim.interpolator = LinearInterpolator()
            translateXAnim.repeatCount = -1
            translateXAnim.addUpdateListener { animation ->
                translateX[i] = animation.animatedValue as Float
                postInvalidate()
            }
            translateXAnim.start()
            translateYAnim.duration = 1000
            translateYAnim.interpolator = LinearInterpolator()
            translateYAnim.repeatCount = -1
            translateYAnim.addUpdateListener { animation ->
                translateY[i] = animation.animatedValue as Float
                postInvalidate()
            }
            translateYAnim.start()
            animators.add(translateXAnim)
            animators.add(translateYAnim)
        }
        return animators
    }
}