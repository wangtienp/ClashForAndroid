package com.github.kr328.clash

import android.widget.EditText
import com.github.kr328.clash.common.util.intent
import com.github.kr328.clash.design.LoginDesign
import kotlinx.coroutines.isActive
import kotlinx.coroutines.selects.select

class LoginActivity : BaseActivity<LoginDesign>(){
    override suspend fun main() {
        val design = LoginDesign(this)
        setContentDesign(design)
        findViewById<EditText>(R.id.password)
    while (isActive)
        select<Unit> {
            events.onReceive{

            }
            design.requests.onReceive{
                when(it){
                    LoginDesign.Request.OpenRegister->{
                        startActivity(RegisterActivity::class.intent)
                        finish()
                    }
                    LoginDesign.Request.OpenForget->
                        startActivity(SettingsActivity::class.intent)
                    LoginDesign.Request.Login->
                        startActivity(SettingsActivity::class.intent)
                    LoginDesign.Request.GoBack->
                        finish()
                    LoginDesign.Request.TogglePassword->
                        design.togglePassword()

                }
            }
        }
    }

}