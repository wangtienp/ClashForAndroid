package com.github.kr328.clash.design

import android.content.Context
import android.text.InputType
import android.view.View
import com.github.kr328.clash.design.databinding.DesignRegisterBinding
import com.github.kr328.clash.design.util.layoutInflater
import com.github.kr328.clash.design.util.root

class RegisterDesign (context: Context) : Design<RegisterDesign.Request>(context){
    enum class Request{
        TogglePassword,
        TogglePasswordConfirm,
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
        binding.toggle=true
        binding.toggleConfirm=true
    }

    fun togglePassword(){

            if(binding.toggle== true){
                binding.toggle = false
                binding.password.inputType = 1

            }
            else{
                binding.toggle = true
                binding.password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            }

    }
    fun togglePasswordConfirm(){

            if(binding.toggleConfirm== true){
                binding.toggleConfirm = false
                binding.confirmPassword.inputType = 1

            }
            else{
                binding.toggleConfirm = true
                binding.confirmPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            }
    }

}