package com.github.kr328.clash.design

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.github.kr328.clash.design.databinding.DesignOtpSignupBinding
import com.github.kr328.clash.design.util.layoutInflater
import com.github.kr328.clash.design.util.root

class OTPDesignSignup(context: Context) : Design<OTPDesignSignup.Request>(context) {

    enum class Request{
        GoBack,
        Verify,
        Resend,
        Setup
    }

    private val binding = DesignOtpSignupBinding
        .inflate(context.layoutInflater, context.root, false)
    override val root: View
        get() = binding.root

    init {
        binding.self = this

    }
    fun setupOtp(){
       binding.otp1.addTextChangedListener(object :TextWatcher{
           override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
           override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               if(s?.length==1){
                   binding.otp2.requestFocus()
               }
           }
           override fun afterTextChanged(s: Editable?) {

           }
       })
        binding.otp2.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if(s?.length==1){
                    binding.otp3.requestFocus()
                }
            }
        })

    }


}