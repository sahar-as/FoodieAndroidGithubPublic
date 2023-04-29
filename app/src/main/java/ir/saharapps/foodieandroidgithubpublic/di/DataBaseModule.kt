package ir.saharapps.foodieapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.saharapps.foodieapp.common.Constants.DATA_BASE_NAME
import ir.saharapps.foodieapp.data.local.FoodOrderDataBase
import ir.saharapps.foodieapp.data.repository.AddressInterfaceImp
import ir.saharapps.foodieapp.data.repository.FoodOrderHistoryInterfaceImp
import ir.saharapps.foodieapp.data.repository.LocalFoodOrderInterfaceImp
import ir.saharapps.foodieapp.domain.repository.AddressInterFace
import ir.saharapps.foodieapp.domain.repository.FoodOrderHistoryInterface
import ir.saharapps.foodieapp.domain.repository.LocalFoodOrderInterface
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FoodOrderDataBase::class.java, DATA_BASE_NAME).build()

    @Singleton
    @Provides
    fun provideLocalFoodOrder(foodOrderDataBase: FoodOrderDataBase): LocalFoodOrderInterface{
        return LocalFoodOrderInterfaceImp(foodOrderDataBase)
    }

    @Singleton
    @Provides
    fun provideUserAddress(foodOrderDataBase: FoodOrderDataBase): AddressInterFace{
        return AddressInterfaceImp(foodOrderDataBase)
    }

    @Singleton
    @Provides
    fun provideFoodOrderHistory(foodOrderDataBase: FoodOrderDataBase): FoodOrderHistoryInterface{
        return FoodOrderHistoryInterfaceImp(foodOrderDataBase)
    }
}