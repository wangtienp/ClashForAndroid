package com.github.kr328.clash

import com.github.kr328.clash.common.util.intent
import com.github.kr328.clash.design.LoginDesign
import com.github.kr328.clash.design.RegisterDesign
import kotlinx.coroutines.isActive
import kotlinx.coroutines.selects.select

class RegisterActivity:BaseActivity<RegisterDesign>() {
    override suspend fun main() {
        val design = RegisterDesign(this)
        setContentDesign(design)

        R.layout.design_register
        while (isActive){
            select<Unit> {
                events.onReceive{

                }
                design.requests.onReceive{
                    when(it){
                        RegisterDesign.Request.Register->
                            startActivity(OTPSignupActivity::class.intent)
                        RegisterDesign.Request.GoBack->
                            finish()
                        RegisterDesign.Request.OpenLogin->{
                            startActivity(LoginActivity::class.intent)
                            finish()
                        }
                        RegisterDesign.Request.TogglePasswordConfirm->{
                            design.togglePasswordConfirm()
                        }
                        RegisterDesign.Request.TogglePassword->
                            design.togglePassword()
                    }
                }
            }
        }
    }
}