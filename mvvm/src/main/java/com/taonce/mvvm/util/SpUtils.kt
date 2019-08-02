package com.taonce.mvvm.util

import android.content.Context
import android.content.SharedPreferences


/**
 * Author: Taonce
 * Date: 2019/4/1
 * Desc: SP 文件的工具类
 */

// SP文件名
const val fileName = "share_preference"

/**
 * 向SP中写入数据
 * [key]-[value] 键值对
 */
fun Context.putSP(key: String, value: Any) {
    val sp: SharedPreferences = this.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    when (value) {
        is String -> sp.edit().putString(key, value).apply()
        is Int -> sp.edit().putInt(key, value).apply()
        is Long -> sp.edit().putLong(key, value).apply()
        is Boolean -> sp.edit().putBoolean(key, value).apply()
        is Float -> sp.edit().putFloat(key, value).apply()
        else -> toast("类型错误")
    }
}

/**
 * 向SP中取出数据
 * [key]-[defaultValue] 键值对
 */
fun Context.getSP(key: String, defaultValue: Any): Any? {
    val sp: SharedPreferences = this.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    when (defaultValue) {
        is String -> return sp.getString(key, defaultValue)
        is Int -> return sp.getInt(key, defaultValue)
        is Long -> return sp.getLong(key, defaultValue)
        is Boolean -> return sp.getBoolean(key, defaultValue)
        is Float -> return sp.getFloat(key, defaultValue)
        else -> toast("类型错误")
    }
    return null
}

/**
 * 移除[key]对应的[getSP]
 */
fun Context.remove(key: String) {
    val sp: SharedPreferences = this.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    sp.edit().remove(key).apply()
}

/**
 * 清除[SharedPreferences]的所有数据
 */
fun Context.clear() {
    val sp: SharedPreferences = this.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    sp.edit().clear().apply()
}

/**
 * 检测[SharedPreferences]是否包含某个键值对
 */
fun Context.contains(key: String): Boolean {
    val sp: SharedPreferences = this.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    return sp.contains(key)
}

