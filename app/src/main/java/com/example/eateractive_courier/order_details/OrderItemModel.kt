package com.example.eateractive_courier.order_details

import com.example.eateractive_courier.cart.entity.OrderItemEntity

sealed interface OrderItemModel {
    data class OrderItem(
        val orderItemEntity: OrderItemEntity
    ) : OrderItemModel {
        companion object {
            operator fun invoke(name: String): OrderItem =
                OrderItem(OrderItemEntity(0, name))
        }
    }

    object Divider : OrderItemModel
}