package ir.saharapps.foodieapp.domain.use_cases.food_order_local

import ir.saharapps.foodieapp.data.repository.Repository
import ir.saharapps.foodieapp.domain.model.FoodOrder
import javax.inject.Inject

class DeleteZeroCountOrderLocalUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(zero: Int){
        repository.localDeleteZeroCountOrder(zero)
    }
}