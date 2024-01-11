package com.gac.nioapp.test.dialog

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.gac.nioapp.test.R
import com.gac.nioapp.test.extension.gone
import com.syy.modulebase.utils.EventConstant

/**
 * Author:tianfengLiang
 * Email:15975026890@163.com
 * Date:2022/8/11
 * Name:AlertDialogSingleButtonFragment
 * Description:
 */
class AlertDialogWithTitleFragment : BaseAlertDialogFragment(), View.OnClickListener {
    private var tvMsg: TextView? = null
    private var tv_right: TextView? = null
    private var tv_left: TextView? = null
    private var tvTitle: TextView? = null
    private var loBackgound: View? = null
    private var cancelable = false
    private var line: View? = null
    override fun initData() {
        val bundle = arguments
        if (bundle != null) {
            cancelable = bundle.getBoolean(CANCELABLE)
            tvMsg!!.text = bundle.getString(MSG)
            tvTitle!!.text = bundle.getString(TITLE)
            tv_right!!.text = bundle.getString(RIGHT_TEXT)
            tv_left!!.text = bundle.getString(LEFT_TEXT)
        }

        if (bundle?.getString(LEFT_TEXT).isNullOrEmpty()) {
            line?.gone()
            tv_left?.gone()
        }

        if (bundle?.getString(RIGHT_TEXT).isNullOrEmpty()) {
            line?.gone()
            tv_right?.gone()
        }
    }

    override fun initView() {
        loBackgound = findViewById(R.id.loBackgound)
        tvMsg = findViewById(R.id.tvMsg)
        tvTitle = findViewById(R.id.tvTitle)
        tv_right = findViewById(R.id.tv_right)
        tv_left = findViewById(R.id.tv_left)
        line = findViewById(R.id.v_line)
    }

    override fun getLayoutResID(): Int {
        return R.layout.dialog_alert_with_confim
    }

    override fun onClick(v: View) {
        super.onClick(v)
        if (v.id == R.id.loBackgound) {
            if (cancelable) dismiss()
            return
        }
        val position =
            if (v.id == R.id.tv_left) EventConstant.ALERT_BUTTON_LEFT else EventConstant.ALERT_BUTTON_RIGHT
        if (onDialogHandleListener != null) {
            onDialogHandleListener.onDialogHandle(null, position)
        }
        dismiss()
    }

    override fun initListener() {
        tv_right!!.setOnClickListener(this)
        tv_left!!.setOnClickListener(this)
        if (cancelable) loBackgound!!.setOnClickListener(this)
    }

    companion object {
        const val MSG = "msg"
        const val TITLE = "TITLE"
        const val RIGHT_TEXT = "RIGHT_TEXT"
        const val CANCELABLE = "cancelable"
        const val LEFT_TEXT = "LEFT_TEXT"

        @JvmStatic
        fun newInstance(
            msg: String?,
            title: String?,
            rightText: String?,
            leftText: String?,
            onDialogHandleListener: BaseDialogFragment.OnDialogHandleListener?,
            cancelable: Boolean
        ): AlertDialogWithTitleFragment {
            val args = Bundle()
            args.putString(MSG, msg)
            args.putString(TITLE, title)
            args.putString(RIGHT_TEXT, rightText)
            args.putString(LEFT_TEXT, leftText)
            args.putBoolean(CANCELABLE, cancelable)
            val fragment = AlertDialogWithTitleFragment()
            fragment.arguments = args
            fragment.onDialogHandleListener = onDialogHandleListener
            return fragment
        }
    }
}