package org.sopt.first.api

import org.sopt.first.data.request.RequestLoginData
import org.sopt.first.data.response.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("/login/signin")
    fun postLogin(
        @Body body: RequestLoginData
    ) : Call<ResponseLoginData>
}