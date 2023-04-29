package ir.saharapps.foodieapp.data.remote.dto.food

import ir.saharapps.foodieapp.domain.model.Food

data class FoodResult(
    val getAllFood: List<Food>,
    val errorMsg: String = ""
)