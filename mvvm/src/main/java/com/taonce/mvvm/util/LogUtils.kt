package com.taonce.mvvm.util


import android.util.Log


/**
 * Author: Taonce
 * Date: 2019/3/28
 * Desc: 常用的日志打印类
 */
const val isShow: Boolean = true

fun showDebug(
    msg: String,
    tag: String = "taonce"
) {
    if (isShow) {
        Log.d(tag, msg)
    }
}

fun showError(
    msg: String,
    tag: String = "taonce"
) {
    if (isShow)
        Log.e(tag, msg)
}

fun showInfo(
    msg: String,
    tag: String = "taonce"
) {
    if (isShow)
        Log.i(tag, msg)
}

fun showWarning(
    msg: String,
    tag: String = "taonce"
) {
    if (isShow)
        Log.w(tag, msg)
}

