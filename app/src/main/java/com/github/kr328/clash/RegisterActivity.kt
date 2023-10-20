package com.github.kr328.clash

import android.widget.Toast
import com.github.kr328.clash.common.util.intent
import com.github.kr328.clash.common.util.register
import com.github.kr328.clash.design.LoginDesign
import com.github.kr328.clash.design.RegisterDesign
import kotlinx.coroutines.isActive
import kotlinx.coroutines.selects.select

class RegisterActivity:BaseActivity<RegisterDesign>() {
    override suspend fun main() {
        val design = RegisterDesign(this)
        setContentDesign(design)

        while (isActive){
            select<Unit> {
                events.onReceive{

                }
                design.requests.onReceive{
                    when(it){
                        RegisterDesign.Request.Register->{
                            val pass =design.register()
                            if(pass){
                                startActivity(OTPSignupActivity::class.intent)
                                Toast.makeText(this@RegisterActivity.applicationContext,"验证码发送成功\n" +
                                        "如果没有收到验证码请检查垃圾箱。",Toast.LENGTH_LONG).show()

                            }

                        }

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
                        RegisterDesign.Request.Unfocused->{
                            design.hideOrShowKeyboard()
                        }

                    }
                }
            }
        }
    }
}