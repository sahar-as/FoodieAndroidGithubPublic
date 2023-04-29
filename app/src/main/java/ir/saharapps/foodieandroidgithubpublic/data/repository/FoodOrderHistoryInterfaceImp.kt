package ir.saharapps.foodieapp.data.repository

import ir.saharapps.foodieapp.data.local.FoodOrderDataBase
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import ir.saharapps.foodieapp.domain.repository.FoodOrderHistoryInterface
import javax.inject.Inject

class FoodOrderHistoryInterfaceImp @Inject constructor(
    foodOrderDataBase: FoodOrderDataBase
): FoodOrderHistoryInterface {

    private val foodOrderHistoryDao = foodOrderDataBase.foodOrderHistoryDao()

    override suspend fun addFoodOrderHistory(foodOrderHistory: FoodOrderHistory) {
        foodOrderHistoryDao.addFoodOrderHistory(foodOrderHistory)
    }

    override suspend fun getAllFoodOrderHistory(): List<FoodOrderHistory> {
        return foodOrderHistoryDao.getAllFoodOrderHistory()
    }

    override suspend fun deleteOrderHistoryById(orderId: Int) {
        foodOrderHistoryDao.deleteOrderHistoryById(orderId)
    }

    override suspend fun getFoodOrderHistoryById(orderId: Int): FoodOrderHistory {
        return foodOrderHistoryDao.getOrderHistoryById(orderId)
    }
}