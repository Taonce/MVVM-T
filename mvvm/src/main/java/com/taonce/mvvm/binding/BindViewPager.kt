package com.taonce.mvvm.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager


/**
 * @author Taonce
 * @description
 */
@BindingAdapter(
    value = ["bind:transform", "bind:reverse"],
    requireAll = false
)
fun bindTransform(
    viewPager: ViewPager,
    reverse: Boolean = false,
    transform: ViewPager.PageTransformer
) {
    viewPager.setPageTransformer(reverse, transform)
}

