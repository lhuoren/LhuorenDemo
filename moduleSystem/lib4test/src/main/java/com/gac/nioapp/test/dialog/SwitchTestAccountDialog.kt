package com.gac.nioapp.test.dialog


import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gac.nioapp.test.R
import com.gac.nioapp.test.utils.TestAccountManager

/**
 * @ClassName SwitchEnvironmentDialog
 * @Description TODO
 * @Author LiuZhiQiang
 * @Date 2021/12/27 10:13
 */
class SwitchTestAccountDialog() : BaseAlertDialogFragment() {

    //    private var onDialogHandleListener = null
    private lateinit var activity: AppCompatActivity
    private lateinit var spinner: Spinner
    private lateinit var cancel: TextView
    private lateinit var confirm: TextView
    private lateinit var editAccount: EditText
    private lateinit var editContent: EditText

    companion object {
        @JvmStatic
        fun newInstance(activity: AppCompatActivity) = SwitchTestAccountDialog().apply {
            this.activity = activity
        }
    }

    override fun initData() {
        spinner = findViewById(R.id.spinner)
        cancel = findViewById(R.id.cancel)
        confirm = findViewById(R.id.confirm)
        editAccount = findViewById(R.id.et_add_account)
        editContent = findViewById(R.id.et_add_content)
        var testAccountManager = TestAccountManager()
        testAccountManager.initSpinner(activity, spinner, this)
        cancel.setOnClickListener {
            dismiss()
        }
        confirm.setOnClickListener {
            if (editAccount.text.isNotEmpty() && editContent.text.isNotEmpty()) {
                if (testAccountManager.addTestAccount(editAccount.text.toString(), editContent.text.toString())) {
                    dismiss()
                }
            } else {
                showToast("输入不能为空")
            }
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
        return R.layout.dialog_switch_account
    }

}