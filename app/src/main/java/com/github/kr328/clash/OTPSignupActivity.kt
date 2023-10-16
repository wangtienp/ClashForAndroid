package com.github.kr328.clash

import com.github.kr328.clash.common.util.intent
import com.github.kr328.clash.design.OTPDesignSignup
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
                        OTPDesignSignup.Request.GoBack->
                            finish()
                        OTPDesignSignup.Request.Verify ->
                            startActivity(SettingsActivity::class.intent)
                        OTPDesignSignup.Request.Resend ->
                            startActivity(SettingsActivity::class.intent)
                        OTPDesignSignup.Request.Setup ->{
                            design.setupOtp()
                        }
                    }
                }
            }
    }
}