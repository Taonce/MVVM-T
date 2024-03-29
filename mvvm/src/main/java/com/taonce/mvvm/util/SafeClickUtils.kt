package com.taonce.mvvm.util

import android.view.View
import com.taonce.mvvm.R

/**
 * 重复点击时间间隔
 */
private const val INTERVAL = 1000L

/**
 * 设置点击事件时间间隔，默认1000ms
 *
 * @param interval 间隔
 * @param onClick 点击事件
 */
fun View.safeClick(interval: Long = INTERVAL, onClick: () -> Unit) {
    setOnClickListener {
        val systemTime = System.currentTimeMillis()
        val viewClickTime = getTag(R.id.view_click_time)
        if (viewClickTime == null) {
            onClick()
            setTag(R.id.view_click_time, systemTime)
        } else {
            if (viewClickTime is Long && viewClickTime + interval > systemTime) {
                showDebug(msg = "double click < 1s")
            } else {
                onClick()
                setTag(R.id.view_click_time, systemTime)
            }
        }
    }
}