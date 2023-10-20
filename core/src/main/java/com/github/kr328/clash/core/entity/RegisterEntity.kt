package com.github.kr328.clash.core.entity

import android.annotation.SuppressLint
import com.github.kr328.clash.common.log.Log
import com.github.kr328.clash.common.util.login
import com.github.kr328.clash.common.util.register
import com.github.kr328.clash.core.model.post
import com.google.gson.JsonParser
import java.io.IOException
import com.github.kr328.clash.common.util.verifyEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterEntity {
    @SuppressLint("SuspiciousIndentation")
    suspend fun registerRequest(email:String, password:String,email_code:Int){
        val url = register

        val body = mapOf<String,Any>(
            "email" to email,
            "password" to password,
            "email_code" to email_code
        )

        try {
            val response = post(url, body)
            val statusCode = response.code
            val responseBody = response.responseBody as String
            val parser = JsonParser()
            val jsonObject = parser.parse(responseBody).asJsonObject
            if(statusCode == 200 || statusCode ==201){
                println("register successful")
                val dataObject = jsonObject.getAsJsonObject("data")

            }
            else{
                println("login failed with status code: ")
                Log.d("failed with $statusCode")
            }
        }catch (e: IOException){
            println("An error occured: ${e.message}")
            Log.e("${e.message}")
        }


    }
    suspend fun verifyEmail(email: String): Map<String,Any>?{
        val url= verifyEmail
        val body = mapOf<String,Any>(
            "email" to email
        )
        return withContext(Dispatchers.IO){
            try {
                val response = post(url, body)
                val statusCode = response.code
                val responseBody = response.responseBody as String
                val parser = JsonParser()
                val jsonObject = parser.parse(responseBody).asJsonObject
                val responseData = mapOf<String, Any>(
                    "status" to statusCode,
                    "response" to jsonObject
                )
                responseData
            }catch (e:IOException){
                throw IOException("Exception $e")
            }
        }
    }
}