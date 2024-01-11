package com.gac.nioapp.test.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.gac.nioapp.test.R
import com.gac.nioapp.test.manager.AppStatusConstant
import com.gac.nioapp.test.view.GrayFrameLayout

/**
 * @package： com.gac.commonui.dialog
 * @describe：
 * @author： liming
 * @time： 3/2/21 1:02 PM
 * @e-mail： liming@gac-nio.com
 */
open class OnCallDialogImpl : BaseDialogFragment.OnCallDialog {
    override fun getDialog(context: Context?): Dialog {
        val dialog = Dialog(context!!, R.style.BottomDialogStyle)
        if (AppStatusConstant.IS_APP_MONOCHROME) {
            val view: View = LayoutInflater.from(context).inflate(layoutResID, null)
            val grayFrameLayout = GrayFrameLayout(context)
            grayFrameLayout.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            grayFrameLayout.addView(view)
            dialog.setContentView(grayFrameLayout)
        } else {
            dialog.setContentView(getLayoutResID())
        }
        return dialog
    }

    override fun getLayoutResID(): Int {
        return 0
    }

}