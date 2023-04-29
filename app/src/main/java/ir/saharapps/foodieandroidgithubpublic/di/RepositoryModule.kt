package ir.saharapps.foodieapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.saharapps.foodieapp.data.preference.PreferenceManagerImp
import ir.saharapps.foodieapp.data.repository.Repository
import ir.saharapps.foodieapp.domain.repository.PreferenceManager
import ir.saharapps.foodieapp.domain.use_cases.food_usecases.GetAllFoodUseCase
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.ReadOnBoardStatusUseCase
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.SaveOnBoardStatusUseCase
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import ir.saharapps.foodieapp.domain.use_cases.food_order_history.AddFoodOrderHistoryUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_order_history.DeleteFoodOrderHistoryById
import ir.saharapps.foodieapp.domain.use_cases.food_order_history.GetAllFoodOrderHistoryUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_order_history.GetFoodOrderHistoryByIdUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_order_local.*
import ir.saharapps.foodieapp.domain.use_cases.food_order_remote.AddFoodOrderRemoteUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_order_remote.GetAllFoodOrderRemoteByPhoneUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_usecases.GetFavFoodUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_usecases.GetFoodByDishTypeUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_usecases.GetFoodByIdUseCase
import ir.saharapps.foodieapp.domain.use_cases.phone_auth_usecases.CheckCodeUseCase
import ir.saharapps.foodieapp.domain.use_cases.phone_auth_usecases.SendPhoneGetCodeUseCase
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.ReadCalenderUseCase
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.ReadDeliveryPercentageUseCase
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.ReadDeliveryTimeUseCase
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.SaveCalenderUseCase
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.SaveDeliveryPercentageUseCase
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.SaveDeliveryTimeUseCases
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.SaveJwtTokenUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_address.AddAddressUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_address.DeleteAddressUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_address.GetAddressByIdUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_address.GetAddressUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_address.UpdateAddressUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_usecases.DeleteUserByPhoneUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_usecases.GetUserByPhoneUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_usecases.GetUserInfoUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_usecases.UpdateUserAddressUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_usecases.UserAuthenticateUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_usecases.UserSignInUseCase
import ir.saharapps.foodieapp.domain.use_cases.user_usecases.UserSignUpUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context): PreferenceManager{
        return PreferenceManagerImp(context)
    }

    @Singleton
    @Provides
    fun provideUseCase(repository: Repository): UseCases{
        return UseCases(
            saveOnBoardStatusUseCase = SaveOnBoardStatusUseCase(repository),
            readOnBoardStatusUseCase = ReadOnBoardStatusUseCase(repository),
            saveDeliveryTimeUseCases = SaveDeliveryTimeUseCases(repository),
            readDeliveryTimeUseCase = ReadDeliveryTimeUseCase(repository),
            saveDeliveryPercentageUseCase = SaveDeliveryPercentageUseCase(repository),
            readDeliveryPercentageUseCase = ReadDeliveryPercentageUseCase(repository),
            saveJwtTokenUseCase = SaveJwtTokenUseCase(repository),
            readCalenderUseCase = ReadCalenderUseCase(repository),
            saveCalenderUseCase = SaveCalenderUseCase(repository),

            getAllFoodUseCase = GetAllFoodUseCase(repository),
            getFoodByIdUseCase = GetFoodByIdUseCase(repository),
            getFoodByDishTypeUseCase = GetFoodByDishTypeUseCase(repository),
            getFavFoodUseCase = GetFavFoodUseCase(repository),
            getUserByPhoneUseCase = GetUserByPhoneUseCase(repository),

            sendPhoneGetCodeUseCase = SendPhoneGetCodeUseCase(repository),
            checkCodeUseCase = CheckCodeUseCase(repository),
            userSignUpUseCase = UserSignUpUseCase(repository),
            userSignInUseCase = UserSignInUseCase(repository),
            userAuthenticateUseCase = UserAuthenticateUseCase(repository),
            getUserInfoUseCase = GetUserInfoUseCase(repository),
            deleteUserByPhoneUseCase = DeleteUserByPhoneUseCase(repository),
            updateUserAddressUseCase = UpdateUserAddressUseCase(repository),

            insertFoodOrderLocalUseCase = InsertFoodOrderLocalUseCase(repository),
            updateFoodOrderLocalUseCase = UpdateFoodOrderLocalUseCase(repository),
            getAllFoodOrderLocalUseCase = GetAllFoodOrderLocalUseCase(repository),
            deleteAllFoodOrderLocalUseCase = DeleteAllFoodOrderLocalUseCase(repository),
            deleteZeroCountOrderLocalUseCase = DeleteZeroCountOrderLocalUseCase(repository),
            deleteFoodByIdLocalUseCase = DeleteFoodByIdLocalUseCase(repository),
            searchFoodOrderLocalUseCase = SearchFoodOrderLocalUseCase(repository),

            addressUseCase = AddAddressUseCase(repository),
            getAddressUseCase = GetAddressUseCase(repository),
            deleteAddressUseCase = DeleteAddressUseCase(repository),
            updateAddressUseCase = UpdateAddressUseCase(repository),
            getAddressByIdUseCase = GetAddressByIdUseCase(repository),

            addFoodOrderHistoryUseCase = AddFoodOrderHistoryUseCase(repository),
            getAllFoodOrderHistoryUseCase = GetAllFoodOrderHistoryUseCase(repository),
            deleteFoodOrderHistoryById = DeleteFoodOrderHistoryById(repository),
            getFoodOrderHistoryByIdUseCase = GetFoodOrderHistoryByIdUseCase(repository),

            addFoodOrderRemoteUseCase = AddFoodOrderRemoteUseCase(repository),
            getAllFoodOrderRemoteByPhoneUseCase = GetAllFoodOrderRemoteByPhoneUseCase(repository)
        )
    }
}