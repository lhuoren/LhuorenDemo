package com.lhuoren.custom.xrecycleview

import android.view.View
import com.lhuoren.custom.xrecycleview.indicator.BaseIndicatorController
import android.util.AttributeSet
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import com.lhuoren.custom.xrecycleview.indicator.BallPulseIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallGridPulseIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallClipRotateIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallClipRotatePulseIndicator
import com.lhuoren.custom.xrecycleview.indicator.SquareSpinIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallClipRotateMultipleIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallPulseRiseIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallRotateIndicator
import com.lhuoren.custom.xrecycleview.indicator.CubeTransitionIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallZigZagIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallZigZagDeflectIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallTrianglePathIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallScaleIndicator
import com.lhuoren.custom.xrecycleview.indicator.LineScaleIndicator
import com.lhuoren.custom.xrecycleview.indicator.LineScalePartyIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallScaleMultipleIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallPulseSyncIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallBeatIndicator
import com.lhuoren.custom.xrecycleview.indicator.LineScalePulseOutIndicator
import com.lhuoren.custom.xrecycleview.indicator.LineScalePulseOutRapidIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallScaleRippleIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallScaleRippleMultipleIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallSpinFadeLoaderIndicator
import com.lhuoren.custom.xrecycleview.indicator.LineSpinFadeLoaderIndicator
import com.lhuoren.custom.xrecycleview.indicator.TriangleSkewSpinIndicator
import com.lhuoren.custom.xrecycleview.indicator.PacmanIndicator
import com.lhuoren.custom.xrecycleview.indicator.BallGridBeatIndicator
import com.lhuoren.custom.xrecycleview.indicator.SemiCircleSpinIndicator
import androidx.annotation.IntDef
import com.lhuoren.custom.R

/**
 * Created by lhr on 2015/10/15
 *
 * .BallPulse,
 * .BallGridPulse,
 * .BallClipRotate,
 * .BallClipRotatePulse,
 * .SquareSpin,
 * .BallClipRotateMultiple,
 * .BallPulseRise,
 * .BallRotate,
 * .CubeTransition,
 * .BallZigZag,
 * .BallZigZagDeflect,
 * .BallTrianglePath,
 * .BallScale,
 * .LineScale,
 * .LineScaleParty,
 * .BallScaleMultiple,
 * .BallPulseSync,
 * .BallBeat,
 * .LineScalePulseOut,
 * .LineScalePulseOutRapid,
 * .BallScaleRipple,
 * .BallScaleRippleMultiple,
 * .BallSpinFadeLoader,
 * .LineSpinFadeLoader,
 * .TriangleSkewSpin,
 * .Pacman,
 * .BallGridBeat,
 * .SemiCircleSpin
 *
 */
class AVLoadingIndicatorView : View {
    @IntDef(
        flag = true,
        value = [BallPulse, BallGridPulse, BallClipRotate, BallClipRotatePulse, SquareSpin, BallClipRotateMultiple, BallPulseRise, BallRotate, CubeTransition, BallZigZag, BallZigZagDeflect, BallTrianglePath, BallScale, LineScale, LineScaleParty, BallScaleMultiple, BallPulseSync, BallBeat, LineScalePulseOut, LineScalePulseOutRapid, BallScaleRipple, BallScaleRippleMultiple, BallSpinFadeLoader, LineSpinFadeLoader, TriangleSkewSpin, Pacman, BallGridBeat, SemiCircleSpin]
    )
    annotation class Indicator

    //attrs
    var mIndicatorId = 0
    var mIndicatorColor = 0
    var mPaint: Paint? = null
    var mIndicatorController: BaseIndicatorController? = null
    private var mHasAnimation = false

    constructor(context: Context?) : super(context) {
        init(null, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AVLoadingIndicatorView)
        mIndicatorId = a.getInt(R.styleable.AVLoadingIndicatorView_indicator, BallPulse)
        mIndicatorColor =
            a.getColor(R.styleable.AVLoadingIndicatorView_indicator_color, Color.WHITE)
        a.recycle()
        mPaint = Paint()
        mPaint!!.color = mIndicatorColor
        mPaint!!.style = Paint.Style.FILL
        mPaint!!.isAntiAlias = true
        applyIndicator()
    }

    fun setIndicatorId(indicatorId: Int) {
        mIndicatorId = indicatorId
        applyIndicator()
    }

    fun setIndicatorColor(color: Int) {
        mIndicatorColor = color
        mPaint!!.color = mIndicatorColor
        this.invalidate()
    }

