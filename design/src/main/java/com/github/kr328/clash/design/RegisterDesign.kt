package com.github.kr328.clash.design

import android.content.Context
import android.content.Intent
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import com.github.kr328.clash.design.databinding.DesignRegisterBinding
import com.github.kr328.clash.design.ui.ToastDuration
import com.github.kr328.clash.design.util.layoutInflater
import com.github.kr328.clash.design.util.root

class RegisterDesign (context: Context) : Design<RegisterDesign.Request>(context){
    enum class Request{
        TogglePassword,
        TogglePasswordConfirm,
        GoBack,
        OpenLogin,
        Register,
        Unfocused
    }

    private val binding = DesignRegisterBinding
        .inflate(context.layoutInflater, context.root, false)
    override val root: View
        get() = binding.root

    init {
        binding.self = this
        binding.toggle=true
        binding.toggleConfirm=true
        val emailList = arrayOf("@gmail.com","@qq.com","@yahoo.com",
            "@163.com","@sina.com","@126.com","@outlook.com","@yeah.net","@foxmail.com")

        val emailSpinner = binding.emailOption
        val customAdapter = ArrayAdapter(this.context,R.layout.spinner_textview, emailList)
        customAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        emailSpinner.adapter = customAdapter

        emailSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                emailAlias = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }
    //初始后缀
    private var emailAlias = "@gmail.com"
    private var emailCompleted = binding.email.text.toString() + emailAlias

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


   suspend fun register():Int{
       if(binding.email.text.isEmpty()){
           showToast("邮箱格式不正确",ToastDuration.Short)
           return 0
       }
        if (binding.password.text.isEmpty()||binding.confirmPassword.text.isEmpty()){
            showToast("密码不能为空",ToastDuration.Short)
            return 0
        }else if (binding.password.text.length<8||binding.confirmPassword.text.length<8){
            showToast("密码必须大于 8 个字符",ToastDuration.Short)
            return 0
        }else if(!binding.password.text.equals(binding.confirmPassword.text)){
            showToast("密码必须一致",ToastDuration.Short)
            return 0
        }
        emailCompleted = binding.email.text.toString() + emailAlias
       return 1

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
        val editText3 = binding.confirmPassword


            hideSoftKeyboard(this.context,editText1)
            hideSoftKeyboard(this.context,editText2)
            hideSoftKeyboard(this.context,editText3)

        editText1.setOnClickListener {
            showSoftKeyboard(this.context, editText1)
        }
        editText2.setOnClickListener {
            showSoftKeyboard(this.context, editText2)
        }
        editText3.setOnClickListener {
            showSoftKeyboard(this.context, editText3)
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