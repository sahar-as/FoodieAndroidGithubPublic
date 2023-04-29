package ir.saharapps.foodieapp.presentation.screens.food_order_history

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class FoodOrderHistoryViewModel @Inject constructor(
    useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val fromWhere = savedStateHandle.get<String>("fromWhere")

    private var _allOrderHistoryList = MutableStateFlow<List<FoodOrderHistory>>(emptyList())
    val allOrderHistoryList: StateFlow<List<FoodOrderHistory>> = _allOrderHistoryList

    init {
        viewModelScope.launch {
            val calender = Calendar.getInstance()
            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH) + 1
            val day = calender.get(Calendar.DAY_OF_MONTH)
            val date = "$month/$day/$year"
            val hour = calender.get(Calendar.HOUR)
            val minute = calender.get(Calendar.MINUTE)
            val time = (hour*60) + minute
            var orderToShow = mutableListOf<FoodOrderHistory>()
            val foodOrderList = useCases.getAllFoodOrderHistoryUseCase()

            if(fromWhere == "current"){
                for(order in foodOrderList){
                    if(date == order.date && order.deliveredTime > time) {
                        orderToShow.add(order)
                    }
                }
                _allOrderHistoryList.value = orderToShow
            }else{
                for(order in foodOrderList){
                    if(date != order.date || order.deliveredTime < time) {
                        orderToShow.add(order)
                    }
                }
                _allOrderHistoryList.value = orderToShow
            }

        }
    }
}