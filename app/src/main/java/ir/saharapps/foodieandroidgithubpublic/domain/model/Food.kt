package ir.saharapps.foodieapp.domain.model

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Food(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val ingredient: String,
    val image: String,
    val cost: String,
    val rank: Double,
    val isAvailable: Boolean,
    val dishType: String
)

