package com.github.kr328.clash.core.model

import com.github.kr328.clash.common.util.login
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response


val JSON = MediaType.get()
fun post(url:String,headers:Map<String,String>, body: Map<String,Any>):Response{
    val client = OkHttpClient()

    val request = Request.Builder().url(url).post()
}
