package com.example.eateractive_courier.order_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eateractive_courier.delivery.DeliveryViewModel
import com.example.eateractive_courier.server.ServerApi

class OrderDetailsViewModelFactory(
    private val serverApi: ServerApi,
    private val orderId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrderDetailsViewModel(
            serverApi,
            orderId
        ) as T
    }
}