package com.example.eateractive_courier.cart

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eateractive_courier.cart.dao.OrderDao
import com.example.eateractive_courier.cart.entity.OrderItemEntity

@Database(
    entities = [
        OrderItemEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class OrderDatabase : RoomDatabase() {
    abstract fun cartDao(): OrderDao
}

fun cartDatabase(context: Context) = Room.databaseBuilder(
    context,
    OrderDatabase::class.java, "order_database"
).build()