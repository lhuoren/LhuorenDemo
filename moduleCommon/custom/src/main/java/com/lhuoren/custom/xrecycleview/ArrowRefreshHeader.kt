package com.lhuoren.custom.xrecycleview

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.lhuoren.custom.xrecycleview.BaseRefreshHeader.Companion.STATE_DONE
import com.lhuoren.custom.xrecycleview.BaseRefreshHeader.Companion.STATE_NORMAL
import com.lhuoren.custom.xrecycleview.BaseRefreshHeader.Companion.STATE_REFRESHING
import com.lhuoren.custom.xrecycleview.BaseRefreshHeader.Companion.STATE_RELEASE_TO_REFRESH
import com.lhuoren.custom.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by lhr on 15/11/22.
 */
class ArrowRefreshHeader : LinearLayout, BaseRefreshHeader {
    private var mContainer: LinearLayout? = null
    private var mArrowImageView: ImageView? = null
    private var mProgressBar: SimpleViewSwitcher? = null
    private var mStatusTextView: TextView? = null
    private var mState = STATE_NORMAL
    private var mHeaderTimeView: TextView? = null
    private var mRotateUpAnim: Animation? = null
    private var mRotateDownAnim: Animation? = null
    var mMeasuredHeight = 0

    constructor(context: Context?) : super(context) {
        initView()
    }

    /**
     * @param context
     * @param attrs
     */
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        // 初始情况，设置下拉刷新view高度为0
        mContainer = LayoutInflater.from(context).inflate(
            R.layout.listview_header, null
        ) as LinearLayout
        val lp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        lp.setMargins(0, 0, 0, 0)
        this.layoutParams = lp
        setPadding(0, 0, 0, 0)
        addView(mContainer, LayoutParams(LayoutParams.MATCH_PARENT, 0))
        gravity = Gravity.BOTTOM
        mArrowImageView = findViewById<View>(R.id.listview_header_arrow) as ImageView
        mStatusTextView = findViewById<View>(R.id.refresh_status_textview) as TextView

