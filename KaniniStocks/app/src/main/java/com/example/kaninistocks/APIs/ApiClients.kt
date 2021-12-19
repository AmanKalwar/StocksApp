package com.example.kaninistocks.APIs

import android.content.Context
import com.example.kaninistocks.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClients {
    private lateinit var apiInterface: ApiInterface

    fun getApiService(): ApiInterface {
        if (!::apiInterface.isInitialized) {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://android-kanini-course.cloud/priceapp-secure-backend/")
                .build()

            apiInterface = retrofit.create(ApiInterface::class.java)
        }
        return  apiInterface
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }


}