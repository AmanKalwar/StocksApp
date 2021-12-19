package com.example.kaninistocks.userData

data class LoginRespondData(
    val email: String,
    val id: Int,
    val memberSince: Long,
    val password: Any,
    val token: String
)