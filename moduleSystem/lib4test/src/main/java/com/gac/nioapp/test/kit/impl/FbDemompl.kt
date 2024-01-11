package com.gac.nioapp.test.kit.impl

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.Toast
import com.didichuxing.doraemonkit.util.UIUtils
import com.gac.nioapp.test.R
import com.gac.nioapp.test.dialog.DragFloatActionButton
import com.gac.nioapp.test.kit.HycanAbstractKit
import com.gac.nioapp.test.kit.IDeveloperKit
import com.gac.nioapp.test.utils.EnvironmentManager
import com.gac.nioapp.test.helper.ApplicationHelper
import com.google.auto.service.AutoService

/**
 * @className GoRnActivityImpl
 * @description
 * 添加主页浮动按钮
 * @author heyufei
 * @since 2021/12/3 2:53 下午
 * @version 1.0
 */
@AutoService(IDeveloperKit::class)
class FbDemompl : IDeveloperKit {


    override fun getAbstractKit(): HycanAbstractKit {
        val TAG = "SimpleDemompl"
        return object : HycanAbstractKit() {


//            override val name: Int
//                get() = when (BuildConfig.ENGINEER_NAME) {
//                    "heyufei" -> R.string.user_fly
//                    "fly4work" -> R.string.user_fly
//                    "LiuZhiQiang" -> R.string.user_qiang
//                    "liming" -> R.string.user_min
//                    "jack" -> R.string.user_cheng
//                    else -> R.string.user_jenkins
//                }

            override val name: Int
                get() = R.string.user_lhr

            private var fb1: DragFloatActionButton? = null
            private var fb2: DragFloatActionButton? = null

            private var isShowFBtn = true


            override fun onClickWithReturn(activity: Activity): Boolean {
                if (isShowFBtn) {
                    Toast.makeText(activity, "隐藏首页测试浮动按钮", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "显示首页测试浮动按钮", Toast.LENGTH_SHORT).show()
                }
                isShowFBtn = !isShowFBtn
                showFb(isShowFBtn)

                return true
            }

            override fun onAppInit(context: Context?) {
                GacnioTestSchedulerManager.getInstance().testScheduler
                addFbtButton()
            }

            private fun addFbtButton() {
                ApplicationHelper.getApplication().registerActivityLifecycleCallbacks(
                    object : Application.ActivityLifecycleCallbacks {

                        override fun onActivityCreated(
                            activity: Activity,
                            savedInstanceState: Bundle?
                        ) {
                        }

                        override fun onActivityStarted(activity: Activity) {}

                        override fun onActivityResumed(activity: Activity) {
                            if (activity::class.java.simpleName == "MainActivity") {
                                ApplicationHelper.getApplication()
                                    .unregisterActivityLifecycleCallbacks(this)
                                initFbtnView(activity)
                            } else {
                                Log.d(TAG, "onActivityCreated: ${activity::class.java.simpleName}")
                            }
                        }

                        override fun onActivityPaused(activity: Activity) {}

                        override fun onActivityStopped(activity: Activity) {}

                        override fun onActivitySaveInstanceState(
                            activity: Activity,
                            outState: Bundle
                        ) {
                        }

                        override fun onActivityDestroyed(activity: Activity) {}
                    }
                )
            }

            private fun initFbtnView(activity: Activity) {
                activity.runOnUiThread {
                    /* val rootView =
                         (activity.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as RelativeLayout*/
                    val rootView = activity.findViewById<FrameLayout>(R.id.layout_root_act)
                    if (rootView == null) {

                    }

                    Log.d(TAG, "initFbtnView: $rootView")
                    fb1 = DragFloatActionButton(activity)
                    fb1!!.setImageResource(R.drawable.shape_yellow)
                    fb1!!.id = R.id.layout_for_test

                    val gravity = Gravity.END or Gravity.CENTER_VERTICAL

                    rootView.addView(
                        fb1, FrameLayout.LayoutParams(
                            UIUtils.dp2px(50f), UIUtils.dp2px(50f), gravity
                        )
                    )

                    fb2 = DragFloatActionButton(activity)
                    fb2!!.setText(EnvironmentManager.getShortName(), 12.0f, Color.WHITE)
                    rootView.addView(
                        fb2, FrameLayout.LayoutParams(
                            UIUtils.dp2px(50f), UIUtils.dp2px(50f), gravity
                        ).apply {
                            setMargins(0, UIUtils.dp2px(50f), 0, 0)
                        }
                    )


                    fb1!!.apply {
                        setOnClickListener {
                            GacnioTestSchedulerManager.getInstance().getTestScheduler()
                                .onDragFloatBtnClick(activity)
                        }
                        setOnLongClickListener {
                            GacnioTestSchedulerManager.getInstance().getTestScheduler()
                                .onDragFloatBtnLongClick(activity)
                            true
                        }
                        if (name != R.string.user_jenkins) {
                            show()
                        } else {
                            isShowFBtn = false
                        }
                    }

                    fb2!!.apply {
                        setOnClickListener {
                            GacnioTestSchedulerManager.getInstance().testScheduler
                                .onNextDragFloatBtnClick(activity)
                        }
                        if (name != R.string.user_jenkins) {
                            show()
                        } else {
                            isShowFBtn = false
                        }
                    }
                }


            }

            fun showFb(needShow: Boolean) {
                if (!needShow) {
                    fb1?.hide()
                    fb2?.hide()
                } else {
                    fb1?.show()
                    fb2?.show()
                }
            }
        }
    }

}