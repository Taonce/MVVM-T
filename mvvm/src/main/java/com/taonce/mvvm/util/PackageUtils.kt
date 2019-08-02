package com.taonce.mvvm.util

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build

/**
 * 获取版本号
 * [packageName] 包名
 * @return VersionCode
 */
fun Context.getVersionCode(packageName: String = this.packageName): String {
    var versionCode = ""
    getPackageInfo(packageName)?.let {
        versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            it.longVersionCode.toString()
        } else {
            it.versionCode.toString()
        }
    }
    return versionCode
}

/**
 * 获取版本名
 * [packageName] 包名
 * @return VersionName
 */
fun Context.getVersionName(packageName: String = this.packageName): String {
    return if (getPackageInfo(packageName) != null)
        getPackageInfo(packageName)!!.versionName else ""
}

/**
 * 获取[PackageInfo]对象
 */
fun Context.getPackageInfo(packageName: String = this.packageName): PackageInfo? {
    return try {
        this.packageManager.getPackageInfo(packageName, 0)
    } catch (e: PackageManager.NameNotFoundException) {
        showError(msg = "getVersionCode Exception: NameNotFoundException")
        null
    }
}
