package com.taonce.mvvm.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: [RecyclerView]的ViewHolder基类
 */
class BaseRecyclerHolder<VB : ViewBinding>(val viewBinding: VB) :
    RecyclerView.ViewHolder(viewBinding.root)
