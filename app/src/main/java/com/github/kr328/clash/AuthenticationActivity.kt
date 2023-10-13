package com.github.kr328.clash

import com.github.kr328.clash.common.util.intent
import com.github.kr328.clash.design.AuthenticationDesign
import kotlinx.coroutines.isActive
import kotlinx.coroutines.selects.select

class AuthenticationActivity:BaseActivity<AuthenticationDesign>() {
    override suspend fun main() {
        val design = AuthenticationDesign(this)
        setContentDesign(design)
        while (isActive)
            select<Unit> {
                events.onReceive{

                }
                design.requests.onReceive{
                    when(it){
                        AuthenticationDesign.Request.Login->
                            startActivity(LoginActivity::class.intent)
                        AuthenticationDesign.Request.Register->
                            startActivity(SettingsActivity::class.intent)
                    }
                }
            }
    }
}