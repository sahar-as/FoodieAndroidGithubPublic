package ir.saharapps.foodieapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "food_order_history")
data class FoodOrderHistory(
    val phoneNumber: String,
    @PrimaryKey
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
