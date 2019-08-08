package com.taonce.mvvm.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


/**
 * @author Taonce
 * @description 带列表的[Fragment]
 * 单击和长按事件如果没设置的话直接返回null
 */
abstract class BaseListFragment<VDB : ViewDataBinding, A : BaseRecyclerAdapter<*, *>>
    : BaseFragment<VDB>() {

    protected val mAdapter by lazy { getAdapter() }

    abstract fun getAdapter(): A

    abstract fun getItemClickListener(): OnItemClickListener?

    abstract fun getItemLongClickListener(): OnItemLongClickListener?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter.setOnClickListener(getItemClickListener())
        mAdapter.setOnLongClickListener(getItemLongClickListener())
    }
}

