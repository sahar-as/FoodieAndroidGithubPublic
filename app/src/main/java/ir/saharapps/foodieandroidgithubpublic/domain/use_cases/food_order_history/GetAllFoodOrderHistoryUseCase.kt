package ir.saharapps.foodieapp.domain.use_cases.food_order_history

import ir.saharapps.foodieapp.data.repository.Repository
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import javax.inject.Inject

class GetAllFoodOrderHistoryUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun  invoke(): List<FoodOrderHistory>{
        return repository.getAllFoodOrderHistory()
    }
}