package com.github.kr328.clash.core.entity

import android.annotation.SuppressLint
import android.widget.Toast
import com.github.kr328.clash.common.log.Log
import com.github.kr328.clash.common.util.login
import com.github.kr328.clash.core.model.post
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.time.Duration

class LoginEntity {
    @SuppressLint("SuspiciousIndentation")
    suspend fun LoginRequest(email:String, password:String){
        val url = login
        println("login link : $url")
        val body = mapOf<String,Any>(
            "email" to email,
            "password" to password
        )

           try {
               val response = post(url, body)
               val statusCode = response.code
               val responseBody = response.responseBody as String
               val parser = JsonParser()
               val jsonObject = parser.parse(responseBody).asJsonObject
               val dataObject = jsonObject.getAsJsonObject("data")
               val token = dataObject.get("token").asString
               val auth = dataObject.get("auth_data").asString
               val isAdmin = dataObject.get("is_admin").asInt

               println("token : $token   auth : $auth   isAdmin : $isAdmin")
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