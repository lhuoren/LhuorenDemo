package com.lhuoren.custom.xrecycleview.indicator

import android.animation.Animator
import java.util.ArrayList
import android.graphics.RectF
import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.graphics.Paint
import com.lhuoren.custom.xrecycleview.indicator.BaseIndicatorController

/**
 * Created by lhuoren on 2015/10/20.
 */
class SemiCircleSpinIndicator : BaseIndicatorController() {
    override fun draw(canvas: Canvas, paint: Paint) {
        val rectF = RectF(0F, 0F, width.toFloat(), height.toFloat())
        canvas.drawArc(rectF, -60f, 120f, false, paint)
    }

    override fun createAnimation(): List<Animator> {
        val animators: MutableList<Animator> = ArrayList()
        val rotateAnim = ObjectAnimator.ofFloat(target, "rotation", 0f, 180f, 360f)
        rotateAnim.duration = 600
        rotateAnim.repeatCount = -1
        rotateAnim.start()
        animators.add(rotateAnim)
        return animators
    }
}