package com.example.kaninistocks.userData

import java.sql.Timestamp

data class UserDetails(
    val id:Int=0,
    val token:String="",
    val email:String="",
    val memberSince:Timestamp,
    val password:String=""

)


