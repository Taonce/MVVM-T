package com.taonce.mvvm.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.taonce.mvvm.base.BaseRecyclerAdapter
import com.taonce.mvvm.base.OnItemClickListener
import com.taonce.mvvm.base.OnItemLongClickListener
import com.taonce.mvvm.base.OnLoadMoreListener
import com.taonce.mvvm.base.BaseListActivity
import com.taonce.mvvm.base.BaseListFragment


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: DataBinding绑定RecyclerView的点击、长按事件和加载更多事件
 */

/**
 * 继承了[BaseListFragment]或者[BaseListActivity]之后，就无需用db绑定事件了
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

@BindingAdapter("bind:loadMore")
fun bindLoadMore(rcv: RecyclerView, listener: OnLoadMoreListener) {
    rcv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            val layoutManager = recyclerView.layoutManager
            val totalCount = recyclerView.adapter?.itemCount ?: 0
            val lastItemPosition = when (layoutManager) {
                is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                is StaggeredGridLayoutManager -> {
                    layoutManager.run {
                        layoutManager.findLastVisibleItemPositions(intArrayOf(spanCount))[0]
                    }
                }
                else -> 0
            }
            val visibleItemCount = recyclerView.childCount
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                && lastItemPosition == (totalCount - 1)
                && visibleItemCount > 0
            ) {
                listener.loadMore()
            }
        }
    })
}

