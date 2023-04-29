package ir.saharapps.foodieapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

//user = "Sahar1"
//pass = "@cmnFnD7kAzgTun"
interface UserPhoneAuthApi {

//    https://raygansms.com/AutoSendCode.ashx?Username=Sahar1&Password=@cmnFnD7kAzgTun&Mobile=09354107968&Footer=Test
    @GET("/AutoSendCode.ashx")
    suspend fun sendPhoneGetCode(
        @Query("Username") user: String,
        @Query("Password") pass: String,
        @Query("Mobile") phone: String,
        @Query("Footer") footer: String
    ): String

    @GET("/login/CheckSendCode.ashx")
    suspend fun checkCode(
        @Query("Username") user: String,
        @Query("Password") pass: String,
        @Query("Mobile") phone: String,
        @Query("Code") code: String
    ): Boolean
}