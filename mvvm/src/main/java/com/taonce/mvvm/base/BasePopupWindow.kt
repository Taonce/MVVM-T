package com.taonce.mvvm.base

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.taonce.mvvm.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author Taonce.
 * @description
 */
@Suppress("LeakingThis")
abstract class BasePopupWindow(
    context: Context,
    animStyle: Int = R.style.PopWindowAnimation,
    width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    gravity: Int = Gravity.BOTTOM
) : PopupWindow(context), CoroutineScope by MainScope() {

    init {
        contentView = LayoutInflater.from(context).inflate(
            getLayoutId(), null, false
        )
        animationStyle = animStyle
        setWidth(width)
        setHeight(height)
        isOutsideTouchable = true
        setBackgroundDrawable(null)
        showAtLocation(contentView.rootView, gravity, 0, 0)
        work()
    }

    abstract fun getLayoutId(): Int

    abstract fun work()

    /**
     * 处理返回键[AppCompatActivity.onBackPressed]
     */
    fun onBackPressed() {
        if (isShowing) {
            dismiss()
        }
    }

    override fun dismiss() {
        super.dismiss()
        cancel()
    }
}