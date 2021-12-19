package com.example.kaninistocks

data class OrdersHistory(
    var userId:Int=0,
    var stockid: Int=0,
    var owningId:Int=0,
    val count: Int?=0,
    val dateTimestamp : String=""
)
