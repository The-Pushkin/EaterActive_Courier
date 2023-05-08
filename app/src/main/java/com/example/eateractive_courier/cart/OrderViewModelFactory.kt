package com.example.eateractive_courier.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OrderViewModelFactory(
    private val database: OrderDatabase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrderViewModel(
            database
        ) as T
    }
}