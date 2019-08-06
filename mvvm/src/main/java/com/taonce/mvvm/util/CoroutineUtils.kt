package com.taonce.mvvm.util

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: 统一捕捉协程中的异常，可用于网络请求
 */
private val loggingExceptionHandler: CoroutineExceptionHandler =
    CoroutineExceptionHandler { _, throwable ->
        showError(throwable.message ?: "")
    }

private val handlerContext: CoroutineContext = loggingExceptionHandler

/**
 * 可自定义协程上下文环境，也可以使用默认的 [handlerContext]
 */
fun CoroutineScope.safeLaunch(coroutineContext: CoroutineContext? = null, block: suspend () -> Unit): Job =
    launch(coroutineContext?.let { it + handlerContext } ?: handlerContext) { block() }
