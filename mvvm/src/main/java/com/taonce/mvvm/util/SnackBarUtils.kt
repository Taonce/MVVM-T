package com.taonce.mvvm.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * 显示SnackBar
 *
 * @param view rootView
 * @param msg 显示内容
 * @param duration 时长
 * @param actionMsg 按钮内容
 * @param action 按钮事件
 */
fun showSnackBar(
    view: View,
    msg: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMsg: String,
    action: () -> Unit
) {
    Snackbar.make(view, msg, duration).setAction(actionMsg) { action() }.show()
}

/**
 * 长时间显示SnackBar
 *
 * @param view rootView
 * @param msg 显示内容
 * @param actionMsg 按钮内容
 * @param action 按钮事件
 */
fun showSnackBarLong(
    view: View,
    msg: String,
    actionMsg: String,
    action: () -> Unit
) {
    Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction(actionMsg) { action() }.show()
}

fun showCustomSnackBar(
    view: View,
    msg: String,
    actionMsg: String,
    actionTextColor: Int,
    action: () -> Unit
) {
    Snackbar.make(view, msg, Snackbar.LENGTH_LONG).apply {
        setAction(actionMsg) { action() }
        setActionTextColor(actionTextColor)
        show()
    }
}