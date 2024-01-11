package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint

/**
 * Created by lhuoren on 2015/10/19.
 */
class BallBeatIndicator : BaseIndicatorController() {
    private val scaleFloats = floatArrayOf(
        SCALE,
        SCALE,
        SCALE
    )
    var alphas = intArrayOf(
        ALPHA,
        ALPHA,
        ALPHA
    )

    override fun draw(canvas: Canvas, paint: Paint) {
        val circleSpacing = 4f
        val radius = (width - circleSpacing * 2) / 6
        val x = width / 2 - (radius * 2 + circleSpacing)
        val y = (height / 2).toFloat()
        for (i in 0..2) {
            canvas.save()
            val translateX = x + radius * 2 * i + circleSpacing * i
            canvas.translate(translateX, y)
            canvas.scale(scaleFloats[i], scaleFloats[i])
            paint.alpha = alphas[i]
            canvas.drawCircle(0f, 0f, radius, paint)
            canvas.restore()
        }
    }

    override fun createAnimation(): List<Animator> {
        val animators: MutableList<Animator> = ArrayList()
        val delays = intArrayOf(350, 0, 350)
        for (i in 0..2) {
            val scaleAnim = ValueAnimator.ofFloat(1f, 0.75f, 1f)
            scaleAnim.duration = 700
            scaleAnim.repeatCount = -1
            scaleAnim.startDelay = delays[i].toLong()
            scaleAnim.addUpdateListener { animation ->
                scaleFloats[i] = animation.animatedValue as Float
                postInvalidate()
            }
            scaleAnim.start()
            val alphaAnim = ValueAnimator.ofInt(255, 51, 255)
            alphaAnim.duration = 700
            alphaAnim.repeatCount = -1
            alphaAnim.startDelay = delays[i].toLong()
            alphaAnim.addUpdateListener { animation ->
                alphas[i] = animation.animatedValue as Int
                postInvalidate()
            }
            alphaAnim.start()
            animators.add(scaleAnim)
            animators.add(alphaAnim)
        }
        return animators
    }

    companion object {
        const val SCALE = 1.0f
        const val ALPHA = 255
    }
}