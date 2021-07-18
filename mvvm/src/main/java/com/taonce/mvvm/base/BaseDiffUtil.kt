package com.taonce.mvvm.base

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Taonce.
 * @description DiffUtil的基类，原先的四个方法简化成两个
 *              并且需要重写的两个方法默认进行了非空判断
 */
abstract class BaseDiffUtil<T>(
    private val oldData: List<T>?,
    private val newData: List<T>?
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        if (oldData.isNullOrEmpty() || newData.isNullOrEmpty()) false
        else itemIsSame(oldItemPosition, newItemPosition)

    override fun getOldListSize() = oldData?.size ?: 0

    override fun getNewListSize() = newData?.size ?: 0

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        if (oldData.isNullOrEmpty() || newData.isNullOrEmpty()) false
        else itemContentIsSame(oldItemPosition, newItemPosition)

    abstract fun itemIsSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean

    abstract fun itemContentIsSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean
}