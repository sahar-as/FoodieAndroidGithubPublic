package ir.saharapps.foodieapp.domain.repository

import ir.saharapps.foodieapp.data.remote.dto.food.FoodResult
import ir.saharapps.foodieapp.domain.model.Food

interface FoodInterface {
    suspend fun getAllFood(): FoodResult
    suspend fun getFoodById(foodId: Int): FoodResult
    suspend fun getFoodByDishType(dishType: String): FoodResult
    suspend fun getFavFood(rank: Double): FoodResult
}