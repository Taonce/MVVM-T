package com.taonce.mvvm.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: [RecyclerView]的Adapter封装，结合的是DataBinding
 */
abstract class BaseRecyclerAdapter<VB : ViewBinding, T>(
    private val mData: MutableList<T>? = null
) : RecyclerView.Adapter<BaseRecyclerHolder<VB>>() {

    private var mClickListener: OnItemClickListener? = null

    private var mLongClickListener: OnItemLongClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerHolder<VB> = BaseRecyclerHolder(
        getViewBinding(parent)
    )

    override fun onBindViewHolder(
        holder: BaseRecyclerHolder<VB>,
        position: Int
    ) {
        setVariable(position, holder.viewBinding)
        holder.viewBinding.root.apply {
            setOnClickListener {
                mClickListener?.click(position, it)
            }
            setOnLongClickListener {
                mLongClickListener?.click(position, it)
                true
            }
        }
    }

    /**
     * 获取item数据，有可能为空
     */
    fun getItemData(position: Int) = mData?.get(position)

    override fun getItemCount(): Int = mData?.size ?: 0

    abstract fun getLayoutId(): Int

    abstract fun getViewBinding(parent: ViewGroup): VB

    /**
     * 处理界面
     */
    abstract fun setVariable(position: Int, viewBinding: VB)

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

    /**
     * 指定位置的item插入
     */
    fun addItem(position: Int, data: T) {
        if (position in 0..itemCount) {
            mData?.let {
                it.add(position, data)
                notifyItemInserted(position)
                if (position != itemCount) {
                    notifyItemRangeChanged(position, itemCount - position)
                }
            }
        }
    }

    /**
     * 列表末尾插入多条数据
     */
    fun add(newData: MutableList<T>) {
        mData?.let {
            it.addAll(newData)
            notifyItemRangeInserted(itemCount, newData.size)
        }
    }

    fun setOnClickListener(listener: OnItemClickListener?) {
        mClickListener = listener
    }

    fun setOnLongClickListener(listener: OnItemLongClickListener?) {
        mLongClickListener = listener
    }
}

