package com.taonce.mvvm.util


import android.content.Context
import android.util.TypedValue

/**
 * Author: Taonce
 * Date: 2019/4/1
 * Desc: 单位转换
 */

fun Context.dp2px(dp: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    this.resources.displayMetrics
)

fun Context.sp2dp(sp: Float): Float = sp / (this.resources.displayMetrics.density)

fun Context.sp2px(sp: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    sp,
    this.resources.displayMetrics
)

fun Context.px2sp(px: Float): Float = px / this.resources.displayMetrics.scaledDensity

