package com.taonce.mvvm.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.taonce.mvvm.binding.OnClickListener
import com.taonce.mvvm.binding.OnLongClickListener


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: [RecyclerView]的Adapter封装，结合的是DataBinding
 */
abstract class BaseRecyclerAdapter<T>(
    private val mData: MutableList<T>? = null
) : RecyclerView.Adapter<BaseRecyclerHolder<ViewDataBinding>>() {

    private var mClickListener: OnClickListener? = null
    private var mLongClickListener: OnLongClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerHolder<ViewDataBinding> = BaseRecyclerHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutId(),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: BaseRecyclerHolder<ViewDataBinding>,
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
                    mLongClickListener?.longClick(position, it)
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
    abstract fun setVariable(position: Int, dataBinding: ViewDataBinding)

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

    fun setOnClickListener(listener: OnClickListener?) {
        mClickListener = listener
    }

    fun setOnLongClickListener(listener: OnLongClickListener?) {
        mLongClickListener = listener
    }
}

