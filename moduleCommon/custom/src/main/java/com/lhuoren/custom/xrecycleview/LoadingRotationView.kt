package com.lhuoren.custom.xrecycleview

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.lhuoren.custom.R
import java.io.InputStream
import kotlin.math.atan2
import kotlin.math.min

class LoadingRotationView : View {

    constructor(context: Context) : super(context) {
        initRes(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initRes(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initRes(context, attrs, defStyleAttr)
    }

    private val TAG = "LoadingRotationView"

    private var mPath: Path? = null
    private var mPaint: Paint? = null
    private var mBitmap: Bitmap? = null
    private var mPathMeasure: PathMeasure? = null
    private var mPoint: FloatArray? = null
    private var mTan: FloatArray? = null
    private var mDdegrees = 0f
    private var animator: ValueAnimator? = null

    @SuppressLint("ResourceType")
    private fun initRes(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        mPaint = Paint()
        mPaint!!.color = Color.TRANSPARENT
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = 1f
    }

    fun setPath(path: Path?) {
        mPath = path
        mPathMeasure = PathMeasure(path, false)
        mPoint = FloatArray(2)
        mTan = FloatArray(2)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mPath == null) {
            return
        }

        canvas.rotate(
            4.let { mDdegrees += it; mDdegrees },
            ((mBitmap?.width!! + 1) / 2).toFloat(),
            (mBitmap!!.height / 2).toFloat()
        )
        canvas.drawPath(mPath!!, mPaint!!)
        val degress = mTan!![1]?.toDouble()?.let {
            atan2(
                it,
                mTan!![0].toDouble()
            )
        }?.let {
            Math.toDegrees(
                it
            ).toFloat()
        }
        degress?.let {
            matrix.postRotate(
                it,
                ((mBitmap?.width!! + 1) / 2).toFloat(),
                (mBitmap!!.height / 2).toFloat()
            )
        }
//        matrix.postTranslate(mPoint!![0] - (mBitmap?.width!! +1) / 2, mPoint!![1] - mBitmap!!.height / 2)
        canvas.drawBitmap(mBitmap!!, matrix, null)
    }

    /**
     * 对外接口，开始动画
     */

    @SuppressLint("ResourceType")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun startAnim() {

        if (mBitmap == null) {
            val inputStream: InputStream = resources.openRawResource(R.mipmap.sentry_model_loading)
            mBitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
        }

        val path = Path()
        path!!.addCircle(
            (mBitmap?.width!! + 1)!! / 2F,
            mBitmap?.height!! / 2F,
            10F,
            Path.Direction.CW
        )
        setPath(path)

        if (animator == null) {
            animator = ValueAnimator.ofFloat(0f, mPathMeasure!!.length)
            animator?.duration = 2000
            animator?.interpolator = LinearInterpolator() //插值器
            animator?.repeatCount = ValueAnimator.INFINITE
            animator?.addUpdateListener { animation ->
                val distance = animation.animatedValue as Float
                mPathMeasure!!.getPosTan(distance, mPoint, mTan)
                invalidate()
            }
            animator?.start()
        }

    }

    /**
     * 对外接口，取消动画
     */
    fun setAnimatorCancel() {
        if (mBitmap != null) {
            mBitmap!!.recycle()
            mBitmap = null
        }

        if (animator != null) {
            animator!!.cancel()
            animator = null
        }
    }
}