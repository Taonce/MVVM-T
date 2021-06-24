package com.taonce.mvvm.util

import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: 统一捕捉协程中的异常，可用于网络请求
 */
private const val TAG = "CoroutinesUtils"

private val exceptionHandler: CoroutineExceptionHandler =
    CoroutineExceptionHandler { _, throwable ->
        showThrowable(TAG, "coroutines Exception", throwable)
    }

private val handlerContext: CoroutineContext = exceptionHandler

/**
 * 可自定义协程上下文环境，也可以使用默认的 [handlerContext]
 */
fun CoroutineScope.safeLaunch(
    coroutineContext: CoroutineContext? = null,
    block: suspend () -> Unit
) = launch(coroutineContext?.let { it + handlerContext } ?: handlerContext) { block() }

/**
 * 主线程启动协程
 * @param handler 捕捉异常处理
 * @param block 协程体
 *
 * @return [Job]
 */
fun CoroutineScope.mainLaunch(
    handler: CoroutineExceptionHandler = exceptionHandler,
    block: suspend CoroutineScope.() -> Unit
) = launch(context = Dispatchers.Main + handler + SupervisorJob(), block = block)


/**
 * IO线程启动协程
 * @param handler 捕捉异常处理
 * @param block 协程体
 *
 * @return [Job]
 */
fun CoroutineScope.ioLaunch(
    handler: CoroutineExceptionHandler = exceptionHandler,
    block: suspend CoroutineScope.() -> Unit
) = launch(Dispatchers.IO + handler + SupervisorJob(), block = block)


/**
 * 主线程启动协程
 * @param block 协程体
 *
 * @return [Deferred]
 */
fun <T> CoroutineScope.mainAsync(block: suspend CoroutineScope.() -> T) =
    async(Dispatchers.Main + SupervisorJob(), block = block)

/**
 * IO线程启动协程
 * @param block 协程体
 *
 * @return [Deferred]
 */
fun <T> CoroutineScope.ioAsync(block: suspend CoroutineScope.() -> T) =
    async(Dispatchers.IO + SupervisorJob(), block = block)

/**
 * 主线程启动协程，带返回值
 * @param handler 捕捉异常处理
 * @param block 协程体
 *
 * @return T
 */
suspend fun <T> mainWithContext(
    handler: CoroutineExceptionHandler = exceptionHandler,
    block: suspend CoroutineScope.() -> T
) = withContext(Dispatchers.Main + handler + SupervisorJob(), block)

/**
 * 子线程启动协程，带返回值
 * @param handler 捕捉异常处理
 * @param block 协程体
 *
 * @return T
 */
suspend fun <T> ioWithContext(
    handler: CoroutineExceptionHandler = exceptionHandler,
    block: suspend CoroutineScope.() -> T
) = withContext(Dispatchers.IO + handler + SupervisorJob(), block)

/**
 * 非[CoroutineScope]扩展函数的子线程开启协程
 * @param handler 捕捉异常处理
 * @param block 协程体
 *
 * @return [Job]
 */
fun ioScopeLaunch(
    handler: CoroutineExceptionHandler = exceptionHandler,
    block: suspend CoroutineScope.() -> Unit
) = CoroutineScope(Dispatchers.IO + handler + SupervisorJob()).launch(block = block)

/**
 * 非[CoroutineScope]扩展函数的主线程开启协程
 * @param handler 捕捉异常处理
 * @param block 协程体
 *
 * @return [Job]
 */
fun mainScopeLaunch(
    handler: CoroutineExceptionHandler = exceptionHandler,
    block: suspend CoroutineScope.() -> Unit
) = CoroutineScope(Dispatchers.Main + handler + SupervisorJob()).launch(block = block)

/**
 * 非[CoroutineScope]扩展函数的子线程开启协程
 * @param block 协程体
 *
 * @return [Deferred]
 */
fun <T> ioScopeAsync(
    block: suspend CoroutineScope.() -> T
) = CoroutineScope(Dispatchers.IO + SupervisorJob()).async(block = block)

/**
 * 非[CoroutineScope]扩展函数的主线程开启协程
 * @param block 协程体
 *
 * @return [Deferred]
 */
fun <T> mainScopeAsync(
    block: suspend CoroutineScope.() -> T
) = CoroutineScope(Dispatchers.Main + SupervisorJob()).async(block = block)