package com.taonce.mvvm.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @author tao.
 * @description
 * @date 2021/7/8
 */

class BaseMultiRecyclerHolder(val viewBinding: ViewBinding) :
    RecyclerView.ViewHolder(viewBinding.root)