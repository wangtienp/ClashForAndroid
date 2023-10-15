package com.github.kr328.clash.core.entity

import android.widget.Toast
import com.github.kr328.clash.common.log.Log
import com.github.kr328.clash.common.util.login
import com.github.kr328.clash.core.model.post
import java.io.IOException
import java.time.Duration

class LoginEntity {
    suspend fun LoginRequest(email:String,password:String){
        val url = login
        val body = mapOf<String,String>(
            "email" to email,
            "password" to password
        )
        try {
            val response = post(url, body)
            val statusCode = response.code
            val responseBody = response.responseBody
            if (statusCode == 200 ||statusCode == 201){
                println("login successful")
                Log.d("success")
            }else{
                println("login failed with status code: $statusCode")
                Log.d("failed with $statusCode")
            }
        }catch (e:IOException){
            println("An error occured: ${e.message}")
            Log.e("${e.message}")
        }
    }
}