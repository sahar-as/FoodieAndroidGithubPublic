package ir.saharapps.foodieapp.data.repository

import ir.saharapps.foodieapp.data.remote.UserApi
import ir.saharapps.foodieapp.data.remote.dto.user.LoginRequest
import ir.saharapps.foodieapp.data.remote.dto.user.SignUpRequest
import ir.saharapps.foodieapp.data.remote.dto.user.UserSignLoginResponse
import ir.saharapps.foodieapp.domain.model.User
import ir.saharapps.foodieapp.domain.repository.PreferenceManager
import ir.saharapps.foodieapp.domain.repository.UserInterface
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.stateIn
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject
@OptIn(DelicateCoroutinesApi::class)
class UserInterfaceImp @Inject constructor(
    private val api: UserApi,
    private val jwtPreference: PreferenceManager
): UserInterface {

    override suspend fun signUpUser(
        phone: String,
        userName: String,
        password: String
    ): UserSignLoginResponse<Unit> {
        return try {
            api.signUp(
                request = SignUpRequest(phone, userName, password)
            )
            signInUser(phone, password)
        }catch (e: ConnectException){
            UserSignLoginResponse.InternetConnection()
        }catch (e: SocketTimeoutException){
            UserSignLoginResponse.ServerConnection()
        }catch (e: HttpException){
            if (e.code() == 401){
                UserSignLoginResponse.Unauthorized()
            }else{
                UserSignLoginResponse.UnKnownError()
            }
        }catch (e: Exception){
            UserSignLoginResponse.UnKnownError()
        }
    }

    override suspend fun signInUser(phone: String, password: String): UserSignLoginResponse<Unit> {
        return try {
            val response = api.signIn(
                loginRequest = LoginRequest(phone,password)
            )
            jwtPreference.saveJwtToken(response.token)
            UserSignLoginResponse.Authorized()
        }catch (e: ConnectException){
            UserSignLoginResponse.InternetConnection()
        }catch (e: SocketTimeoutException){
            UserSignLoginResponse.ServerConnection()
        } catch (e: HttpException){
            if (e.code() == 401){
                println("$$$$$$$$$$$$$$ 1 $e")
                UserSignLoginResponse.Unauthorized()
            }else{
                println("$$$$$$$$$$$$$$ 2 $e")
                UserSignLoginResponse.UnKnownError()
            }
        }catch (e: Exception){
            println("$$$$$$$$$$$$$$ 3 $e")
            UserSignLoginResponse.UnKnownError()
        }
    }


    override suspend fun authenticate(): UserSignLoginResponse<Unit> {
        return try {
            val token = jwtPreference.readJwtToken().stateIn(GlobalScope).value
            if(token == ""){
                return UserSignLoginResponse.Unauthorized()
            }
            api.authenticate("Bearer $token")
            UserSignLoginResponse.Authorized()
        }catch (e: ConnectException){
            UserSignLoginResponse.InternetConnection()
        }catch (e: SocketTimeoutException){
            UserSignLoginResponse.ServerConnection()
        }catch (e: HttpException){
            if (e.code() == 401){
                UserSignLoginResponse.Unauthorized()
            }else{
                UserSignLoginResponse.UnKnownError()
            }
        }catch (e: Exception){
            UserSignLoginResponse.UnKnownError()
        }
    }

    override suspend fun getUserId(): String {
        val token = jwtPreference.readJwtToken().stateIn(GlobalScope).value
        if(token == ""){
            return "Unauthorized"
        }
        return api.secretInfo("Bearer $token")
    }

    override suspend fun getUserInfo(): User {
        val user = User("","","","","","")
        return try {
            val userId = getUserId()
            api.getUserInfo(userId = userId)
        }catch (e: java.lang.Exception){
            user
        }
    }

    override suspend fun deleteUserByPhone(phone: String): Boolean {
        return try {
            api.deleteUserByPhone(phone)
        }catch (e: Exception){
            false
        }
    }

    override suspend fun getUserByPhone(phone: String): User? {
        return try{
            val user = api.getUserByPhone(phone)
            user
        }catch (e: java.lang.Exception){
            null
        }
    }

    override suspend fun updateUserAddress(phone: String, address: String) {
        return try{
            api.updateUserAddress(phone,address)
        }catch (e: Exception){
            println(e.message)
        }
    }
}