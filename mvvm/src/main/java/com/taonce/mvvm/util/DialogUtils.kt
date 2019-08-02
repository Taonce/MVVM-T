package com.taonce.mvvm.util

import android.content.Context
import androidx.appcompat.app.AlertDialog


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: 常见的Dialog弹框
 * --------------------
 * | dialog           |
 * |                  |
 * |   cancel   yes   |
 * --------------------
 */
fun Context.alert(
    message: CharSequence,
    title: CharSequence? = null,
    yesButton: (() -> Unit)? = null,
    noButton: (() -> Unit)? = null
) {
    val builder = AlertDialog.Builder(this)
    builder.apply {
        if (title != null) setTitle(title)
        setMessage(message)
        if (yesButton != null) setPositiveButton("确定") { _, _ -> yesButton() }
        if (noButton != null) setNegativeButton("取消") { _, _ -> noButton() }
        show()
    }
}

fun Context.alert(
    message: Int,
    title: Int? = null,
    yesButton: (() -> Unit)? = null,
    noButton: (() -> Unit)? = null
) {
    val builder = AlertDialog.Builder(this)
    builder.apply {
        if (title != null) setTitle(title)
        setMessage(message)
        if (yesButton != null) setPositiveButton("确定") { _, _ -> yesButton() }
        if (noButton != null) setNegativeButton("取消") { _, _ -> noButton() }
        show()
    }
}
