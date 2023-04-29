package ir.saharapps.foodieapp.data.remote

import android.media.session.MediaSession.Token
import ir.saharapps.foodieapp.data.remote.dto.user.SignUpRequest
import ir.saharapps.foodieapp.data.remote.dto.user.LoginRequest
import ir.saharapps.foodieapp.data.remote.dto.user.TokenResponse
import ir.saharapps.foodieapp.domain.model.User
import retrofit2.http.*

interface UserApi {
    @POST("/signup")
    suspend fun signUp(@Body request: SignUpRequest)

    @POST("/signin")
    suspend fun signIn(@Body loginRequest: LoginRequest):TokenResponse

    @GET("/authenticate")
    suspend fun authenticate(@Header("Authorization") token: String)

    @GET("/secret")
    suspend fun secretInfo(@Header("Authorization") token: String): String

    @GET("/getUserInfo/{userId}")
    suspend fun getUserInfo(@Path("userId") userId: String): User

    @DELETE("deleteByPhone/{phone}")
    suspend fun deleteUserByPhone(@Path("phone") phone: String): Boolean

    @GET("/getUserByPhone/{phone}")
    suspend fun getUserByPhone(@Path("phone") phone: String): User

    @POST("updateUserAddress/{phone}/{address}")
    suspend fun updateUserAddress(@Path("phone") phone: String, @Path("address") address: String)
}