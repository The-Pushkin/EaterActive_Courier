package com.example.eateractive_courier.order_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eateractive_courier.server.ServerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderDetailsViewModel(
    private val serverApi: ServerApi,
    private val orderId: Int
) : ViewModel() {
    fun confirmPickup() = viewModelScope.launch(Dispatchers.IO) {
        serverApi.confirmPickup(orderId)
    }
}