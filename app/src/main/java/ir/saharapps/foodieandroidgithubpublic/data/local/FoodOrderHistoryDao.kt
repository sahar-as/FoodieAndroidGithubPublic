package ir.saharapps.foodieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory

@Dao
interface FoodOrderHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFoodOrderHistory(foodOrderHistory: FoodOrderHistory)

    @Query("SELECT * FROM food_order_history")
    suspend fun getAllFoodOrderHistory(): List<FoodOrderHistory>

    @Query("DELETE FROM food_order_history WHERE orderId LIKE :orderId")
    suspend fun deleteOrderHistoryById(orderId: Int)

    @Query("SELECT * FROM food_order_history WHERE orderId LIKE :orderId")
    suspend fun getOrderHistoryById(orderId: Int): FoodOrderHistory
}