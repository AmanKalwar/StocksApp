package com.example.kaninistocks

import android.app.Application
import com.example.kaninistocks.APIs.ApiService
import com.example.kaninistocks.APIs.HttpApiService
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

//import com.fasterxml.jackson.databind.ObjectMapper

//import retrofit2.converter.jackson.JacksonConverterFactory

class MyApplication:Application() {
    public lateinit var apiService: ApiService

    override fun onCreate() {
        super.onCreate()
        apiService=initHttpApiService()
       // httpApiService=initHttpApiService()
    }

   /* private fun initHttpApiService():HttpApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud/priceapp-secure-backend/")
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
            .build()
        return retrofit.create(HttpApiService::class.java)
    }*/
   private fun initHttpApiService():ApiService{

       val retrofit = Retrofit.Builder()
           .baseUrl("https://android-kanini-course.cloud/priceapp-secure-backend/")
           .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
           .build()
       return retrofit.create(ApiService::class.java)
   }
}