        //init the progress view
        mProgressBar = findViewById<View>(R.id.listview_header_progressbar) as SimpleViewSwitcher
        val progressView = AVLoadingIndicatorView(context)
        progressView.setIndicatorColor(-0x4a4a4b)
        progressView.setIndicatorId(ProgressStyle.BallSpinFadeLoader)
        mProgressBar!!.setView(progressView)
        mRotateUpAnim = RotateAnimation(
            0.0f, -180.0f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        (mRotateUpAnim as RotateAnimation).duration = ROTATE_ANIM_DURATION.toLong()
        (mRotateUpAnim as RotateAnimation).fillAfter = true
        mRotateDownAnim = RotateAnimation(
            -180.0f, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        (mRotateDownAnim as RotateAnimation).duration = ROTATE_ANIM_DURATION.toLong()
        (mRotateDownAnim as RotateAnimation).fillAfter = true
        mHeaderTimeView = findViewById<View>(R.id.last_refresh_time) as TextView
        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        mMeasuredHeight = measuredHeight
    }

    fun setProgressStyle(style: Int) {
        if (style == ProgressStyle.SysProgress) {
            mProgressBar!!.setView(ProgressBar(context, null, android.R.attr.progressBarStyle))
        } else {
            val progressView = AVLoadingIndicatorView(this.context)
            progressView.setIndicatorColor(-0x4a4a4b)
            progressView.setIndicatorId(style)
            mProgressBar!!.setView(progressView)
        }
    }

    fun setArrowImageView(resid: Int) {
        mArrowImageView!!.setImageResource(resid)
    }

    fun setState(state: Int) {
        if (state == mState) return
        when (state) {
            STATE_REFRESHING -> {    // 显示进度
                mArrowImageView!!.clearAnimation()
                mArrowImageView!!.visibility = INVISIBLE
                mProgressBar!!.visibility = VISIBLE
            }
            STATE_DONE -> {
                mArrowImageView!!.visibility = INVISIBLE
                mProgressBar!!.visibility = INVISIBLE
            }
            else -> {    // 显示箭头图片
                mArrowImageView!!.visibility = VISIBLE
                mProgressBar!!.visibility = INVISIBLE
            }
        }
        when (state) {
            STATE_NORMAL -> {
                if (mState == STATE_RELEASE_TO_REFRESH) {
                    mArrowImageView!!.startAnimation(mRotateDownAnim)
                }
                if (mState == STATE_REFRESHING) {
                    mArrowImageView!!.clearAnimation()
                }
                mStatusTextView!!.setText(R.string.listview_header_hint_normal)
            }
            STATE_RELEASE_TO_REFRESH -> if (mState != STATE_RELEASE_TO_REFRESH) {
                mArrowImageView!!.clearAnimation()
                mArrowImageView!!.startAnimation(mRotateUpAnim)
                mStatusTextView!!.setText(R.string.listview_header_hint_release)
            }
            STATE_REFRESHING -> mStatusTextView!!.setText(R.string.refreshing)
            STATE_DONE -> mStatusTextView!!.setText(R.string.refresh_done)
            else -> {}
        }
        mState = state
    }

    fun getState(): Int {
        return mState
    }

    override fun refreshComplete() {
        mHeaderTimeView!!.text = friendlyTime(Date())
        setState(STATE_DONE)
        MainScope().launch {
            delay(200)
            reset()
        }
    }

    fun setVisibleHeight(height: Int) {
        var height = height
        if (height < 0) height = 0
        val lp = mContainer!!.layoutParams as LayoutParams
        lp.height = height
        mContainer!!.layoutParams = lp
    }

    fun getVisibleHeight(): Int {
        val lp = mContainer!!.layoutParams as LayoutParams
        return lp.height
    }

    override fun onMove(delta: Float) {
        if (getVisibleHeight() > 0 || delta > 0) {
            setVisibleHeight(delta.toInt() + getVisibleHeight())
            if (mState <= STATE_RELEASE_TO_REFRESH) { // 未处于刷新状态，更新箭头
                if (getVisibleHeight() > mMeasuredHeight) {
                    setState(STATE_RELEASE_TO_REFRESH)
                } else {
                    setState(STATE_NORMAL)
                }
            }
        }
    }

    override fun releaseAction(): Boolean {
        var isOnRefresh = false
        val height = getVisibleHeight()
        if (height == 0) // not visible.
            isOnRefresh = false
        if (getVisibleHeight() > mMeasuredHeight && mState < STATE_REFRESHING) {
            setState(STATE_REFRESHING)
            isOnRefresh = true
        }
        // refreshing and header isn't shown fully. do nothing.
        if (mState == STATE_REFRESHING && height <= mMeasuredHeight) {
            //return;
        }
        var destHeight = 0 // default: scroll back to dismiss header.
        // is refreshing, just scroll back to show all the header.
        if (mState == STATE_REFRESHING) {
            destHeight = mMeasuredHeight
        }
        smoothScrollTo(destHeight)
        return isOnRefresh
    }

    fun reset() {
        smoothScrollTo(0)

        MainScope().launch {
            delay(500)
            setState(STATE_NORMAL)
        }
    }

    private fun smoothScrollTo(destHeight: Int) {
        val animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight)
        animator.setDuration(300).start()
        animator.addUpdateListener { animation -> setVisibleHeight(animation.animatedValue as Int) }
        animator.start()
    }

    companion object {
        private const val ROTATE_ANIM_DURATION = 180
        fun friendlyTime(time: Date): String {
            //获取time距离当前的秒数
            val ct = ((System.currentTimeMillis() - time.time) / 1000).toInt()
            if (ct == 0) {
                return "刚刚"
            }
            if (ct in 1..59) {
                return ct.toString() + "秒前"
            }
            if (ct in 60..3599) {
                return (ct / 60).coerceAtLeast(1).toString() + "分钟前"
            }
            if (ct in 3600..86399) return (ct / 3600).toString() + "小时前"
            if (ct in 86400..2591999) { //86400 * 30
                val day = ct / 86400
                return day.toString() + "天前"
            }
            return if (ct in 2592000..31103999) { //86400 * 30
                (ct / 2592000).toString() + "月前"
            } else (ct / 31104000).toString() + "年前"
        }
    }
}