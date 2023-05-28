package com.example.eateractive_courier.delivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eateractive_courier.server.ServerApi

class DeliveryViewModelFactory(
    private val serverApi: ServerApi,
    private val orderId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DeliveryViewModel(
            serverApi,
            orderId
        ) as T
    }
}