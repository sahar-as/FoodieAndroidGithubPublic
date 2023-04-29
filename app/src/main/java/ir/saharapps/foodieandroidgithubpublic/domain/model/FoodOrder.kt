package ir.saharapps.foodieapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "food_orders")
data class FoodOrder (
    @PrimaryKey
    val foodId: Int,
    val count: Int,
    val cost: String,
)