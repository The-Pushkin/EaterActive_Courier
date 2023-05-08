package com.example.eateractive_courier.cart.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.eateractive_courier.cart.entity.OrderItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM order_table")
    fun getAll(): Flow<List<OrderItemEntity>>

    @Insert
    suspend fun insert(orderItemEntity: OrderItemEntity)

    @Delete
    suspend fun delete(orderItemEntity: OrderItemEntity)
}