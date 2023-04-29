package ir.saharapps.foodieapp.domain.use_cases.food_order_history

import ir.saharapps.foodieapp.data.repository.Repository
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import javax.inject.Inject

class DeleteFoodOrderHistoryById @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun  invoke(orderId: Int){
        return repository.deleteOrderHistoryById(orderId)
    }
}