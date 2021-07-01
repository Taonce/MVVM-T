package com.taonce.mvvm.util

import android.graphics.Color
import android.view.View
import androidx.annotation.ColorInt
import com.google.android.material.snackbar.Snackbar

/**
 * 显示[Snackbar]
 *
 * @param msg 显示内容
 * @param duration 时长
 * @param actionMsg 按钮内容
 * @param action 按钮事件
 */
fun View.showSnackBar(
    msg: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMsg: String,
    action: () -> Unit
) {
    Snackbar.make(this, msg, duration).setAction(actionMsg) { action() }.show()
}

/**
 * 长时间显示[Snackbar]
 *
 * @param msg 显示内容
 * @param actionMsg 按钮内容
 * @param action 按钮事件
 */
fun View.showSnackBarLong(
    msg: String,
    actionMsg: String,
    action: () -> Unit
) {
    Snackbar.make(this, msg, Snackbar.LENGTH_LONG).setAction(actionMsg) { action() }.show()
}

/**
 * 自定义[Snackbar]
 *
 * @param msg 显示内容
 * @param actionMsg 按钮内容
 * @param actionTextColor 按钮文字颜色
 * @param backgroundColor 背景颜色
 * @param action 按钮事件
 */
fun View.showCustomSnackBar(
    msg: String,
    actionMsg: String,
    @ColorInt actionTextColor: Int,
    @ColorInt backgroundColor: Int = Color.WHITE,
    action: () -> Unit
) {
    Snackbar.make(this, msg, Snackbar.LENGTH_LONG).apply {
        view.setBackgroundColor(backgroundColor)
        setAction(actionMsg) { action() }
        setActionTextColor(actionTextColor)
        show()
    }
}