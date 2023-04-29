package ir.saharapps.foodieapp.presentation.screens.user_information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.domain.model.User
import ir.saharapps.foodieapp.domain.model.UserAddress
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInformationViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private var _userInfo = MutableStateFlow<User?>(null)
    val userInfo: StateFlow<User?> = _userInfo

    private var _userAddressList = MutableStateFlow<List<UserAddress>>(emptyList())
    val userAddressList: StateFlow<List<UserAddress>> = _userAddressList

    init {
        viewModelScope.launch {
            _userInfo.value = useCases.getUserInfoUseCase()
            _userAddressList.value = useCases.getAddressUseCase()
        }
    }

}