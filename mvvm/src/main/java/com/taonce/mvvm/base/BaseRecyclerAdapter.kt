package com.taonce.mvvm.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: [RecyclerView]的Adapter封装，结合的是DataBinding
 */
abstract class BaseRecyclerAdapter<VDB : ViewDataBinding, T>(
    private val mData: MutableList<T>? = null
) : RecyclerView.Adapter<BaseRecyclerHolder<VDB>>() {

    private var mClickListener: OnItemClickListener? = null
    private var mLongClickListener: OnItemLongClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerHolder<VDB> = BaseRecyclerHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutId(),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: BaseRecyclerHolder<VDB>,
        position: Int
    ) {
        setVariable(position, holder.dataBinding)
        holder.dataBinding.apply {
            executePendingBindings()
            root.apply {
                setOnClickListener {
                    mClickListener?.click(position, it)
                }
                setOnLongClickListener {
                    mLongClickListener?.click(position, it)
                    true
                }
            }
        }
    }

    override fun getItemCount(): Int = mData?.size ?: 0

    abstract fun getLayoutId(): Int

    /**
     * 处理界面
     */
    abstract fun setVariable(position: Int, dataBinding: VDB)

    /**
     * 可结合DiffUtil使用
     */
    abstract fun update(newData: MutableList<T>)

    /**
     * 移除指定的item
     */
    fun removeItem(position: Int) {
        if (position in 0 until itemCount)
            mData?.let {
                it.removeAt(position)
                notifyItemRemoved(position)
                if (position != itemCount) {
                    notifyItemRangeChanged(position, itemCount - position)
                }
            }
    }

    fun setOnClickListener(listener: OnItemClickListener?) {
        mClickListener = listener
    }

    fun setOnLongClickListener(listener: OnItemLongClickListener?) {
        mLongClickListener = listener
    }
}

