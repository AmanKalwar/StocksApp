package com.example.kaninistocks

data class stock(
    var name:String="",
    var price: String =0,
    var userId: String = 0,
    var stockid: Int = 0,
    var owningId: Int = 0,
    val count: Int? = 0,
    val url: String ="",
    val dateTimestamp: String = ""
)
