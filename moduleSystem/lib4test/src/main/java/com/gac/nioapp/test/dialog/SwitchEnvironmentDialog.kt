package com.gac.nioapp.test.dialog


import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gac.nioapp.test.R
import com.gac.nioapp.test.utils.EnvironmentManager

/**
 * @ClassName SwitchEnvironmentDialog
 * @Description TODO
 * @Author LiuZhiQiang
 * @Date 2021/12/27 10:13
 */
class SwitchEnvironmentDialog() : BaseAlertDialogFragment() {

    //    private var onDialogHandleListener = null
    private lateinit var activity: AppCompatActivity
    private lateinit var spinner: Spinner
    private lateinit var cancel: TextView

    companion object {
        @JvmStatic
        fun newInstance(activity: AppCompatActivity) = SwitchEnvironmentDialog().apply {
            this.activity = activity
        }
    }

    override fun initData() {
        spinner = findViewById(R.id.spinner)
        cancel = findViewById(R.id.cancel)
        EnvironmentManager().initSpinner(activity, spinner)
        cancel.setOnClickListener {
            dismiss()
        }
    }

    fun showDialog() {
        show(activity.supportFragmentManager, TAG)
    }

    override fun initListener() {

    }

    override fun initView() {


    }


    override fun getLayoutResID(): Int {
        return R.layout.dialog_switch_environment
    }

}