package ir.saharapps.foodieapp.domain.use_cases.food_usecases

import ir.saharapps.foodieapp.data.remote.dto.food.FoodResult
import ir.saharapps.foodieapp.data.repository.Repository
import javax.inject.Inject

class GetFoodByDishTypeUseCase  @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(dishType: String): FoodResult {
        return repository.getFoodByDishType(dishType)
    }
}