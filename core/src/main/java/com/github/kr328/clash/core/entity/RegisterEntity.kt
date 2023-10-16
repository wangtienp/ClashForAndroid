package com.github.kr328.clash.core.entity

import android.annotation.SuppressLint
import com.github.kr328.clash.common.log.Log
import com.github.kr328.clash.common.util.login
import com.github.kr328.clash.common.util.register
import com.github.kr328.clash.core.model.post
import java.io.IOException

class RegisterEntity {
    @SuppressLint("SuspiciousIndentation")
    suspend fun registerRequest(email:String, password:String,email_code:Int){
        val url = register
        println("login link : $url")
        val body = mapOf<String,Any>(
            "email" to email,
            "password" to password,
            "email_code" to email_code
        )

        try {
            val response = post(url, body)
            val statusCode = response.code
            val responseBody = response.responseBody
            if (statusCode == 200 ||statusCode == 201){
                println("login successful")
                Log.d("success")
            }else{
                println("login failed with status code: ")
                Log.d("failed with $statusCode")
            }
        }catch (e: IOException){
            println("An error occured: ${e.message}")
            Log.e("${e.message}")
        }


    }
}