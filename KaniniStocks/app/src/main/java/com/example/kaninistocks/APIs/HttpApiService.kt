package com.example.kaninistocks.APIs

import com.example.kaninistocks.userData.LoginDetails
import com.example.kaninistocks.userData.UserDetails
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HttpApiService {
    @POST("login")
    @FormUrlEncoded
    suspend fun userLogin(@Body details:LoginDetails): Call<UserDetails>
}