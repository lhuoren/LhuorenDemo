package com.gac.nioapp.test.view.topbar

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * 1, 靠左的大标题
 * 2, 上拉时显示居中标题
 *
 *
 * 像这样子使用
<androidx.coordinatorlayout.widget.CoordinatorLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/content"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="@color/backgroundMain"
android:fitsSystemWindows="false">

<com.google.android.material.appbar.AppBarLayout
android:id="@+id/appbar"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:stateListAnimator="@drawable/appbar_elevation">


<com.google.android.material.appbar.CollapsingToolbarLayout
style="@style/CollapsingToolbarStyle"
android:layout_width="match_parent"
android:layout_height="120dp"
app:layout_scrollFlags="scroll|snap|exitUntilCollapsed">

<com.gac.nioapp.test.view.topbar.CollapsibleTitleToolbar
android:id="@+id/about_title_bar"
android:layout_width="match_parent"
android:layout_height="@dimen/height_topbar"
android:background="@color/backgroundMain"
app:TopBarViewShowDivider="false"
app:layout_collapseMode="pin"
app:titleText="标题" />

</com.google.android.material.appbar.CollapsingToolbarLayout>
</com.google.android.material.appbar.AppBarLayout>

<androidx.core.widget.NestedScrollView
android:id="@+id/scrollable"
android:layout_width="match_parent"
android:layout_height="match_parent"
app:layout_behavior="@string/appbar_scrolling_view_behavior">
<!-- content -->
</androidx.core.widget.NestedScrollView>
</androidx.coordinatorlayout.widget.CoordinatorLayout>
 */
class CollapsibleTitleToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TopBarView(context, attrs), AppBarLayout.OnOffsetChangedListener {

    //靠左的大标题的字体大小
    val bigTitleTextSize = 30f

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val alphaF = -verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!
        tvTopBarTitle?.alpha = alphaF
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
//        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
        (parent?.parent as? AppBarLayout)?.addOnOffsetChangedListener(this)

        (parent as? CollapsingToolbarLayout)?.apply {
            val bigTextView = generateBigTitle(context, tvTopBarTitle)

            addView(bigTextView, 0)

            bigTextView.layoutParams = CollapsingToolbarLayout.LayoutParams(-1, -2)
                .apply {
                    gravity = Gravity.BOTTOM
                }
        }
    }


    private fun generateBigTitle(context: Context, orgTitleView: TextView): TextView {
        val bigTextView = TextView(context)
        bigTextView.text = orgTitleView.text
        orgTitleView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                bigTextView.text = s
            }
        })
        bigTextView.setTextColor(orgTitleView.textColors)
        bigTextView.gravity = Gravity.START
        val paddingLeft = (20f * Resources.getSystem().displayMetrics.density).toInt()
        bigTextView.setPadding(paddingLeft, 0, 0, 0)
        bigTextView.textSize = bigTitleTextSize
        val fromAsset = Typeface.createFromAsset(resources.assets, "fonts/Saira-Medium.ttf")
        bigTextView.typeface = fromAsset
        return bigTextView
    }


    fun TextView.setAlpha(alpha: Float) {
        val orgTextColor = currentTextColor
        val a = (alpha * 255.0f + 0.5f).toInt()

        val red: Int = Color.red(orgTextColor)
        val green: Int = Color.green(orgTextColor)
        val blue: Int = Color.blue(orgTextColor)
        setTextColor(Color.argb(a, red, green, blue))
    }
}