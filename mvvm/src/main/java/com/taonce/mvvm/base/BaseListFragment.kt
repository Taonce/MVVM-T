package com.taonce.mvvm.base

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

    protected val mClickListener by lazy { getItemClickListener() }

    protected val mLongClickListener by lazy { getItemLongClickListener() }

    abstract fun getAdapter(): A

    abstract fun getItemClickListener(): OnItemClickListener?

    abstract fun getItemLongClickListener(): OnItemLongClickListener?
}

