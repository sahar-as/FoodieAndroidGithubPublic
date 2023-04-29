package ir.saharapps.foodieapp.data.remote.dto.user

data class SignUpRequest(
    val phoneNumber: String,
    val userName: String,
    val password: String
)
