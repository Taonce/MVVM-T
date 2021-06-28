package com.taonce.mvvm.util

import android.content.Context
import android.graphics.drawable.Drawable

fun Context.getString(stringId: Int) = resources.getString(stringId)

fun Context.getColor(colorId: Int) = resources.getColor(colorId)

fun Context.getDrawable(drawableId: Int): Drawable? = resources.getDrawable(drawableId)