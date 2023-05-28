package com.example.eateractive_courier.order_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eateractive_courier.server.ServerApi

class OrdersViewModelFactory(
    private val serverApi: ServerApi
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrdersViewModel(
            serverApi
        ) as T
    }
}