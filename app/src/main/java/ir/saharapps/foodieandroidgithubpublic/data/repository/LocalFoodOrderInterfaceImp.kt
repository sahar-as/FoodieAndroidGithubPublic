package ir.saharapps.foodieapp.data.repository

import ir.saharapps.foodieapp.data.local.FoodOrderDataBase
import ir.saharapps.foodieapp.domain.model.FoodOrder
import ir.saharapps.foodieapp.domain.repository.LocalFoodOrderInterface
import javax.inject.Inject

class LocalFoodOrderInterfaceImp @Inject constructor(
    foodOrderDataBase: FoodOrderDataBase
): LocalFoodOrderInterface {

    private val foodOrderDao = foodOrderDataBase.foodOrderDao()

    override suspend fun insertFoodOrder(foodOrder: FoodOrder) {
        foodOrderDao.addFoodOrder(foodOrder)
    }

    override suspend fun getAllFoodOrders(): List<FoodOrder> {
        return foodOrderDao.getAllOrders()
    }

    override suspend fun updateFoodOrder(foodId: Int, count: Int) {
        foodOrderDao.updateOrder(foodId, count)
    }

    override suspend fun deleteAllOrders() {
        foodOrderDao.deleteAllOrder()
    }

    override suspend fun deleteZeroCountOrders(zero: Int) {
        foodOrderDao.deleteZeroCounts(zero)
    }

    override suspend fun deleteFoodById(foodId: Int) {
        foodOrderDao.deleteFoodById(foodId)
    }

    override suspend fun searchFoodOrder(foodId: Int): FoodOrder {
        return foodOrderDao.searchFoodOrder(foodId)
    }
}