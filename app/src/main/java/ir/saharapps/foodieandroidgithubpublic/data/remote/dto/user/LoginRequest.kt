package ir.saharapps.foodieapp.data.remote.dto.user

data class LoginRequest(
    val phoneNumber: String,
    val password: String
)
