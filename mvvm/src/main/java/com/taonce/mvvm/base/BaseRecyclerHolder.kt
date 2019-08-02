package com.taonce.mvvm.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: [RecyclerView]的ViewHolder基类
 */
class BaseRecyclerHolder<VB : ViewDataBinding>(val dataBinding: VB) : RecyclerView.ViewHolder(dataBinding.root)
