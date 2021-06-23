package com.taonce.mvvm.util


import android.content.Context
import android.widget.Toast
import com.taonce.mvvm.base.BaseFragment


/**
 * Author: Taonce
 * Date: 2019/3/28
 * Desc: 吐司工具
 */

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toast(resId: Int) {
    Toast.makeText(this, this.resources.getString(resId), Toast.LENGTH_SHORT).show()
}

fun Context.toastLong(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.toastLong(resId: Int) {
    Toast.makeText(this, this.resources.getString(resId), Toast.LENGTH_LONG).show()
}

fun BaseFragment.toast(text: String) {
    Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
}

fun BaseFragment.toast(resId: Int) {
    Toast.makeText(this.context, this.resources.getString(resId), Toast.LENGTH_SHORT).show()
}

fun BaseFragment.toastLong(text: String) {
    Toast.makeText(this.context, text, Toast.LENGTH_LONG).show()
}

fun BaseFragment.toastLong(resId: Int) {
    Toast.makeText(this.context, this.resources.getString(resId), Toast.LENGTH_LONG).show()
}

