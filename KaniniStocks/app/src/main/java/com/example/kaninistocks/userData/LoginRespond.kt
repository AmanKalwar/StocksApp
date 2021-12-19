package com.example.kaninistocks.userData

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class LoginRespond(
    @SerializedName("id")
    var id:Int=0,
    @SerializedName("token")
    var token:String="",
    @SerializedName("email")
    var email:String="",
    @SerializedName("memberSince")
    var memberSince: Long=0,
    @SerializedName("password")
    var password:String=""
)


