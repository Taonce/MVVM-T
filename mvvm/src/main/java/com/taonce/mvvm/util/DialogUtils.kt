package com.taonce.mvvm.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.taonce.mvvm.R


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
fun Context.dialog(
    message: CharSequence,
    title: CharSequence? = null,
    yesButton: (() -> Unit)? = null,
    noButton: (() -> Unit)? = null
) {
    val builder = AlertDialog.Builder(this)
    builder.apply {
        title?.let { setTitle(it) }
        setMessage(message)
        yesButton?.let { setPositiveButton("确定") { _, _ -> it() } }
        noButton?.let { setPositiveButton("取消") { _, _ -> it() } }
        show()
    }
}

fun Context.dialog(
    @StringRes message: Int,
    @StringRes title: Int? = null,
    yesButton: (() -> Unit)? = null,
    noButton: (() -> Unit)? = null
) {
    val builder = AlertDialog.Builder(this)
    builder.apply {
        title?.let { setTitle(it) }
        setMessage(message)
        yesButton?.let { setPositiveButton("确定") { _, _ -> it() } }
        noButton?.let { setPositiveButton("取消") { _, _ -> it() } }
        show()
    }
}

/**
 * 自定义弹框
 */
fun Context.customDialog(
    @StringRes message: Int,
    @StringRes title: Int? = null,
    yesButton: (() -> Unit)? = null,
    noButton: (() -> Unit)? = null,
    @LayoutRes viewId: Int
) {
    val custom = LayoutInflater.from(this).inflate(viewId, null)
    val builder = AlertDialog.Builder(this)
    builder.apply {
        title?.let { setTitle(it) }
        setMessage(message)
        setView(custom)
        yesButton?.let { setPositiveButton("确定") { _, _ -> it() } }
        noButton?.let { setPositiveButton("取消") { _, _ -> it() } }
        show()
    }
}

fun Context.dialog(
    message: CharSequence,
    title: CharSequence? = null,
    yesButton: (() -> Unit)? = null,
    noButton: (() -> Unit)? = null,
    @LayoutRes viewId: Int
) {
    val custom = LayoutInflater.from(this).inflate(viewId, null)
    val builder = AlertDialog.Builder(this)
    builder.apply {
        title?.let { setTitle(it) }
        setMessage(message)
        setView(custom)
        yesButton?.let { setPositiveButton("确定") { _, _ -> it() } }
        noButton?.let { setPositiveButton("取消") { _, _ -> it() } }
        show()
    }
}

/**
 * 仿iOS的Dialog
 * -----------------------
 * |        title        |
 * |         msg         |
 * |---------------------|
 * |  cancel |  enter    |
 * -----------------------
 */
@SuppressLint("InflateParams")
fun Context.iosDialog(
    message: CharSequence,
    title: CharSequence,
    cancelText: CharSequence? = null,
    enterText: CharSequence? = null,
    enterListener: (() -> Unit)? = null,
    cancelListener: (() -> Unit)? = null
): AlertDialog = LayoutInflater.from(this).inflate(R.layout.dialog_common_ios, null).run iosView@{
    findViewById<TextView>(R.id.dialog_title).text = title
    findViewById<TextView>(R.id.dialog_msg).text = message
    AlertDialog.Builder(this@iosDialog).apply {
        setView(this@iosView)
    }.create().let { dialog ->
        findViewById<Button>(R.id.dialog_enter).apply {
            enterText?.let { text = it }
            setOnClickListener {
                enterListener?.invoke()
                dialog.dismiss()
            }
        }
        findViewById<Button>(R.id.dialog_cancel).apply {
            cancelText?.let { text = it }
            setOnClickListener {
                cancelListener?.invoke()
                dialog.dismiss()
            }
        }
        dialog.show()
        dialog
    }
}

@SuppressLint("InflateParams")
fun Context.iosDialog(
    @StringRes message: Int,
    @StringRes title: Int,
    @StringRes cancelText: Int? = null,
    @StringRes enterText: Int? = null,
    enterListener: (() -> Unit)? = null,
    cancelListener: (() -> Unit)? = null
): AlertDialog = LayoutInflater.from(this).inflate(R.layout.dialog_common_ios, null).run iosView@{
    findViewById<TextView>(R.id.dialog_title).text = resources.getString(title)
    findViewById<TextView>(R.id.dialog_msg).text = resources.getString(message)
    AlertDialog.Builder(this@iosDialog).apply {
        setView(this@iosView)
    }.create().let { dialog ->
        findViewById<Button>(R.id.dialog_enter).apply {
            enterText?.let { text = resources.getString(it) }
            setOnClickListener {
                enterListener?.invoke()
                dialog.dismiss()
            }
        }
        findViewById<Button>(R.id.dialog_cancel).apply {
            cancelText?.let { text = resources.getString(it) }
            setOnClickListener {
                cancelListener?.invoke()
                dialog.dismiss()
            }
        }
        dialog.show()
        dialog
    }
}
