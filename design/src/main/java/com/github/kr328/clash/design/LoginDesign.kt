package com.github.kr328.clash.design


import android.content.Context
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.github.kr328.clash.core.entity.LoginEntity
import com.github.kr328.clash.design.databinding.DesignLoginBinding
import com.github.kr328.clash.design.ui.ToastDuration
import com.github.kr328.clash.design.util.layoutInflater
import com.github.kr328.clash.design.util.root
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject
import org.json.JSONObject
import java.io.IOException


class LoginDesign(context: Context) : Design<LoginDesign.Request>(context) {

    enum class Request{
        TogglePassword,
        GoBack,
        OpenForget,
        OpenRegister,
        Login,
        Unfocused
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

            if(binding.toggle== true){
                binding.toggle = false
                binding.password.inputType = 1
            }
            else{
                binding.toggle = true
                binding.password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            }

    }
    private fun showSoftKeyboard(context: Context, editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideSoftKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    fun  hideOrShowKeyboard(){
        val editText1 = binding.email
        val editText2 = binding.password



        hideSoftKeyboard(this.context,editText1)
        hideSoftKeyboard(this.context,editText2)


        editText1.setOnClickListener {
            showSoftKeyboard(this.context, editText1)
        }
        editText2.setOnClickListener {
            showSoftKeyboard(this.context, editText2)
        }
    }

  suspend fun login(){
                  val email = binding.email.text.toString()
                  val password = binding.password.text.toString()
                  launch(Dispatchers.IO) {
                      try {
                         val loginResponse= LoginEntity().loginRequest(email, password)
                          println(loginResponse)
                          if (loginResponse != null) {
                              if (loginResponse.containsKey("status")){
                                  println(loginResponse["status"])
                                  val status = loginResponse["status"] as Int
                                  val responseData = loginResponse["response"]

                                  println(responseData)
                                  val responseDataString = responseData.toString()
                                  val jsonObject = JsonParser().parse(responseDataString).asJsonObject
                                  when (status) {
                                      200, 201 -> {
                                          val data = jsonObject.get("data").asJsonObject
                                          val token = data.get("token").asString
                                          val auth = data.get("auth_data").asString
                                          println("Successful token: $token auth : $auth")
                                      }
                                      500 -> {
                                          val message = jsonObject.get("message").asString
                                          showToast(message,ToastDuration.Short)
                                      }
                                      else ->{
                                          val errors = jsonObject.get("errors").asJsonObject
                                          for (entry in errors.entrySet()) {
                                              val errorArray = entry.value.asJsonArray
                                              if (errorArray.size()>0) {
                                                  val firstError = errorArray[0].asString
                                                  showToast(firstError,ToastDuration.Short)
                                                  break
                                              }
                                          }

                                      }
                                  }
                              }
                          }

                      }catch (e:IOException){
                          println("An error occurred")
                      }
                  }

  }
}


