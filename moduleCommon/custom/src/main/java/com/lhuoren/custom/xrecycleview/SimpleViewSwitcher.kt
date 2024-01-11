package com.lhuoren.custom.xrecycleview

import android.content.Context
import android.view.ViewGroup
import android.util.AttributeSet
import android.view.View

/**
 * Created by lhr on 15/11/22.
 */
class SimpleViewSwitcher : ViewGroup {
    constructor(context: Context?) : super(context) {}

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int = 0) : super(
        context,
        attrs,
        defStyle
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val childCount = this.childCount
        var maxHeight = 0
        var maxWidth = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val cw = child.measuredWidth
            // int ch = child.getMeasuredHeight();
            maxWidth = child.measuredWidth
            maxHeight = child.measuredHeight
        }
        setMeasuredDimension(maxWidth, maxHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        for (i in 0 until count) {
            val child = getChildAt(i)
            if (child.visibility != GONE) {
                child.layout(0, 0, r - l, b - t)
            }
        }
    }

    fun setView(view: View?) {
        if (this.childCount != 0) {
            removeViewAt(0)
        }
        this.addView(view, 0)
    }
}