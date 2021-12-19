package com.example.kaninistocks.APIs

import android.telecom.Call
import com.example.kaninistocks.*
import com.example.kaninistocks.userData.*
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @POST(Constants.Register_URL)
    suspend fun Register(@Body request: LoginRequestData)

    @POST(Constants.Register_URL)
    suspend fun register(@Body user: User): Response<LoginRequestData>

    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): Response<LoginRespondData>
    @GET("stocks")
    suspend fun GetStocks(@Header("Authorization") token: String): Response<StockitemsList>

    @GET("users")
    suspend fun GetUsers(@Header("Authorization") token: String): Response<OtherUserList>

    @POST("users/me/email")
    suspend fun change_email(@Header("Authorization") token: String, @Body updateRequest: EmailChangeRequest): Response<EmailChangeRequest>

    @POST("users/me/ownings")
    suspend fun BuyStocks(@Header("Authorization") token: String, @Body request: Stocks): Response<Unit> //To be checked

    @GET("users/me/loginHistory")
    suspend fun GetLoginhistory(@Header("Authorization") token: String): Response<LoginDataList>

    @DELETE("users/me")
    suspend fun  delete(@Header("Authorization") token: String)

    @GET("users/me/ownings")
    suspend fun fetchOwnings(@Header("Authorization") token: String): Response<OrdersHistoryList>

    @DELETE("users/me/ownings/{owningId}")
    suspend fun DeleteOwnings(@Header("Authorization") token: String, @Path("orderId")orderId:Int): Response<Unit>

}