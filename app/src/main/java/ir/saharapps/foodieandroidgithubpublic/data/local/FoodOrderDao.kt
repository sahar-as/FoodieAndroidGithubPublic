package ir.saharapps.foodieapp.data.local

import androidx.room.*
import ir.saharapps.foodieapp.domain.model.FoodOrder

@Dao
interface FoodOrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFoodOrder(foodOrder: FoodOrder)

    @Query("SELECT * FROM food_orders")
    suspend fun getAllOrders(): List<FoodOrder>

    @Query("UPDATE food_orders SET count= :count WHERE foodId LIKE :foodId")
    suspend fun updateOrder(foodId: Int, count: Int)

    @Query("DELETE FROM food_orders")
    suspend fun deleteAllOrder()

    @Query("DELETE FROM food_orders WHERE count = :zero")
    suspend fun deleteZeroCounts(zero: Int)

    @Query("DELETE FROM food_orders WHERE foodId LIKE :foodId")
    suspend fun deleteFoodById(foodId: Int)

    @Query("SELECT * FROM food_orders WHERE foodId LIKE :foodId")
    suspend fun searchFoodOrder(foodId: Int): FoodOrder

}