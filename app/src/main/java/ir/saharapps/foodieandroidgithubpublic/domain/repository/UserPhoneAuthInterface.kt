package ir.saharapps.foodieapp.domain.repository

interface UserPhoneAuthInterface {
    suspend fun sendPhoneGetCode(username: String, password: String, phone: String, footer: String): String
    suspend fun checkCode(username: String, password: String, phone: String, code: String): Boolean
}