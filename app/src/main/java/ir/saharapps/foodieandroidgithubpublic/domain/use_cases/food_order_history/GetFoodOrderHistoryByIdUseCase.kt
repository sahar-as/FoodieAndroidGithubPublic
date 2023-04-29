package ir.saharapps.foodieapp.domain.use_cases.food_order_history

import ir.saharapps.foodieapp.data.repository.Repository
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import javax.inject.Inject

class GetFoodOrderHistoryByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(orderId: Int): FoodOrderHistory{
        return repository.getFoodOrderHistoryById(orderId)
    }
}