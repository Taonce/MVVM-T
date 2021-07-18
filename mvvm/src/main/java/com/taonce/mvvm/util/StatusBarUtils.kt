package com.taonce.mvvm.util

import com.taonce.mvvm.TApp

/**
 * @author tao.
 * @description
 * @date 2021/7/9
 */
object StatusBarUtils {

    fun getStatusBarHeight(): Int {
        val applicationContext = TApp.instance.applicationContext
        val resourceId =
            applicationContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            applicationContext.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
}