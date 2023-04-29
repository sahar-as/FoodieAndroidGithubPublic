package ir.saharapps.foodieapp.domain.use_cases.food_usecases

import ir.saharapps.foodieapp.data.remote.dto.food.FoodResult
import ir.saharapps.foodieapp.data.repository.Repository
import ir.saharapps.foodieapp.domain.model.Food
import javax.inject.Inject

class GetFoodByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(foodId: Int): FoodResult {
        return repository.getFoodById(foodId)
    }
}