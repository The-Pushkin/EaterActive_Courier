package com.example.eateractive_courier.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eateractive_courier.server.ServerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeliveryViewModel(
    private val serverApi: ServerApi,
    private val orderId: Int
) : ViewModel() {
    private val _deliveryAddress: MutableLiveData<String> = MutableLiveData("Loading...")
    val deliveryAddress: LiveData<String> = _deliveryAddress

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val orderStatus = serverApi.getDeliveryAddress(orderId)
            _deliveryAddress.postValue(orderStatus.body()?.address ?: "no_address")
        }
    }

    fun confirmDelivered() = viewModelScope.launch(Dispatchers.IO) {
        serverApi.confirmDelivered(orderId)
    }
}