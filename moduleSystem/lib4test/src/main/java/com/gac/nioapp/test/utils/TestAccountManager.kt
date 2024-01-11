package com.gac.nioapp.test.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.gac.nioapp.test.R
import com.gac.nioapp.test.bean.TestUserBean
import com.gac.nioapp.test.dialog.SwitchTestAccountDialog
import com.gac.nioapp.test.screen.ScreenUtils
import com.syy.modulebase.utils.SharePreferenceUtil
import com.syy.modulebase.utils.ToastUtil
import com.tencent.mars.xlog.Log
import java.util.*

/**
 * @ClassName TestAccountManager
 * @Description TODO
 * @Author LiuZhiQiang
 * @Date 2021/12/28 10:29
 */
class TestAccountManager {
    private val TAG = "TestAccountManager"
    private val TEST_USER = "testAccount"

    private var mActivity: AppCompatActivity? = null
    private var desList = ArrayList<String>()
    private var testUserList = LinkedList<TestUserBean>()
    private var dialog: SwitchTestAccountDialog? = null
    private var selectItem = 0
    private lateinit var desAdapter: ArrayAdapter<String>

    fun initSpinner(activity: Activity, spinner: Spinner, switchTestAccountDialog: SwitchTestAccountDialog) {
        mActivity = activity as AppCompatActivity
        dialog = switchTestAccountDialog
        getList(activity)
        selectItem = adapterInit()
        desAdapter = ArrayAdapter(mActivity!!, R.layout.spinner_item, R.id.text, desList)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            spinner.dropDownVerticalOffset = ScreenUtils.dp2px(activity, 50f)
        }
        spinner.adapter = desAdapter
        spinner.setSelection(selectItem, false)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                Log.i(TAG, "----position=$position")
                switchAccount(selectItem, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i(TAG, "---onNothingSelected-")
            }
        }
    }

    private fun adapterInit(): Int {
        desList.clear()
        for (i in 0 until testUserList.size) {
            if (testUserList[i].isSelect) {
                selectItem = i
            }
            desList.add(testUserList[i].testAccount + "/" + testUserList[i].accountContext)
        }
        return selectItem
    }

    private fun getList(activity: Activity) {
        var listData = SharedPreferenceUtils.getListData(activity.getSharedPreferences(
            SharePreferenceUtil.SAVE_FILE_NAME, Context.MODE_PRIVATE), TEST_USER, TestUserBean::class.java)
        if (listData.size <= 0) {
            initData()
        } else {
            testUserList.clear()
            testUserList.addAll(listData)
        }
    }

    fun addTestAccount(account: String, content: String): Boolean {
        if (testUserList == null) {
            getList(mActivity!!)
        }
        var newTestUserBean:TestUserBean? = null
        for (testUserBean in testUserList) {
            if (testUserBean.testAccount == account) {
                newTestUserBean = testUserBean
            }
        }
        if (newTestUserBean ==null) {
            testUserList.add(TestUserBean(account, content))
        }else{
            testUserList.remove(newTestUserBean)
        }
        selectItem = adapterInit()
        desAdapter.notifyDataSetChanged()
        putList()
        ToastUtil.toastShortMessage("成功")
        return true
    }

    private fun initData() {
        testUserList.apply {
            add(TestUserBean(
                    "15999934963", "预约维保", true
            ))
            add(TestUserBean(
                    "18268184435", "车联车控"
            ))
            add(TestUserBean(
                    "15315091671", "车联车控"
            ))
        }
        putList()
    }

    private fun putList() {

        SharedPreferenceUtils.putData(mActivity!!.getSharedPreferences(SharePreferenceUtil.SAVE_FILE_NAME, Context.MODE_PRIVATE), TEST_USER, testUserList)
    }

    private fun switchAccount(oldSelect: Int, newSelect: Int) {
        if (oldSelect != newSelect) {
            testUserList[oldSelect].isSelect = false
            testUserList[newSelect].isSelect = true
            putList()
        }
        ToastUtil.toastShortMessage("切换成功")
        dialog?.dismiss()
    }
}