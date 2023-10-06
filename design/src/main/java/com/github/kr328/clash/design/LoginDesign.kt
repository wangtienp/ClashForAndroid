package com.github.kr328.clash.design

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import com.github.kr328.clash.design.databinding.DesignLoginBinding
import com.github.kr328.clash.design.util.layoutInflater
import com.github.kr328.clash.design.util.root

class LoginDesign(context: Context) : Design<LoginDesign.Request>(context) {

    sealed class Request{

    }

    private val binding = DesignLoginBinding
        .inflate(context.layoutInflater, context.root, false)
    override val root: View
        get() = binding.root

    init {
        binding.self = this
        val baseHeight = 812
        val baseWidth = 375
        val displayMetrics = DisplayMetrics()
        val realHeight = displayMetrics.heightPixels
        val realWidth = displayMetrics.widthPixels
        var femHeight = realHeight/baseHeight
        var femWidth = realWidth/baseWidth

    }

}