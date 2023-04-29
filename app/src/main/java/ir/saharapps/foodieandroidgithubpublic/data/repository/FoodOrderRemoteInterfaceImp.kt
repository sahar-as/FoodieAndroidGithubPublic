package ir.saharapps.foodieapp.data.repository

import ir.saharapps.foodieapp.data.remote.FoodOrderAPI
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import ir.saharapps.foodieapp.domain.repository.FoodOrderRemoteInterface
import javax.inject.Inject

class FoodOrderRemoteInterfaceImp @Inject constructor(
    private val foodOrderApi: FoodOrderAPI
): FoodOrderRemoteInterface {

    override suspend fun addFoodOrder(foodOrder: FoodOrderHistory) {
        foodOrderApi.addFoodOrder(foodOrder)
    }

    override suspend fun getAllFoodOrderByPhone(phone: String): List<FoodOrderHistory> {
        return foodOrderApi.getAllFoodOrderByPhone(phone)
    }
}