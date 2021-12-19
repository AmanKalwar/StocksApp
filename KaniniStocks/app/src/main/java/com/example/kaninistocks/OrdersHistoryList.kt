package com.example.kaninistocks

data class OrdersHistoryList(
    val stockOrders:List<OrdersHistory>?= listOf<OrdersHistory>()
)
