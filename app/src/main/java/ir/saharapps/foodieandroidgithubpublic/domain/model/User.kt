package ir.saharapps.foodieapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val phoneNumber: String,
    val userName: String,
    val password: String,
    val userId: String,
    val userAddress: String = "null",
    val salt: String,
)
