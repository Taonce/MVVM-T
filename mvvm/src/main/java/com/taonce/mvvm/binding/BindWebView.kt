package com.taonce.mvvm.binding

import android.webkit.WebView
import androidx.databinding.BindingAdapter


/**
 * Author: Taonce
 * Date: 2019/8/5
 * Project: MVVM-T
 * Desc:
 */

@BindingAdapter("bind:url")
fun bindUrl(webView: WebView, url: String? = null) {
    url ?: return
    if (url.isNotEmpty()) webView.loadUrl(url)
}

