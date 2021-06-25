package com.taonce.mvvm.util

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.taonce.mvvm.base.BaseActivity
import com.taonce.mvvm.base.BaseFragment
import java.io.Serializable


/**
 * Author: Taonce
 * Date: 2019/4/12
 * Desc:
 */

/**
 * [clazz]: destination [BaseActivity]
 * [map]: put data
 * [isFinished]: [BaseActivity] is finish or is not finish
 */
fun <T : AppCompatActivity> AppCompatActivity.start(
    clazz: Class<T>,
    map: Map<String, Any>? = null,
    isFinished: Boolean = false
) {
    val intent = Intent(this, clazz)
    if (map != null) {
        if (map.isNotEmpty()) {
            map.forEach {
                val key: String = it.key
                when (val value: Any = it.value) {
                    is String -> intent.putExtra(key, value)
                    is Int -> intent.putExtra(key, value)
                    is Float -> intent.putExtra(key, value)
                    is Double -> intent.putExtra(key, value)
                    is Boolean -> intent.putExtra(key, value)
                    is Short -> intent.putExtra(key, value)
                    is ShortArray -> intent.putExtra(key, value)
                    is Long -> intent.putExtra(key, value)
                    is LongArray -> intent.putExtra(key, value)
                    is IntArray -> intent.putExtra(key, value)
                    is FloatArray -> intent.putExtra(key, value)
                    is DoubleArray -> intent.putExtra(key, value)
                    is Char -> intent.putExtra(key, value)
                    is CharArray -> intent.putExtra(key, value)
                    is Byte -> intent.putExtra(key, value)
                    is ByteArray -> intent.putExtra(key, value)
                    is BooleanArray -> intent.putExtra(key, value)
                    is Bundle -> intent.putExtra(key, value)
                    is Parcelable -> intent.putExtra(key, value)
                    is Serializable -> intent.putExtra(key, value)
                }
            }
        }
    }
    this.startActivity(intent)
    if (isFinished) this.finish()
}

/**
 * [clazz]: destination [BaseActivity]
 * [map]: put data
 * Don't need to include `isFinished` as it is an extension of [BaseFragment]
 */
fun <T : AppCompatActivity> Fragment.start(
    clazz: Class<T>,
    map: Map<String, Any>? = null
) {
    val intent = Intent(this.context, clazz)
    if (map != null) {
        if (map.isNotEmpty()) {
            map.forEach {
                val key: String = it.key
                when (val value: Any = it.value) {
                    is String -> intent.putExtra(key, value)
                    is Int -> intent.putExtra(key, value)
                    is Float -> intent.putExtra(key, value)
                    is Double -> intent.putExtra(key, value)
                    is Boolean -> intent.putExtra(key, value)
                    is Short -> intent.putExtra(key, value)
                    is ShortArray -> intent.putExtra(key, value)
                    is Long -> intent.putExtra(key, value)
                    is LongArray -> intent.putExtra(key, value)
                    is IntArray -> intent.putExtra(key, value)
                    is FloatArray -> intent.putExtra(key, value)
                    is DoubleArray -> intent.putExtra(key, value)
                    is Char -> intent.putExtra(key, value)
                    is CharArray -> intent.putExtra(key, value)
                    is Byte -> intent.putExtra(key, value)
                    is ByteArray -> intent.putExtra(key, value)
                    is BooleanArray -> intent.putExtra(key, value)
                    is Bundle -> intent.putExtra(key, value)
                    is Parcelable -> intent.putExtra(key, value)
                    is Serializable -> intent.putExtra(key, value)
                }
            }
        }
    }
    this.startActivity(intent)
}

