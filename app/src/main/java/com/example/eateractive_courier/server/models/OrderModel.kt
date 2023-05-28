package com.example.eateractive_courier.server.models

import com.google.gson.annotations.SerializedName

data class OrderModel(
    @SerializedName("order_id")
    val id: Int,
    @SerializedName("restaurant_name")
    val name: String,
    @SerializedName("item_count")
    val itemCount: Int
)