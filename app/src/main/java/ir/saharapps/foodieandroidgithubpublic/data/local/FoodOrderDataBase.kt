package ir.saharapps.foodieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.saharapps.foodieapp.domain.model.FoodOrder
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import ir.saharapps.foodieapp.domain.model.UserAddress

@Database(entities = [FoodOrder::class, UserAddress::class, FoodOrderHistory::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class FoodOrderDataBase: RoomDatabase(){

    abstract fun foodOrderDao(): FoodOrderDao
    abstract fun userAddressDao(): UserAddressDao
    abstract fun foodOrderHistoryDao(): FoodOrderHistoryDao
}