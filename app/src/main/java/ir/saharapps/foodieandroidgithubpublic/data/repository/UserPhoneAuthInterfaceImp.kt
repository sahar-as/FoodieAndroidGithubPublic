package ir.saharapps.foodieapp.data.repository

import ir.saharapps.foodieapp.data.remote.UserPhoneAuthApi
import ir.saharapps.foodieapp.domain.repository.UserPhoneAuthInterface
import javax.inject.Inject

class UserPhoneAuthInterfaceImp @Inject constructor(
    private val userPhoneAuthApi: UserPhoneAuthApi
): UserPhoneAuthInterface {

    override suspend fun sendPhoneGetCode(username: String, password: String, phone: String, footer: String): String {
        return userPhoneAuthApi.sendPhoneGetCode(username, password, phone, footer)
    }

    override suspend fun checkCode(
        username: String,
        password: String,
        phone: String,
        code: String
    ): Boolean {
        return userPhoneAuthApi.checkCode(username, password, phone, code)
    }
}