package com.github.kr328.clash.design.util

import android.util.DisplayMetrics

fun getFem() :Int{
    val baseWidth = 375
    val displayMetrics = DisplayMetrics()
    val realWidth = displayMetrics.widthPixels
   return  realWidth / baseWidth


}