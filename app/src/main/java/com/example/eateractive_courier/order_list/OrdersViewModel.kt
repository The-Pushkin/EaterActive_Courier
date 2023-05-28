package com.example.eateractive_courier.order_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eateractive_courier.server.ServerApi
import com.example.eateractive_courier.server.models.OrderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val serverApi: ServerApi
) : ViewModel() {
    private val _orders: MutableLiveData<List<OrderModel>> = MutableLiveData(emptyList())
    val orders: LiveData<List<OrderModel>> = _orders

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                val response = serverApi.getOrders()
                _orders.postValue(response.body())
                delay(1000)
            }
        }
    }
}