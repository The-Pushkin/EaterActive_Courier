package com.example.eateractive_courier.server

import com.example.eateractive_courier.server.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServerApi {
    @GET("test")
    suspend fun getTest(): Response<TestModel>

    @GET("orders")
    suspend fun getOrders(): Response<List<OrderModel>>

    @GET("orders/content/{order_id}")
    suspend fun getOrderItems(
        @Path("order_id") orderId: Int
    ): Response<List<OrderItemModel>>

    @POST("orders/confirm_pickup/{order_id}")
    suspend fun confirmPickup(
        @Path("order_id") orderId: Int
    ): Response<Void>

    @GET("restaurants/address/{restaurant_id}")
    suspend fun getRestaurantAddress(
        @Path("restaurant_id") restaurantId: Int
    ): Response<RestaurantAddressModel>

    @GET("restaurants/orders/address/{order_id}")
    suspend fun getDeliveryAddress(
        @Path("order_id") orderId: Int
    ): Response<DeliveryAddressModel>

    @POST("orders/confirm_delivered/{order_id}")
    suspend fun confirmDelivered(
        @Path("order_id") orderId: Int
    ): Response<Void>
}