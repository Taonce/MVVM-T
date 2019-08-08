package com.taonce.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding


/**
 * @author Taonce
 * @description 带列表的[AppCompatActivity]
 * 单击和长按事件如果没设置的话直接返回null
 */
abstract class BaseListActivity<VDB : ViewDataBinding, A : BaseRecyclerAdapter<*, *>>
    : BaseActivity<VDB>() {

    protected val mAdapter by lazy { getAdapter() }

    abstract fun getAdapter(): A

    abstract fun getItemClickListener(): OnItemClickListener?

    abstract fun getItemLongClickListener(): OnItemLongClickListener?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 省去具体界面再去绑定的操作
        mAdapter.setOnClickListener(getItemClickListener())
        mAdapter.setOnLongClickListener(getItemLongClickListener())
    }
}

