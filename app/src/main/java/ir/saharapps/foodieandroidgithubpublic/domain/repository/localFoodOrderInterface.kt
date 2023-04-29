package ir.saharapps.foodieapp.domain.repository

import ir.saharapps.foodieapp.domain.model.FoodOrder

interface LocalFoodOrderInterface {
    suspend fun insertFoodOrder(foodOrder: FoodOrder)
    suspend fun getAllFoodOrders():List<FoodOrder>
    suspend fun updateFoodOrder(foodId: Int, count: Int)
    suspend fun deleteAllOrders()
    suspend fun deleteZeroCountOrders(zero: Int)
    suspend fun deleteFoodById(foodId: Int)
    suspend fun searchFoodOrder(foodId: Int): FoodOrder
}