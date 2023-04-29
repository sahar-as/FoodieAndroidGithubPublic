package ir.saharapps.foodieapp.domain.repository

import ir.saharapps.foodieapp.domain.model.FoodOrderHistory

interface FoodOrderHistoryInterface {
    suspend fun addFoodOrderHistory(foodOrderHistory: FoodOrderHistory)
    suspend fun getAllFoodOrderHistory(): List<FoodOrderHistory>
    suspend fun deleteOrderHistoryById(orderId: Int)
    suspend fun getFoodOrderHistoryById(orderId: Int): FoodOrderHistory
}