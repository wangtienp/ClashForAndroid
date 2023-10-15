package com.github.kr328.clash.design


import android.content.Context
import android.text.InputType
import android.view.View
import com.github.kr328.clash.core.entity.LoginEntity
import com.github.kr328.clash.design.databinding.DesignLoginBinding
import com.github.kr328.clash.design.util.layoutInflater
import com.github.kr328.clash.design.util.root
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginDesign(context: Context) : Design<LoginDesign.Request>(context) {

    enum class Request{
        TogglePassword,
        GoBack,
        OpenForget,
        OpenRegister,
        Login
    }

    private val binding = DesignLoginBinding
        .inflate(context.layoutInflater, context.root, false)
    override val root: View
        get() = binding.root



    init {
        binding.self = this
        binding.toggle = true
    }
     fun togglePassword(){
        binding.togglePassword.setOnClickListener(View.OnClickListener {
            if(binding.toggle== true){
                binding.toggle = false
                binding.password.inputType = 1
                println("binding.toggle: ${binding.toggle}")
            }
            else{
                binding.toggle = true
                binding.password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                println("binding.toggle: ${binding.toggle}")
            }
        })
    }

  suspend fun login(){
      withContext(Dispatchers.Main){
          binding.login.setOnClickListener(
              View.OnClickListener {
                  if(binding.email.text.isEmpty()){
                  binding.email.error ="Please fill in email"
                  binding.email.requestFocus()
                  return@OnClickListener
              }else if(binding.password.text.isEmpty()){
                  binding.password.error="Please fill in password"
                  binding.password.requestFocus()
                  return@OnClickListener}

                  val email = binding.email.text.toString()
                  val password = binding.password.text.toString()
                  launch(Dispatchers.IO) {
                      LoginEntity().LoginRequest(email, password)
                  }


          })
      }
  }

}