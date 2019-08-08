package com.taonce.mvvm.binding

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * Author: Taonce
 * Project: MVVM-T
 * Desc: [ImageView]的属性绑定
 */
@BindingAdapter("bind:url")
fun bindUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .centerCrop()
        .into(imageView)
}

@BindingAdapter(
    value = ["bind:placeHolder", "bind:loadUrl", "bind:error"],
    requireAll = false
)
fun bindImage(
    imageView: ImageView,
    @DrawableRes placeHolder: Int,
    loadUrl: String,
    @DrawableRes error: Int
) {
    Glide.with(imageView.context)
        .load(loadUrl).apply {
            RequestOptions.centerCropTransform().placeholder(placeHolder).error(error)
        }
        .into(imageView)
}

