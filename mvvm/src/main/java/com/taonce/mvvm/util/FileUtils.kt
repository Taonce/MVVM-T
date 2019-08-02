package com.taonce.mvvm.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import java.io.File


/**
 * Author: Taonce
 * Date: 2019/3/28
 * Desc: 文件相关工具
 */

/**
 * 判断SD卡是否挂载
 */
fun isSDCard(): Boolean =
    Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

/**
 * 判断SD卡是否有可用空间
 */
@SuppressLint("ObsoleteSdkInt")
fun isEnoughMemory(): Boolean {
    val file: File = Environment.getExternalStorageDirectory()
    val statFs = StatFs(file.path)
    val blockSize: Long
    val freeBlocks: Long
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        blockSize = statFs.blockSizeLong
        freeBlocks = statFs.availableBlocksLong
    } else {
        blockSize = statFs.blockSize.toLong()
        freeBlocks = statFs.availableBlocks.toLong()
    }
    return ((freeBlocks * blockSize) / 1024 / 1024) > 1
}

/**
 * 获取应用文件目录
 * [customPath] 自定义路径
 * @return 应用程序文件目录("/data/data/<包名>/files")
 */
fun Context.getFileDir(customPath: String = ""): File {
    return File(this.filesDir.absolutePath + File.separator + customPath + File.separator)
        .apply { mkdirs() }
}

/**
 * 获取应用缓存目录
 * [customPath] 自定义路径
 * @return 应用程序缓存目录("/data/data/<包名>/cache")
 */
fun Context.getCacheDir(customPath: String = ""): File {
    return File(this.cacheDir.absolutePath + File.separator + customPath + File.separator)
        .apply { mkdirs() }
}

/**
 * 获取应用外置文件目录
 * [customPath] 自定义路径
 * @return 应用程序文件目录("/Android/data/<包名>/files")
 */
fun Context.getExternalFileDir(customPath: String = ""): File? {
    return if (isSDCard() && null != this.getExternalFilesDir("")) {
        File(this.getExternalFilesDir("")!!.absolutePath + File.separator + customPath + File.separator)
            .apply { mkdirs() }
    } else {
        null
    }
}

/**
 * 获取应用外置缓存目录
 * [customPath] 自定义路径
 * @return 应用程序缓存目录("/Android/data/<包名>/cache")
 */
fun Context.getExternalCacheDir(customPath: String = ""): File? {
    return if (isSDCard() && null != this.externalCacheDir) {
        File(this.externalCacheDir!!.absolutePath + File.separator + customPath + File.separator)
            .apply { mkdirs() }
    } else {
        null
    }
}

/**
 * 获取公共下载目录
 * [customPath] 自定义目录
 * @return 公共下载目录(download)
 */
fun getPublicDownloadDir(customPath: String = ""): File? {
    return if (isSDCard()) {
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
                    + File.separator + customPath + File.separator
        )
    } else {
        null
    }
}

