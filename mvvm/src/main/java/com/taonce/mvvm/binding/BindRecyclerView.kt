package com.taonce.mvvm.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taonce.mvvm.base.BaseRecyclerAdapter
import com.taonce.mvvm.base.OnItemClickListener
import com.taonce.mvvm.base.OnItemLongClickListener


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: DataBinding绑定RecyclerView的点击和长按事件
 */

@BindingAdapter("bind:itemClickListener")
fun bindClickListener(rcv: RecyclerView, listener: OnItemClickListener?) {
    rcv.adapter ?: return
    rcv.adapter?.let {
        if (it is BaseRecyclerAdapter<*, *>) {
            it.setOnClickListener(listener)
        }
    }
}

@BindingAdapter("bind:itemLongClickListener")
fun bindLongClickListener(rcv: RecyclerView, listener: OnItemLongClickListener?) {
    rcv.adapter?.let {
        if (it is BaseRecyclerAdapter<*, *>) {
            it.setOnLongClickListener(listener)
        }
    }
}

