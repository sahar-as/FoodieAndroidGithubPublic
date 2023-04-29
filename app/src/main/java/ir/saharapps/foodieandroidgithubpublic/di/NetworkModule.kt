package ir.saharapps.foodieapp.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.saharapps.foodieapp.common.Constants.KTOR_SERVER_URL
import ir.saharapps.foodieapp.common.Constants.PHONE_AUTH_URL
import ir.saharapps.foodieapp.data.remote.FoodKtorBackendApi
import ir.saharapps.foodieapp.data.remote.FoodOrderAPI
import ir.saharapps.foodieapp.data.remote.UserApi
import ir.saharapps.foodieapp.data.remote.UserPhoneAuthApi
import ir.saharapps.foodieapp.data.repository.FoodInterfaceImp
import ir.saharapps.foodieapp.data.repository.FoodOrderRemoteInterfaceImp
import ir.saharapps.foodieapp.data.repository.UserInterfaceImp
import ir.saharapps.foodieapp.data.repository.UserPhoneAuthInterfaceImp
import ir.saharapps.foodieapp.domain.repository.FoodInterface
import ir.saharapps.foodieapp.domain.repository.FoodOrderRemoteInterface
import ir.saharapps.foodieapp.domain.repository.PreferenceManager
import ir.saharapps.foodieapp.domain.repository.UserInterface
import ir.saharapps.foodieapp.domain.repository.UserPhoneAuthInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): FoodKtorBackendApi{
        return Retrofit.Builder()
            .baseUrl(KTOR_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodKtorBackendApi::class.java)
    }
    @Singleton
    @Provides
    fun provideFoodRepository(api: FoodKtorBackendApi): FoodInterface{
        return FoodInterfaceImp(api)
    }

    @Singleton
    @Provides
    fun providePhoneAuthRetrofit(): UserPhoneAuthApi{
        return Retrofit.Builder()
            .baseUrl(PHONE_AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserPhoneAuthApi::class.java)
    }
    @Singleton
    @Provides
    fun provideUserPhoneAuthRepository(api: UserPhoneAuthApi): UserPhoneAuthInterface{
        return UserPhoneAuthInterfaceImp(api)
    }

    @Singleton
    @Provides
    fun provideUserRetrofit(): UserApi{
        return Retrofit.Builder()
            .baseUrl(KTOR_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(UserApi::class.java)
    }
    @Singleton
    @Provides
    fun provideUserRepository(api: UserApi, preferenceManager: PreferenceManager): UserInterface{
        return UserInterfaceImp(api, preferenceManager)
    }

    @Singleton
    @Provides
    fun provideFoodOrderRetrofit(): FoodOrderAPI{
        return Retrofit.Builder()
            .baseUrl(KTOR_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodOrderAPI::class.java)
    }
    @Singleton
    @Provides
    fun provideFoodOrderRepository(api: FoodOrderAPI): FoodOrderRemoteInterface{
        return FoodOrderRemoteInterfaceImp(api)
    }
}