package ir.saharapps.foodieapp.data.remote

import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FoodOrderAPI{

    @POST("/foodOrder")
    suspend fun addFoodOrder(@Body foodOrder: FoodOrderHistory)

    @GET("/getAllFoodOrder/{phone}")
    suspend fun getAllFoodOrderByPhone(@Path("phone") phone: String): List<FoodOrderHistory>
}