package ir.saharapps.foodieapp.domain.use_cases.food_order_local

import ir.saharapps.foodieapp.data.repository.Repository
import javax.inject.Inject

class DeleteFoodByIdLocalUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(foodId: Int){
        repository.localDeleteFoodById(foodId)
    }
}