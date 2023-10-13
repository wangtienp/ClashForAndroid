package com.github.kr328.clash.design

import android.content.Context
import android.view.View
import com.github.kr328.clash.design.databinding.DesignOtpSignupBinding
import com.github.kr328.clash.design.util.layoutInflater
import com.github.kr328.clash.design.util.root

class OTPDesignSignup(context: Context) : Design<OTPDesignSignup.Request>(context) {

    enum class Request{
        GoBack,
        Verify,
        Resend
    }

    private val binding = DesignOtpSignupBinding
        .inflate(context.layoutInflater, context.root, false)
    override val root: View
        get() = binding.root

    init {
        binding.self = this

    }
}