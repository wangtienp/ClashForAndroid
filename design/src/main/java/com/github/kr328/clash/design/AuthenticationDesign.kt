package com.github.kr328.clash.design

import android.content.Context
import android.view.View
import com.github.kr328.clash.design.databinding.DesignAuthenticationBinding
import com.github.kr328.clash.design.util.layoutInflater
import com.github.kr328.clash.design.util.root

class AuthenticationDesign(context: Context): Design<AuthenticationDesign.Request>(context) {

    enum class Request{
        Login,
        Register
    }

    private val binding = DesignAuthenticationBinding.inflate(
        context.layoutInflater,context.root,false)

    override val root: View
        get() = binding.root

    init {
        binding.self=this
    }
}