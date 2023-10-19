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

    suspend fun loginRequest(email: String, password: String): Map<String, Any>? {
        val url = login
        println("login link : $url")
        val body = mapOf<String, Any>(
            "email" to email,
            "password" to password
        )

        return withContext(Dispatchers.IO) {
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

            } catch (e: IOException) {
                throw IOException("Exception $e")
            }
        }
    }
}