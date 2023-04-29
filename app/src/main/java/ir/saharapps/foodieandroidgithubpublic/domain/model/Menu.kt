package ir.saharapps.foodieapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val name: String,
    val image: Int,
    var isSelected: Int =0,
    val dbName: String
)
