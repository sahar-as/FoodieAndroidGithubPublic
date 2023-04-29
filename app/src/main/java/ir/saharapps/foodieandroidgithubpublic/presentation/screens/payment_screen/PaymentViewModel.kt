package ir.saharapps.foodieapp.presentation.screens.payment_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val orderId = savedStateHandle.get<Int>("orderId")

    suspend fun deleteOrderHistory(orderId: Int){
        useCases.deleteFoodOrderHistoryById(orderId)
    }
}