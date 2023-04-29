package ir.saharapps.foodieapp.presentation.screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.data.remote.dto.food.FoodResult
import ir.saharapps.foodieapp.domain.model.FoodOrder
import ir.saharapps.foodieapp.domain.model.User
import ir.saharapps.foodieapp.domain.model.UserAddress
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private var _foodList = MutableStateFlow<FoodResult>(FoodResult(emptyList()))
    val foodList: StateFlow<FoodResult> = _foodList

    private var _foodFavList = MutableStateFlow<FoodResult>(FoodResult(emptyList()))
    val foodFavList: StateFlow<FoodResult> = _foodFavList

    private var _foodOrderList = MutableStateFlow<List<FoodOrder>>(emptyList())
    val foodOrderList: StateFlow<List<FoodOrder>> = _foodOrderList

    private val user = User("","","","","","")
    private var _userInfo = MutableStateFlow<User>(user)
    val userInfo: StateFlow<User> = _userInfo

    private val ktorErrorChannel = Channel<KtorErrorMessage>()
    val ktorErrorFlow = ktorErrorChannel.receiveAsFlow()

    private var _shimmerStatus = MutableStateFlow<Boolean>(true)
    val shimmerStatus: StateFlow<Boolean> = _shimmerStatus
    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.deleteFoodOrderHistoryById(75190671)

            useCases.deleteZeroCountOrderLocalUseCase(0)
            _foodList.value = useCases.getFoodByDishTypeUseCase("pizza")
            _foodFavList.value = useCases.getFavFoodUseCase(5.0)

            if(_foodList.value.errorMsg != "" || _foodFavList.value.errorMsg != ""){
                if (_foodList.value.errorMsg == "ConnectException" || _foodFavList.value.errorMsg == "ConnectException"){
                    ktorErrorChannel.send(KtorErrorMessage.Internet)
                }else if(_foodList.value.errorMsg == "SocketTimeoutException" || _foodFavList.value.errorMsg == "SocketTimeoutException"){
                    ktorErrorChannel.send(KtorErrorMessage.Server)
                }else if(_foodList.value.errorMsg == "UnKnownException" || _foodFavList.value.errorMsg == "UnKnownException"){
                    ktorErrorChannel.send(KtorErrorMessage.UnKnown)
                }else{
                    _shimmerStatus.value = false
                }
            }else{
                _shimmerStatus.value = false
            }
            _userInfo.value = useCases.getUserInfoUseCase()
            _foodOrderList.value = useCases.getAllFoodOrderLocalUseCase()

            val userInfo = useCases.getUserInfoUseCase.invoke()
            val orderHistoryFromRemoteDB = useCases.getAllFoodOrderRemoteByPhoneUseCase(userInfo.phoneNumber)
            for(order in orderHistoryFromRemoteDB){
                useCases.addFoodOrderHistoryUseCase(order)
            }

        }
    }

    suspend fun addOrUpdateFoodOrder(foodOrder: FoodOrder){
        val isOrderExist = useCases.searchFoodOrderLocalUseCase(foodOrder.foodId) ?: ""
        if(isOrderExist == ""){
            useCases.insertFoodOrderLocalUseCase(foodOrder)
        }else{
            useCases.updateFoodOrderLocalUseCase(foodOrder.foodId, foodOrder.count)
        }
        useCases.deleteZeroCountOrderLocalUseCase(0)
        _foodOrderList.value = useCases.getAllFoodOrderLocalUseCase()
    }
    suspend fun getFoodByDishType(dishType: String){
        _foodList.value = useCases.getFoodByDishTypeUseCase(dishType)
    }

    sealed class KtorErrorMessage(){
        object Internet: KtorErrorMessage()
        object Server: KtorErrorMessage()
        object UnKnown: KtorErrorMessage()
    }
}