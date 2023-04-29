package ir.saharapps.foodieapp.domain.repository

import ir.saharapps.foodieapp.data.remote.dto.user.UserSignLoginResponse
import ir.saharapps.foodieapp.domain.model.User


interface UserInterface {
    suspend fun signUpUser(phone: String, userName: String, password: String): UserSignLoginResponse<Unit>
    suspend fun signInUser(phone: String, password: String): UserSignLoginResponse<Unit>
    suspend fun authenticate(): UserSignLoginResponse<Unit>
    suspend fun getUserId(): String
    suspend fun getUserInfo(): User
    suspend fun deleteUserByPhone(phone: String): Boolean
    suspend fun getUserByPhone(phone: String): User?
    suspend fun updateUserAddress(phone: String, address: String)
}