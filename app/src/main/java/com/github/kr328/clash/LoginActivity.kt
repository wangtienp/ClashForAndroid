package com.github.kr328.clash

import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import com.github.kr328.clash.design.LoginDesign
@Suppress("DEPRECATION")
class LoginActivity : BaseActivity<LoginDesign>(){

    @RequiresApi(Build.VERSION_CODES.R)
    override suspend fun main() {
        val design = LoginDesign(this)
        setContentDesign(design)

        val baseHeight = 812
        val baseWidth = 375
        val displayMetrics = DisplayMetrics()
        var realHeight = displayMetrics.heightPixels
        var realWidth = displayMetrics.widthPixels
        var femHeight = realHeight/baseHeight
        var femWidth = realWidth/baseWidth
    }

}