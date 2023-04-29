package ir.saharapps.foodieapp.domain.use_cases

import ir.saharapps.foodieapp.domain.use_cases.food_order_history.AddFoodOrderHistoryUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_order_history.DeleteFoodOrderHistoryById
import ir.saharapps.foodieapp.domain.use_cases.food_order_history.GetAllFoodOrderHistoryUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_order_history.GetFoodOrderHistoryByIdUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_order_local.*
import ir.saharapps.foodieapp.domain.use_cases.food_order_remote.AddFoodOrderRemoteUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_order_remote.GetAllFoodOrderRemoteByPhoneUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_usecases.GetAllFoodUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_usecases.GetFavFoodUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_usecases.GetFoodByDishTypeUseCase
import ir.saharapps.foodieapp.domain.use_cases.food_usecases.GetFoodByIdUseCase
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.ReadOnBoardStatusUseCase
import ir.saharapps.foodieapp.domain.use_cases.preference_usecases.SaveOnBoardStatusUseCase
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

data class UseCases(
    val saveOnBoardStatusUseCase: SaveOnBoardStatusUseCase,
    val readOnBoardStatusUseCase: ReadOnBoardStatusUseCase,
    val saveDeliveryTimeUseCases: SaveDeliveryTimeUseCases,
    val readDeliveryTimeUseCase: ReadDeliveryTimeUseCase,
    val saveDeliveryPercentageUseCase: SaveDeliveryPercentageUseCase,
    val readDeliveryPercentageUseCase: ReadDeliveryPercentageUseCase,
    val saveJwtTokenUseCase: SaveJwtTokenUseCase,
    val readCalenderUseCase: ReadCalenderUseCase,
    val saveCalenderUseCase: SaveCalenderUseCase,

    val getAllFoodUseCase: GetAllFoodUseCase,
    val getFoodByIdUseCase: GetFoodByIdUseCase,
    val getFoodByDishTypeUseCase: GetFoodByDishTypeUseCase,
    val getFavFoodUseCase: GetFavFoodUseCase,

    val sendPhoneGetCodeUseCase: SendPhoneGetCodeUseCase,
    val checkCodeUseCase: CheckCodeUseCase,
    val userSignUpUseCase: UserSignUpUseCase,
    val userSignInUseCase: UserSignInUseCase,
    val userAuthenticateUseCase: UserAuthenticateUseCase,
    val getUserInfoUseCase: GetUserInfoUseCase,
    val deleteUserByPhoneUseCase: DeleteUserByPhoneUseCase,
    val getUserByPhoneUseCase: GetUserByPhoneUseCase,
    val updateUserAddressUseCase: UpdateUserAddressUseCase,

    val insertFoodOrderLocalUseCase: InsertFoodOrderLocalUseCase,
    val updateFoodOrderLocalUseCase: UpdateFoodOrderLocalUseCase,
    val getAllFoodOrderLocalUseCase: GetAllFoodOrderLocalUseCase,
    val deleteAllFoodOrderLocalUseCase: DeleteAllFoodOrderLocalUseCase,
    val deleteZeroCountOrderLocalUseCase: DeleteZeroCountOrderLocalUseCase,
    val deleteFoodByIdLocalUseCase: DeleteFoodByIdLocalUseCase,
    val searchFoodOrderLocalUseCase: SearchFoodOrderLocalUseCase,

    val addressUseCase: AddAddressUseCase,
    val getAddressUseCase: GetAddressUseCase,
    val deleteAddressUseCase: DeleteAddressUseCase,
    val updateAddressUseCase: UpdateAddressUseCase,
    val getAddressByIdUseCase: GetAddressByIdUseCase,

    val addFoodOrderHistoryUseCase: AddFoodOrderHistoryUseCase,
    val getAllFoodOrderHistoryUseCase: GetAllFoodOrderHistoryUseCase,
    val deleteFoodOrderHistoryById: DeleteFoodOrderHistoryById,
    val getFoodOrderHistoryByIdUseCase: GetFoodOrderHistoryByIdUseCase,

    val addFoodOrderRemoteUseCase: AddFoodOrderRemoteUseCase,
    val getAllFoodOrderRemoteByPhoneUseCase: GetAllFoodOrderRemoteByPhoneUseCase
)
