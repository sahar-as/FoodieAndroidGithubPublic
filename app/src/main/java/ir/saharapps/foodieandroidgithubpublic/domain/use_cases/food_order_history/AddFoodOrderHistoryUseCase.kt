package ir.saharapps.foodieapp.domain.use_cases.food_order_history

import ir.saharapps.foodieapp.data.repository.Repository
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import javax.inject.Inject

class AddFoodOrderHistoryUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun  invoke(foodOrderHistory: FoodOrderHistory){
        repository.addFoodOrderHistory(foodOrderHistory)
    }
}