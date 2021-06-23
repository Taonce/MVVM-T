package com.taonce.mvvm.base

import android.view.View

/**
 * @author tao.
 * @description
 * @date 2021/6/23
 */
fun interface OnItemClickListener {
    fun click(position: Int, itemView: View);
}