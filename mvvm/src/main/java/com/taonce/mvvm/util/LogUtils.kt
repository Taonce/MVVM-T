package com.taonce.mvvm.util

import android.util.Log

/**
 * Author: Taonce
 * Date: 2019/3/28
 * Desc: 常用的日志打印类
 */
private const val IS_SHOW_LOG: Boolean = true

private const val TAG = "MVVM-T"

fun showDebug(
    tag: String = TAG,
    msg: String
) {
    if (IS_SHOW_LOG) {
        Log.d(tag, zipLogMsg(msg))
    }
}

fun showError(
    tag: String = TAG,
    msg: String,
    error: Exception? = null
) {
    if (IS_SHOW_LOG) {
        error?.let {
            Log.e(tag, msg, error)
        } ?: Log.e(tag, zipLogMsg(msg))
    }

}

fun showThrowable(
    tag: String = TAG,
    msg: String,
    throwable: Throwable? = null
) {
    if (IS_SHOW_LOG) {
        throwable?.let {
            Log.e(tag, msg, throwable)
        } ?: Log.e(tag, zipLogMsg(msg))
    }
}

fun showInfo(
    tag: String = TAG,
    msg: String
) {
    if (IS_SHOW_LOG) {
        Log.i(tag, zipLogMsg(msg))
    }
}

fun showWarning(
    tag: String = TAG,
    msg: String
) {
    if (IS_SHOW_LOG) {
        Log.w(tag, zipLogMsg(msg))
    }
}

private fun zipLogMsg(msg: String): String {
    val stackTrace = Thread.currentThread().stackTrace
    val buffer = StringBuffer()
    stackTrace.takeIf { it.size > 5 }?.let {
        val element = stackTrace[5]
        buffer.apply {
            append(getShortClassName(element.className))
            append(".")
            append(element.methodName)
            append("(")
            append(element.fileName)
            append(":")
            append(element.lineNumber)
            append(")")
            append(":")
        }
    }
    return with(buffer) {
        append(msg).toString()
    }
}

private fun getShortClassName(className: String): String {
    if (className.isEmpty()) {
        return ""
    }
    return className.substring(className.lastIndexOf(".") + 1)
}

