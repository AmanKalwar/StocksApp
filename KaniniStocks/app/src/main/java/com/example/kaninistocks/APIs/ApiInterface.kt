package com.example.kaninistocks.APIs

import com.example.kaninistocks.*
import com.example.kaninistocks.userData.EmailChangeRequest
import com.example.kaninistocks.userData.LoginRequestData
import com.example.kaninistocks.userData.LoginRespondData
import com.example.kaninistocks.userData.RegisterRespond
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @POST("login")
    fun loginUser(@Body requestLogin: LoginRequestData): Call<LoginRespondData>?
    @POST("users/me/email")
    fun changeEmail(email:String):Call<Void>

    @POST("register")
     fun rigister(@Body requestLogin: LoginRequestData): Call<Void>?

    @GET("stocks")
    suspend fun GetDishes(@Header("Authorization") token: String): Response<StockitemsList>

    @GET("users")
    suspend fun GetUsers(@Header("Authorization") token: String): Response<OtherUserList>

    @POST("users/me/email")
    suspend fun change_email(@Header("Authorization") token: String, @Body updateRequest: EmailChangeRequest): Response<EmailChangeRequest>

    @POST("users/me/ownings")
    suspend fun PlaceOrders(@Header("Authorization") token: String, @Body request: Stocks): Response<Unit> //To be checked

    @GET("users/me/loginHistory")
    suspend fun GetLoginhistory(@Header("Authorization") token: String): Response<LoginDataList>

    @DELETE("users/me")
    suspend fun  delete(@Header("Authorization") token: String)

    @GET("users/me/ownings")
    suspend fun fetchOrders(@Header("Authorization") token: String): Response<OrdersHistoryList>

    @DELETE("users/me/ownings/{owningId}")
    suspend fun DeleteOrders(@Header("Authorization") token: String, @Path("orderId")orderId:Int): Response<Unit>

}