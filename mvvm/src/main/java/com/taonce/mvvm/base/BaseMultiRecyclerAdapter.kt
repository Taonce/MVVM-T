package com.taonce.mvvm.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.taonce.mvvm.util.showDebug

/**
 * @author tao.
 * @description
 * @date 2021/7/8
 */
abstract class BaseMultiRecyclerAdapter<T>(val mData: MutableList<T> = mutableListOf()) :
    RecyclerView.Adapter<BaseMultiRecyclerHolder>() {
    companion object {
        const val COMMON_TYPE = 0x00

        const val HEADER_TYPE = 0x01

        const val FOOTER_TYPE = 0x02
    }

    private val viewBindingMap by lazy { mutableMapOf<Int, Int>() }

    private var mClickListener: OnItemClickListener? = null

    private var mLongClickListener: OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseMultiRecyclerHolder {
        showDebug("onCreateViewHolder position $itemCount")
        if (viewBindingMap.isNotEmpty()) {
            viewBindingMap.forEach {
                if (viewType == it.value) {
                    return BaseMultiRecyclerHolder(getMultiViewBinding(parent, viewType))
                }
            }
            return BaseMultiRecyclerHolder(getViewBinding(parent))
        } else {
            return BaseMultiRecyclerHolder(getViewBinding(parent))
        }
    }

    override fun onBindViewHolder(holderBase: BaseMultiRecyclerHolder, position: Int) {
        setMultiVariable(position, holderBase.viewBinding)
        holderBase.viewBinding.root.apply {
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
     * 处理界面
     */
    abstract fun setMultiVariable(position: Int, viewBinding: ViewBinding)

    override fun getItemViewType(position: Int): Int {
        if (viewBindingMap.isEmpty()) {
            return COMMON_TYPE
        } else {
            return getMultiViewType(position) ?: COMMON_TYPE
        }
    }

    private fun getMultiViewType(position: Int) = viewBindingMap[position]

    open fun addItemType(position: Int, type: Int) {
        viewBindingMap[position] = type
    }

    /**
     * 获取item数据，有可能为空
     */
    fun getItemData(position: Int) = mData[position]

    override fun getItemCount(): Int = mData.size

    abstract fun getViewBinding(parent: ViewGroup): ViewBinding

    abstract fun getMultiViewBinding(parent: ViewGroup, viewType: Int): ViewBinding

    /**
     * 可结合DiffUtil使用
     */
    abstract fun update(parent: ViewGroup, newData: List<T>)

    /**
     * 移除指定的item
     */
    fun removeItem(position: Int) {
        if (position in 0 until itemCount)
            mData.let {
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
            mData.let {
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
    fun add(newData: List<T>) {
        mData.let {
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