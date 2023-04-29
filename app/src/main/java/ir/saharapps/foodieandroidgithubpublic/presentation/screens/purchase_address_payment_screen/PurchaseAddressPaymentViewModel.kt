package ir.saharapps.foodieapp.presentation.screens.purchase_address_payment_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import ir.saharapps.foodieapp.domain.model.UserAddress
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class PurchaseAddressPaymentViewModel @Inject constructor(
    private val useCases: UseCases,
): ViewModel() {

    private var _addressList = MutableStateFlow<List<UserAddress>>(emptyList())
    val addressList: StateFlow<List<UserAddress>> = _addressList

    val orderId = List(8) { ('0'..'9').random() }.joinToString("").toInt()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _addressList.value = useCases.getAddressUseCase()
        }
    }

    suspend fun saveOrderListInHistory(addressId: Int, paymentMethod: String){

        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH) + 1
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val date = "$month/$day/$year"
        val hour = calender.get(Calendar.HOUR)
        val minute = calender.get(Calendar.MINUTE)
        val time = (hour*60) + minute
        val deliveredTime = time+30

        val phone = useCases.getUserInfoUseCase.invoke().phoneNumber

        viewModelScope.launch(Dispatchers.IO) {
            orderId?.let {
                val orderList = useCases.getAllFoodOrderLocalUseCase()
                var foodNameList = ""
                var foodCountList = ""
                var foodCostList = ""
                var totalCost = 0.0

                for(order in orderList){
                    foodNameList += (useCases.getFoodByIdUseCase(order.foodId).getAllFood[0].name + "*")
                    foodCountList += order.count.toString() + "*"
                    foodCostList += order.cost + "*"
                    totalCost += order.cost.toDouble() * order.count
                }
                val foodOrderHistory = FoodOrderHistory(phone,orderId,foodNameList,  foodCountList, foodCostList, date, time, deliveredTime, addressId, paymentMethod, totalCost)
                useCases.addFoodOrderHistoryUseCase(foodOrderHistory)
                println("ZZZZZZZZZZZZZZZ ${useCases.getAllFoodOrderHistoryUseCase()}")
            }
        }
    }
}