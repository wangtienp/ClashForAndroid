package com.github.kr328.clash.design

import android.content.Context
import android.view.View
import com.github.kr328.clash.design.databinding.DesignRegisterBinding
import com.github.kr328.clash.design.util.layoutInflater
import com.github.kr328.clash.design.util.root

class RegisterDesign (context: Context) : Design<RegisterDesign.Request>(context){
    enum class Request{
        TogglePassword,
        GoBack,
        OpenLogin,
        Register
    }

    private val binding = DesignRegisterBinding
        .inflate(context.layoutInflater, context.root, false)
    override val root: View
        get() = binding.root

    init {
        binding.self = this

    }

}