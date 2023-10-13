package com.github.kr328.clash.core.bridge

import com.github.kr328.clash.common.dataclass.LoginModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.github.kr328.clash.common.util.login as login

interface CallForRetro {
    @POST(login)
    suspend fun login(@Body post: LoginModel):Response<LoginModel>
}