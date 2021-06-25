package com.taonce.mvvm.util

import androidx.appcompat.app.AppCompatActivity

/**
 * @author tao.
 * @description [AppCompatActivity]栈管理
 * @date 2021/6/25
 */
object ActivityStackManager {
    private val activityList = mutableListOf<AppCompatActivity>()

    /**
     * 添加一个[AppCompatActivity]实例
     */
    fun addActivity(activity: AppCompatActivity) {
        activityList.add(activity)
    }

    /**
     * 移除指定[AppCompatActivity]实例
     */
    fun removeActivity(activity: AppCompatActivity) {
        activityList.remove(activity)
    }

    /**
     * finish所有[AppCompatActivity]
     */
    fun finishAll() {
        activityList.takeIf { it.isEmpty() }?.let {
            showInfo(msg = "finishAll Activity List is Empty")
            return
        }
        activityList.forEach {
            if (!it.isFinishing && !it.isDestroyed) {
                it.finish()
            }
        }
    }
}