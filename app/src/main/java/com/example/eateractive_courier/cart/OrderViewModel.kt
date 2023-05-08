package com.example.eateractive_courier.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eateractive_courier.cart.entity.OrderItemEntity
import kotlinx.coroutines.launch

class OrderViewModel(private val database: OrderDatabase) : ViewModel() {
    private val _menuItems: MutableLiveData<List<OrderItemEntity>> = MutableLiveData(emptyList())
    val menuItems: LiveData<List<OrderItemEntity>> = _menuItems

    init {
        viewModelScope.launch {
            database.cartDao().getAll().collect {
                _menuItems.value = it
            }
        }
    }

    fun addToCart(orderItemEntity: OrderItemEntity) = viewModelScope.launch {
        database.cartDao().insert(orderItemEntity)
    }

    fun removeFromCart(orderItemEntity: OrderItemEntity) = viewModelScope.launch {
        database.cartDao().delete(orderItemEntity)
    }
}
