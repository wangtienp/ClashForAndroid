package com.github.kr328.clash.design

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
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
    private var timeLeftInSeconds:Long = 0
    private var timeLeftInMillis:Long=0
    val sharedPreferences = context.getSharedPreferences("isCountDown",Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    init {
        binding.self = this
        if(true){
            countDownTimer()
        }else{
            binding.resendOtp.setText("再次發送")
            binding.resendOtp.isClickable = true
            binding.resendOtp.setTextColor(Color.parseColor("#5557F5"))
        }

    }
    fun onBackClicked(){
        var endTime = System.currentTimeMillis() + totalTimesInMillis
        editor.putLong("endTime",endTime)
        editor.apply()
    }

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
    private fun countDownTimer(){
        object : CountDownTimer(totalTimesInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                timeLeftInSeconds = millisUntilFinished/1000
                binding.resendOtp.setText("請在$timeLeftInSeconds 秒內重試")
                binding.resendOtp.isClickable = false
                binding.resendOtp.setTextColor(Color.GRAY)
            }

            override fun onFinish() {
                binding.resendOtp.setText("再次發送")
                binding.resendOtp.isClickable = true
                binding.resendOtp.setTextColor(Color.parseColor("#5557F5"))
            }
        }.start()
    }

    fun resendOtp(){
//         object : CountDownTimer(totalTimesInMillis, 1000) {
//
//            override fun onTick(millisUntilFinished: Long) {
//                timeLeftInSeconds = millisUntilFinished/1000
//                binding.resendOtp.setText("請在$timeLeftInSeconds 秒內重試")
//                binding.resendOtp.isClickable = false
//                binding.resendOtp.setTextColor(Color.GRAY)
//            }
//
//            override fun onFinish() {
//                binding.resendOtp.setText("再次發送")
//                binding.resendOtp.isClickable = true
//                binding.resendOtp.setTextColor(Color.parseColor("#5557F5"))
//            }
//        }.start()
    }
    fun request(request: OTPDesignSignup.Request) {
        requests.trySend(request)
    }


}