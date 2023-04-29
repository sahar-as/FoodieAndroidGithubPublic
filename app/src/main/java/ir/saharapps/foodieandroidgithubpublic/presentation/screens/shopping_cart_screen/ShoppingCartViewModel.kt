package ir.saharapps.foodieapp.presentation.screens.shopping_cart_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.data.remote.dto.food.FoodResult
import ir.saharapps.foodieapp.domain.model.Food
import ir.saharapps.foodieapp.domain.model.FoodOrder
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import ir.saharapps.foodieapp.presentation.screens.home_screen.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private var _foodOrderList = MutableStateFlow<List<FoodOrder>>(emptyList())
    val foodOrderList: StateFlow<List<FoodOrder>> = _foodOrderList

    private var _foodList = MutableStateFlow<FoodResult>(FoodResult(emptyList()))
    val foodList: StateFlow<FoodResult> = _foodList

    private var foodInfoChannel = Channel<Food> ()
    val foodFlow = foodInfoChannel.receiveAsFlow()


    private val ktorErrorChannel = Channel<HomeViewModel.KtorErrorMessage>()
    val ktorErrorFlow = ktorErrorChannel.receiveAsFlow()

    private var _shimmerStatus = MutableStateFlow<Boolean>(true)
    val shimmerStatus: StateFlow<Boolean> = _shimmerStatus

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _foodOrderList.value = useCases.getAllFoodOrderLocalUseCase()

        }
    }

    suspend fun getFoodInfo(foodId: Int): FoodResult{
        val foodResult = useCases.getFoodByIdUseCase(foodId)
        return if(foodResult.errorMsg == "" || foodResult.errorMsg  == null){
            foodResult
        }else{
            when (_foodList.value.errorMsg) {
                "ConnectException" -> {
                    ktorErrorChannel.send(HomeViewModel.KtorErrorMessage.Internet)
                }
                "SocketTimeoutException" -> {
                    ktorErrorChannel.send(HomeViewModel.KtorErrorMessage.Server)
                }
                "UnKnownException" -> {
                    ktorErrorChannel.send(HomeViewModel.KtorErrorMessage.UnKnown)
                }
            }
            FoodResult(emptyList())
        }
    }

    suspend fun deleteAllFoodOrderList(){
        useCases.deleteAllFoodOrderLocalUseCase()
    }

    fun changeShimmerState(state: Boolean){
        _shimmerStatus.value = state
    }

    suspend fun removeFoodFromFoodOrderList(foodId: Int){
        useCases.deleteFoodByIdLocalUseCase(foodId)
        _foodOrderList.value = useCases.getAllFoodOrderLocalUseCase()
        println("!!!!!!!!!!!!!!!!!!! ${useCases.getAllFoodOrderLocalUseCase}")
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

    sealed class KtorErrorMessage(){
        object Internet: KtorErrorMessage()
        object Server: KtorErrorMessage()
        object UnKnown: KtorErrorMessage()
    }
}