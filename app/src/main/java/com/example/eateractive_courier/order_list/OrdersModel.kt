package com.example.eateractive_courier.order_list

sealed interface OrdersModel {
    data class Order(
        val id: Int,
        val name: String,
        val itemCount: Int
    ) : OrdersModel

    object Divider : OrdersModel
}