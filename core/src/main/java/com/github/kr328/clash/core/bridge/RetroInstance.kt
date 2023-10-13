package com.github.kr328.clash.core.bridge

import com.github.kr328.clash.common.util.baseApiUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl(baseApiUrl) // Replace with your API base URL
    .addConverterFactory(GsonConverterFactory.create())
    .build()