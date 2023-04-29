package ir.saharapps.foodieapp.data.remote

import ir.saharapps.foodieapp.data.remote.dto.food.FoodResult
import ir.saharapps.foodieapp.domain.model.Food
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodKtorBackendApi {
    @GET("/getAllFood")
    suspend fun getAllFood(): FoodResult

    @GET("/getFood/{foodId}")
    suspend fun getFoodById(@Path("foodId") foodId: Int): Food

    @GET("/getFoodByDishType/{dishType}")
    suspend fun getFoodByDishType(@Path("dishType") dishType: String): FoodResult

    @GET("/getFavFood/{rank}")
    suspend fun getFavFood(@Path("rank") rank: Double): FoodResult

}