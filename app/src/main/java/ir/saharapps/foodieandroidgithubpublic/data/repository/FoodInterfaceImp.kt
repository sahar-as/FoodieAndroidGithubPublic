package ir.saharapps.foodieapp.data.repository

import ir.saharapps.foodieapp.data.remote.dto.food.FoodResult
import ir.saharapps.foodieapp.data.remote.FoodKtorBackendApi
import ir.saharapps.foodieapp.domain.repository.FoodInterface
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class FoodInterfaceImp @Inject constructor(
    private val foodKtorBackendApi: FoodKtorBackendApi
): FoodInterface {

    override suspend fun getAllFood(): FoodResult {
//        return foodKtorBackendApi.getAllFood()
        return try {
            foodKtorBackendApi.getAllFood()
        }catch (e: ConnectException) {
            FoodResult(emptyList(), "ConnectException")
        }catch (e: SocketTimeoutException){
            FoodResult(emptyList(), "SocketTimeoutException")
        }catch (e: Exception){
            FoodResult(emptyList(), "UnKnownException")
        }
    }

    override suspend fun getFoodById(foodId: Int): FoodResult {
        return try {
            FoodResult(listOf(foodKtorBackendApi.getFoodById(foodId)))
        }catch (e: ConnectException) {
            FoodResult(emptyList(), "ConnectException")
        }catch (e: SocketTimeoutException){
            FoodResult(emptyList(), "SocketTimeoutException")
        }catch (e: Exception){
            FoodResult(emptyList(), "UnKnownException")
        }
    }

    override suspend fun getFoodByDishType(dishType: String): FoodResult {
//        return foodKtorBackendApi.getFoodByDishType(dishType)
        return try {
            foodKtorBackendApi.getFoodByDishType(dishType)
        }catch (e: ConnectException) {
            FoodResult(emptyList(), "ConnectException")
        }catch (e: SocketTimeoutException){
            FoodResult(emptyList(), "SocketTimeoutException")
        }catch (e: Exception){
            FoodResult(emptyList(), "UnKnownException")
        }
    }


    override suspend fun getFavFood(rank: Double): FoodResult {
//        return foodKtorBackendApi.getFavFood(rank)
        return try {
            foodKtorBackendApi.getFavFood(rank)
        }catch (e: ConnectException) {
            FoodResult(emptyList(), "ConnectException")
        }catch (e: SocketTimeoutException){
            FoodResult(emptyList(), "SocketTimeoutException")
        }catch (e: Exception){
            FoodResult(emptyList(), "UnKnownException")
        }
    }
}