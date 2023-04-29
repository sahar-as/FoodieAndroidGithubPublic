package ir.saharapps.foodieapp.presentation.screens.add_address_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.domain.model.UserAddress
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAddressViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val addressId = savedStateHandle.get<Int>("addressId")
    val fromWhere = savedStateHandle.get<String>("fromWhere")

    private var _address = MutableStateFlow<UserAddress?>(null)
    val address: StateFlow<UserAddress?> = _address

    init {
        viewModelScope.launch {
            addressId?.let {
                _address.value = useCases.getAddressByIdUseCase(addressId)
            }
        }
    }
    suspend fun addAddress(address: UserAddress){
        useCases.addressUseCase(address)
    }

    suspend fun updateAddress(address: UserAddress){
        useCases.updateAddressUseCase(address)
    }

}