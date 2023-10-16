package com.github.kr328.clash.core.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineExceptionHandler
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
import okio.Buffer
import java.io.IOException


val coroutineHandler = CoroutineExceptionHandler { _, throwable ->throwable.printStackTrace()  }

private val mediaType = "application/json".toMediaType()
data class ResponseData(val code:Int, val responseBody:Any?)
suspend fun post(url:String, body: Map<String,Any>):ResponseData{

    val gson = Gson()
    val json =gson.toJson(body)
    val requestBody = json.toRequestBody()
    val requestBodyString = try {
        val buffer = Buffer()
        requestBody.writeTo(buffer)
        buffer.readUtf8()
    } catch (e: IOException) {
        "Error reading RequestBody: ${e.message}"
    }
// Log the RequestBody string
    println("Request Body: $requestBodyString")
    val request = Request.Builder().url(url).post(requestBody).addHeader("Content-Type", "application/json").build()
    println(request)
    val client = OkHttpClient()
    return withContext(Dispatchers.IO + coroutineHandler){
        try {

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()
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