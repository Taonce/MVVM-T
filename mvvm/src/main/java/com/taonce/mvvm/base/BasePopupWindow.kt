package com.taonce.mvvm.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.taonce.mvvm.R

/**
 * @author Taonce.
 * @description
 */
@Suppress("LeakingThis")
abstract class BasePopupWindow<VDB : ViewDataBinding>(
    context: Context,
    animStyle: Int = R.style.PopWindowAnimation,
    width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
) : PopupWindow(context) {

    protected val mDataBinding: VDB

    init {
        mDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutId(), null, false)
        contentView = mDataBinding.root
        animationStyle = animStyle
        setWidth(width)
        setHeight(height)
        work()
    }

    abstract fun getLayoutId(): Int

    abstract fun work()
}