package com.taonce.mvvm.util


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build


/**
 * Author: Taonce
 * Date: 2019/4/1
 * Desc: 网络相关工具类
 */

/**
 * 判断网络是否连接，需要`ACCESS_NETWORK_STATE`权限
 */
fun Context.isNetConnected(): Boolean {
    val connectivityManager: ConnectivityManager? =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    connectivityManager?.let {
        val netInfo: NetworkInfo = it.activeNetworkInfo
        if (netInfo.isConnected) {
            if (netInfo.detailedState == NetworkInfo.DetailedState.CONNECTED) {
                return true
            }
        }
    }
    return false
}

/**
 * 判断是否为 `wifi` 连接
 */
fun Context.isWifi(): Boolean {
    val connectivityManager: ConnectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        connectivityManager.activeNetworkInfo.subtype == NetworkCapabilities.TRANSPORT_WIFI
    } else {
        connectivityManager.activeNetworkInfo.subtype == ConnectivityManager.TYPE_WIFI
    }
}

