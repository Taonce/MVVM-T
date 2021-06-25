package com.taonce.mvvm.base

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.taonce.mvvm.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author Taonce.
 * @description PopupWindow基类
 */
@Suppress("LeakingThis")
abstract class BasePopupWindow<VB : ViewBinding>(
    context: Context,
    animStyle: Int = R.style.PopWindowAnimation,
    width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    gravity: Int = Gravity.BOTTOM
) : PopupWindow(context), CoroutineScope by MainScope() {
    protected val mViewBinding: VB by lazy { getViewBinding() }

    init {
        contentView = mViewBinding.root
        animationStyle = animStyle
        setWidth(width)
        setHeight(height)
        isOutsideTouchable = true
        setBackgroundDrawable(null)
        showAtLocation(contentView.rootView, gravity, 0, 0)
        work()
    }

    abstract fun getViewBinding(): VB

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