package ir.saharapps.foodieapp.domain.repository

import ir.saharapps.foodieapp.domain.model.FoodOrderHistory

interface FoodOrderRemoteInterface {
    suspend fun addFoodOrder(foodOrder: FoodOrderHistory)
    suspend fun getAllFoodOrderByPhone(phone: String): List<FoodOrderHistory>
}