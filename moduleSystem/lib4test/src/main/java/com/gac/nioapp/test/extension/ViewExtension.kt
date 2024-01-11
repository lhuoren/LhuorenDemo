package com.gac.nioapp.test.extension

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import com.jakewharton.rxbinding3.view.clicks
import com.syy.modulebase.utils.TimeAsyncManager

/**
 * Created by xiaojiang on 2020-02-20.
 */

/**
 * View点击防抖动, 默认间隔1秒
 */
@SuppressLint("CheckResult")
fun View.setOnRxClick(skipDuration: Long = 1000, onClick: (view: View) -> Unit) {
    this.clicks()
        .throttleFirst(skipDuration, TimeUnit.MILLISECONDS)
        .subscribe {
            onClick(this@setOnRxClick)
        }
}

/**
 * View连点拦截, 默认间隔1秒
 * Intercept time
 */
@SuppressLint("CheckResult")
fun View.setOnRxClickIntercept(
    effectiveDuration: Long = 1000,
    onClickTimes: (view: View) -> Unit,
    onClick: (view: View) -> Unit
) {
    this.clicks()
        .map {
            onClickTimes(this@setOnRxClickIntercept)
        }
        .debounce(effectiveDuration, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            onClick(this@setOnRxClickIntercept)
        }
}


@SuppressLint("CheckResult")
fun TextView.setRxChange(
    effectiveDuration: Long = 1200,
    onClick: (view: View) -> Unit
) {
    var create = PublishSubject.create<String>()
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            create.onNext(p0.toString())
        }
    })
    create.debounce(effectiveDuration, TimeUnit.MICROSECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            onClick(this@setRxChange)
        }
}

fun View.gone() {
    if (this.visibility != View.GONE) {
        this.visibility = View.GONE
    }
}


fun View.visible() {
    if (this.visibility != View.VISIBLE) {
        this.visibility = View.VISIBLE
    }
}

fun View.invisible() {
    if (this.visibility != View.INVISIBLE) {
        this.visibility = View.INVISIBLE
    }
}

/**
 * 增加根据布尔值确定View是否显示 true:显示
 */
fun View.setVisible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

private var lastClickTime: Long = 0
private const val SPACE_TIME = 600

/**
 * 是否重复点击
 */
@Synchronized
fun isDoubleClick(): Boolean {
    val currentTime = TimeAsyncManager.getCurrentTimeMillis()
    val isClick2: Boolean = currentTime - lastClickTime <= SPACE_TIME
    lastClickTime = currentTime
    return isClick2
}