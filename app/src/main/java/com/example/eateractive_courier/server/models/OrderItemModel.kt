package com.example.eateractive_courier.server.models

import com.google.gson.annotations.SerializedName

data class OrderItemModel(
    @SerializedName("item_id")
    val id: Int,
    val name: String
)