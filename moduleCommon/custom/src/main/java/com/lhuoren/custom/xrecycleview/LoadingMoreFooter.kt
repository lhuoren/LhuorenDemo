package com.lhuoren.custom.xrecycleview

import android.content.Context
import android.widget.LinearLayout
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lhuoren.custom.R

/**
 * Created by lhr on 15/11/22.
 */
class LoadingMoreFooter : LinearLayout {
    private var progressCon: SimpleViewSwitcher? = null
    private var mText: TextView? = null
    private var loadingRotationView:LoadingRotationView? = null

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

    fun initView() {
        gravity = Gravity.CENTER
        layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        progressCon = SimpleViewSwitcher(context)
        progressCon!!.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val progressView = AVLoadingIndicatorView(this.context)
        progressView.setIndicatorColor(-0x4a4a4b)
        progressView.setIndicatorId(ProgressStyle.BallSpinFadeLoader)

        loadingRotationView = LoadingRotationView(this.context)
        progressCon!!.setView(progressView)

        addView(progressCon)
        mText = TextView(context)
        mText!!.text = "正在加载..."
        val layoutParams =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(resources.getDimension(R.dimen.dimen_10).toInt(), resources.getDimension(R.dimen.dimen_5).toInt(), 0, resources.getDimension(R.dimen.dimen_5).toInt())
        mText!!.layoutParams = layoutParams
        addView(mText)
    }

    fun setProgressStyle(style: Int) {
        if (style == ProgressStyle.SysProgress) {
            progressCon!!.setView(ProgressBar(context, null, android.R.attr.progressBarStyle))
        } else {
            val progressView = AVLoadingIndicatorView(this.context)
            progressView.setIndicatorColor(-0x4a4a4b)
            progressView.setIndicatorId(style)

            progressCon!!.setView(progressView)
        }
    }

    fun setState(state: Int) {
        when (state) {
            STATE_LOADING -> {
                progressCon!!.visibility = VISIBLE
                loadingRotationView!!.visibility = VISIBLE
                loadingRotationView!!.startAnim()
                mText!!.text = context.getText(R.string.listview_loading)
                this.visibility = VISIBLE
            }
            STATE_COMPLETE -> {
                mText!!.text = context.getText(R.string.listview_loading)
                loadingRotationView!!.visibility = GONE
                loadingRotationView!!.setAnimatorCancel()
                this.visibility = GONE
            }
            STATE_NOMORE -> {
                mText!!.text = context.getText(R.string.nomore_loading)
                progressCon!!.visibility = GONE
                loadingRotationView!!.visibility = GONE
                loadingRotationView!!.setAnimatorCancel()
                this.visibility = VISIBLE
            }
        }
    }

    companion object {
        const val STATE_LOADING = 0
        const val STATE_COMPLETE = 1
        const val STATE_NOMORE = 2
    }
}