    private fun applyIndicator() {
        when (mIndicatorId) {
            BallPulse -> mIndicatorController = BallPulseIndicator()
            BallGridPulse -> mIndicatorController = BallGridPulseIndicator()
            BallClipRotate -> mIndicatorController = BallClipRotateIndicator()
            BallClipRotatePulse -> mIndicatorController = BallClipRotatePulseIndicator()
            SquareSpin -> mIndicatorController = SquareSpinIndicator()
            BallClipRotateMultiple -> mIndicatorController = BallClipRotateMultipleIndicator()
            BallPulseRise -> mIndicatorController = BallPulseRiseIndicator()
            BallRotate -> mIndicatorController = BallRotateIndicator()
            CubeTransition -> mIndicatorController = CubeTransitionIndicator()
            BallZigZag -> mIndicatorController = BallZigZagIndicator()
            BallZigZagDeflect -> mIndicatorController = BallZigZagDeflectIndicator()
            BallTrianglePath -> mIndicatorController = BallTrianglePathIndicator()
            BallScale -> mIndicatorController = BallScaleIndicator()
            LineScale -> mIndicatorController = LineScaleIndicator()
            LineScaleParty -> mIndicatorController = LineScalePartyIndicator()
            BallScaleMultiple -> mIndicatorController = BallScaleMultipleIndicator()
            BallPulseSync -> mIndicatorController = BallPulseSyncIndicator()
            BallBeat -> mIndicatorController = BallBeatIndicator()
            LineScalePulseOut -> mIndicatorController = LineScalePulseOutIndicator()
            LineScalePulseOutRapid -> mIndicatorController = LineScalePulseOutRapidIndicator()
            BallScaleRipple -> mIndicatorController = BallScaleRippleIndicator()
            BallScaleRippleMultiple -> mIndicatorController = BallScaleRippleMultipleIndicator()
            BallSpinFadeLoader -> mIndicatorController = BallSpinFadeLoaderIndicator()
            LineSpinFadeLoader -> mIndicatorController = LineSpinFadeLoaderIndicator()
            TriangleSkewSpin -> mIndicatorController = TriangleSkewSpinIndicator()
            Pacman -> mIndicatorController = PacmanIndicator()
            BallGridBeat -> mIndicatorController = BallGridBeatIndicator()
            SemiCircleSpin -> mIndicatorController = SemiCircleSpinIndicator()
        }
        mIndicatorController!!.target = this
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureDimension(dp2px(DEFAULT_SIZE), widthMeasureSpec)
        val height = measureDimension(dp2px(DEFAULT_SIZE), heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    private fun measureDimension(defaultSize: Int, measureSpec: Int): Int {
        var result = defaultSize
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        result = when (specMode) {
            MeasureSpec.EXACTLY -> {
                specSize
            }
            MeasureSpec.AT_MOST -> {
                defaultSize.coerceAtMost(specSize)
            }
            else -> {
                defaultSize
            }
        }
        return result
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawIndicator(canvas)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (!mHasAnimation) {
            mHasAnimation = true
            applyAnimation()
        }
    }

    override fun setVisibility(v: Int) {
        if (visibility != v) {
            super.setVisibility(v)
            if (v == GONE || v == INVISIBLE) {
                mIndicatorController!!.setAnimationStatus(BaseIndicatorController.AnimStatus.END)
            } else {
                mIndicatorController!!.setAnimationStatus(BaseIndicatorController.AnimStatus.START)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mIndicatorController!!.setAnimationStatus(BaseIndicatorController.AnimStatus.CANCEL)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mIndicatorController!!.setAnimationStatus(BaseIndicatorController.AnimStatus.START)
    }

    fun drawIndicator(canvas: Canvas?) {
        if (canvas != null) {
            mPaint?.let { mIndicatorController!!.draw(canvas, it) }
        }
    }

    fun applyAnimation() {
        mIndicatorController!!.initAnimation()
    }

    private fun dp2px(dpValue: Int): Int {
        return context.resources.displayMetrics.density.toInt() * dpValue
    }

    companion object {
        //indicators
        const val BallPulse = 0
        const val BallGridPulse = 1
        const val BallClipRotate = 2
        const val BallClipRotatePulse = 3
        const val SquareSpin = 4
        const val BallClipRotateMultiple = 5
        const val BallPulseRise = 6
        const val BallRotate = 7
        const val CubeTransition = 8
        const val BallZigZag = 9
        const val BallZigZagDeflect = 10
        const val BallTrianglePath = 11
        const val BallScale = 12
        const val LineScale = 13
        const val LineScaleParty = 14
        const val BallScaleMultiple = 15
        const val BallPulseSync = 16
        const val BallBeat = 17
        const val LineScalePulseOut = 18
        const val LineScalePulseOutRapid = 19
        const val BallScaleRipple = 20
        const val BallScaleRippleMultiple = 21
        const val BallSpinFadeLoader = 22
        const val LineSpinFadeLoader = 23
        const val TriangleSkewSpin = 24
        const val Pacman = 25
        const val BallGridBeat = 26
        const val SemiCircleSpin = 27

        //Sizes (with defaults in DP)
        const val DEFAULT_SIZE = 30
    }
}