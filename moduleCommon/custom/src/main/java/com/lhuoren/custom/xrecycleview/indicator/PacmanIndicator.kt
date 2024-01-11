package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.animation.ValueAnimator
import android.graphics.RectF
import android.graphics.Canvas
import android.graphics.Paint
import android.view.animation.LinearInterpolator
import com.lhuoren.custom.xrecycleview.indicator.BaseIndicatorController

/**
 * Created by lhuoren on 2015/10/16.
 */
class PacmanIndicator : BaseIndicatorController() {
    private var translateX = 0f
    private var alpha = 0
    private var degrees1 = 0f
    private var degrees2 = 0f
    override fun draw(canvas: Canvas, paint: Paint) {
        drawPacman(canvas, paint)
        drawCircle(canvas, paint)
    }

    private fun drawPacman(canvas: Canvas, paint: Paint) {
        val x = (width / 2).toFloat()
        val y = (height / 2).toFloat()
        canvas.save()
        canvas.translate(x, y)
        canvas.rotate(degrees1)
        paint.alpha = 255
        val rectF1 = RectF(-x / 1.7f, -y / 1.7f, x / 1.7f, y / 1.7f)
        canvas.drawArc(rectF1, 0f, 270f, true, paint)
        canvas.restore()
        canvas.save()
        canvas.translate(x, y)
        canvas.rotate(degrees2)
        paint.alpha = 255
        val rectF2 = RectF(-x / 1.7f, -y / 1.7f, x / 1.7f, y / 1.7f)
        canvas.drawArc(rectF2, 90f, 270f, true, paint)
        canvas.restore()
    }

    private fun drawCircle(canvas: Canvas, paint: Paint) {
        val radius = (width / 11).toFloat()
        paint.alpha = alpha
        canvas.drawCircle(translateX, (height / 2).toFloat(), radius, paint)
    }

    override fun createAnimation(): List<Animator> {
        val animators: MutableList<Animator> = ArrayList()
        val startT = (width / 11).toFloat()
        val translationAnim = ValueAnimator.ofFloat(width - startT, (width / 2).toFloat())
        translationAnim.duration = 650
        translationAnim.interpolator = LinearInterpolator()
        translationAnim.repeatCount = -1
        translationAnim.addUpdateListener { animation ->
            translateX = animation.animatedValue as Float
            postInvalidate()
        }
        translationAnim.start()
        val alphaAnim = ValueAnimator.ofInt(255, 122)
        alphaAnim.duration = 650
        alphaAnim.repeatCount = -1
        alphaAnim.addUpdateListener { animation ->
            alpha = animation.animatedValue as Int
            postInvalidate()
        }
        alphaAnim.start()
        val rotateAnim1 = ValueAnimator.ofFloat(0f, 45f, 0f)
        rotateAnim1.duration = 650
        rotateAnim1.repeatCount = -1
        rotateAnim1.addUpdateListener { animation ->
            degrees1 = animation.animatedValue as Float
            postInvalidate()
        }
        rotateAnim1.start()
        val rotateAnim2 = ValueAnimator.ofFloat(0f, -45f, 0f)
        rotateAnim2.duration = 650
        rotateAnim2.repeatCount = -1
        rotateAnim2.addUpdateListener { animation ->
            degrees2 = animation.animatedValue as Float
            postInvalidate()
        }
        rotateAnim2.start()
        animators.add(translationAnim)
        animators.add(alphaAnim)
        animators.add(rotateAnim1)
        animators.add(rotateAnim2)
        return animators
    }
}