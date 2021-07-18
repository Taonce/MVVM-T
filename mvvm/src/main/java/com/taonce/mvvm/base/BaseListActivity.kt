package com.taonce.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


/**
 * @author Taonce
 * @description 带列表的[AppCompatActivity]
 * 单击和长按事件如果没设置的话直接返回null
 */
abstract class BaseListActivity<vb : ViewBinding, A : BaseRecyclerAdapter<*, *>> :
    BaseActivity<vb>() {

    protected val adapter: A by lazy { getRecycleAdapter() }

    abstract fun getRecycleAdapter(): A

    open fun getItemClickListener(): OnItemClickListener? = null

    open fun getItemLongClickListener(): OnItemLongClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 省去具体界面再去绑定的操作
        adapter.setOnClickListener(getItemClickListener())
        adapter.setOnLongClickListener(getItemLongClickListener())
    }
}

