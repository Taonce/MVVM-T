package com.taonce.mvvm

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: [Application] 基类
 */
class TApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var instance: Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
    }
}
