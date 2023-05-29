package com.example.eateractive_courier.server

import com.example.eateractive_courier.server.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServerApi {
    @POST("courier/signup")
    suspend fun signup(@Body signupModel: SignupModel): Response<JwtModel>

    @POST("courier/login")
    suspend fun login(@Body loginModel: LoginModel): Response<JwtModel>

    @GET("orders")
    suspend fun getOrders(): Response<List<OrderModel>>

    @GET("orders/content/{order_id}")
    suspend fun getOrderItems(
        @Path("order_id") orderId: Int
    ): Response<List<OrderItemModel>>

    @GET("restaurants/address/{order_id}")
    suspend fun getRestaurantAddress(
        @Path("order_id") orderId: Int
    ): Response<RestaurantAddressModel>

    @POST("orders/confirm_pickup/{order_id}")
    suspend fun confirmPickup(
        @Path("order_id") orderId: Int
    ): Response<Void>

    @GET("orders/address/{order_id}")
    suspend fun getDeliveryAddress(
        @Path("order_id") orderId: Int
    ): Response<DeliveryAddressModel>

    @POST("orders/confirm_delivered/{order_id}")
    suspend fun confirmDelivered(
        @Path("order_id") orderId: Int
    ): Response<Void>
}