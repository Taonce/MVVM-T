package com.taonce.mvvm.network

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor
import com.taonce.mvvm.TApp
import com.taonce.mvvm.util.showInfo
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author Taonce.
 * @description
 */

class OkHttpManager {
    companion object {
        val mInstance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpManager()
        }
    }

    private var okHttpClient: OkHttpClient? = null
    // 自定义TAG的Log
    private val okHttpLog: OkHttpLog by lazy { OkHttpLog() }
    // 连接超时时间
    private val connectionTime: Long = 10L
    // 写入超时时间
    private val writeTime: Long = 10L
    // 读取超时时间
    private val readTime: Long = 30L
    // 缓存文件
    private val cacheFile: File = File(TApp.instance.cacheDir.absolutePath)
    // 缓存文件大小
    private val maxSize: Long = 8 * 1024 * 1024
    // OkHttpCache
    private var cache: Cache

    init {
        // 初始化缓存文件
        if (!cacheFile.exists()) cacheFile.mkdir()
        cache = Cache(cacheFile, maxSize)
    }

    // 配置OkHttp
    fun getHttpClient(): OkHttpClient {
        if (null == okHttpClient) {
            okHttpClient = OkHttpClient.Builder()
                .connectTimeout(connectionTime, TimeUnit.SECONDS)
                .readTimeout(readTime, TimeUnit.SECONDS)
                .writeTimeout(writeTime, TimeUnit.SECONDS)
                .addInterceptor(LogInterceptor(okHttpLog))
                .cache(cache)
                .build()
        }
        return okHttpClient!!
    }

    // 自定义OkHttpLog
    class OkHttpLog : LogInterceptor.Logger {
        override fun log(message: String?) {
            message?.let { showInfo(it, "OkHttp") }
        }
    }
}