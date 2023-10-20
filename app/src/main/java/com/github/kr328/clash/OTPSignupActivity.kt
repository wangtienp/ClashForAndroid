package com.github.kr328.clash

import android.content.Context
import androidx.databinding.DataBindingUtil
import com.github.kr328.clash.common.util.intent
import com.github.kr328.clash.common.util.timeEnd
import com.github.kr328.clash.design.OTPDesignSignup
import com.github.kr328.clash.design.databinding.DesignOtpSignupBinding
import kotlinx.coroutines.isActive
import kotlinx.coroutines.selects.select

class OTPSignupActivity:BaseActivity<OTPDesignSignup>(){
    override suspend fun main() {
        val design = OTPDesignSignup(this)
        setContentDesign(design)
        while (isActive)
            select<Unit> {
                events.onReceive{

                }
                design.requests.onReceive{
                    when(it){
                        OTPDesignSignup.Request.GoBack->{
                            design.onBackClicked()
                            finish()
                        }
                        OTPDesignSignup.Request.Verify ->
                            design.verify()
                        OTPDesignSignup.Request.Resend ->
                            design.resendOtp()
                        OTPDesignSignup.Request.Setup ->{
                            design.setupOtp()
                        }
                        OTPDesignSignup.Request.SetupBackward ->{
                            design.setupOtpBackward()
                        }
                    }
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy()")
        var estimatedEndTime = System.currentTimeMillis() +60000
        println(estimatedEndTime)
        val sharedPreferences = getSharedPreferences("isCountDown",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong(timeEnd,estimatedEndTime)

        editor.apply()
    }
}