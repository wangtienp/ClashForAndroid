package com.github.kr328.clash.design

import android.content.Context
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.github.kr328.clash.design.databinding.DesignOtpSignupBinding
import com.github.kr328.clash.design.util.layoutInflater
import com.github.kr328.clash.design.util.root

class OTPDesignSignup(context: Context) : Design<OTPDesignSignup.Request>(context) {

    enum class Request{
        GoBack,
        Verify,
        Resend,
        Setup,
        SetupBackward
    }



    private val binding = DesignOtpSignupBinding
        .inflate(context.layoutInflater, context.root, false)
    override val root: View
        get() = binding.root

    init {
        binding.self = this

    }
    private val editTextList :List<EditText> = listOf(
        binding.otp1,
        binding.otp2,
        binding.otp3,
        binding.otp4,
        binding.otp5,
        binding.otp6,
    )
    private var editTextValue = MutableLiveData<String>()
    private var editTextValueList = Array(6){""}
    private var totalTimesInMillis:Long = 60000
   fun onEditText(index: Int,newText:CharSequence){
       val text = newText.toString()

       if (text.isNotEmpty()){

            this.request(Request.Setup)
           editTextValueList[index]=text

       }else{
           this.request(Request.SetupBackward)
           editTextValueList[index]=""

       }
   }
   fun verify(){
        editTextValue.value = editTextValueList.joinToString("")
       println(editTextValue.value)
   }
    fun setupOtp(){
        val lastEditTextIndex = editTextList.indexOf(editTextList.lastOrNull{it.isFocused})
        if (lastEditTextIndex !=-1 && lastEditTextIndex < editTextList.size-1){
            editTextList[lastEditTextIndex+1].requestFocus()
        }
    }
    fun setupOtpBackward(){
        val lastEditTextIndex = editTextList.indexOf(editTextList.lastOrNull{it.isFocused})
        if (lastEditTextIndex !=-1 && lastEditTextIndex != 0 ){
            editTextList[lastEditTextIndex-1].requestFocus()
        }

    }

    fun resendOtp(){
        countDownTimer = object:CountDownTimer(totalTimesInMillis,1000){

        }
    }
    fun request(request: OTPDesignSignup.Request) {
        requests.trySend(request)
    }


}