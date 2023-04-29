package ir.saharapps.foodieapp.domain.use_cases.food_usecases

import ir.saharapps.foodieapp.data.remote.dto.food.FoodResult
import ir.saharapps.foodieapp.data.repository.Repository
import javax.inject.Inject

class GetFavFoodUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(rank: Double): FoodResult {
        return repository.getFavFood(rank)
    }
}