package com.taonce.mvvm.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding

/**
 * @author tao.
 * @description
 * @date 2021/7/8
 */
abstract class BaseMultiListFragment<vb : ViewBinding, A : BaseMultiRecyclerAdapter<*>>
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