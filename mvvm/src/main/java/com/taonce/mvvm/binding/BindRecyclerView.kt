package com.taonce.mvvm.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taonce.mvvm.base.BaseRecyclerAdapter


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: DataBinding绑定RecyclerView的点击和长按事件
 */

interface OnClickListener {
    fun click(position: Int, view: View)
}

interface OnLongClickListener {
    fun longClick(position: Int, view: View)
}

@BindingAdapter("clickListener")
fun BindClickListener(rcv: RecyclerView, listener: OnClickListener) {
    rcv.adapter?.let {
        if (it is BaseRecyclerAdapter<*>) {
            it.setOnClickListener(listener)
        }
    }
}

@BindingAdapter("longClickListener")
fun BindLongClickListener(rcv: RecyclerView, listener: OnLongClickListener) {
    rcv.adapter?.let {
        if (it is BaseRecyclerAdapter<*>) {
            it.setOnLongClickListener(listener)
        }
    }
}

