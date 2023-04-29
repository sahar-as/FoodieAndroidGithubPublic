package ir.saharapps.foodieapp.presentation.screens.delivery_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DeliveryViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val orderId = savedStateHandle.get<Int>("orderId")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.deleteAllFoodOrderLocalUseCase()
            orderId?.let {
                val foodOrderHistory = useCases.getFoodOrderHistoryByIdUseCase(orderId)
                println("111111111111111 $foodOrderHistory $orderId")
                useCases.addFoodOrderRemoteUseCase(foodOrderHistory)
            }
        }
    }
}