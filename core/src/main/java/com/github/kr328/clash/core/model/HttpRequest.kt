package com.github.kr328.clash.core.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException


private val mediaType = "application/json; charset=utf-8".toMediaType()
data class ResponseData(val code:Int, val responseBody:String?)
suspend fun post(url:String, body: Map<String,Any>):ResponseData{
    val client = OkHttpClient()

    val requestBody = body.toString().toRequestBody(mediaType)
    val request = Request.Builder().url(url).post(requestBody).build()

    return withContext(Dispatchers.IO){
        try {
            val response = client.newCall(request).execute()
            val responseBody = response.body?.toString()
            ResponseData(response.code,responseBody)
        }catch (e:IOException){
            throw IOException("Exception $e")
        }

    }
}
suspend fun get(url: String):Map<String,Any>?{
    val client = OkHttpClient()
    val gson = Gson()
    val request = Request.Builder().url(url).get().build()

    return withContext(Dispatchers.IO){
        try {
            val response = client.newCall(request).execute()
            println("Retrieving data")
            if (response.code==200){
                println("Success")
                val responseBody = response.body?.string()
                val type = object :TypeToken<Map<String,Any>>(){}.type
                val responseData:Map<String,Any>? = gson.fromJson(responseBody,type)
                responseData
            }else{
                null
            }
        }catch (e:IOException){
            null
        }
    }
}