package ir.saharapps.foodieapp.data.remote.dto.food_order

import kotlinx.serialization.Serializable

@Serializable
data class FoodOrderRemote(
    val phoneNumber: String,
    val orderId: Int,
    val foodName: String,
    val foodCount: String,
    val foodCosts: String,
    val date: String,
    val time: Int,
    val deliveredTime: Int,
    val addressId: Int,
    val paymentMethod: String,
    val totalCost: Double
)
