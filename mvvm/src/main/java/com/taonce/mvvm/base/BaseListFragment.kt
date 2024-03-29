package com.taonce.mvvm.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


/**
 * @author Taonce
 * @description 带列表的[Fragment]
 * 单击和长按事件如果没设置的话直接返回null
 */
abstract class BaseListFragment<vb : ViewBinding, A : BaseRecyclerAdapter<*, *>>
    : BaseFragment<vb>() {

    protected val adapter by lazy { getRecyclerAdapter() }

    abstract fun getRecyclerAdapter(): A

    open fun getItemClickListener(): OnItemClickListener? = null

    open fun getItemLongClickListener(): OnItemLongClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnClickListener(getItemClickListener())
        adapter.setOnLongClickListener(getItemLongClickListener())
    }
}

