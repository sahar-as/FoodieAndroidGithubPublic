package ir.saharapps.foodieapp.presentation.screens.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.data.remote.dto.user.UserSignLoginResponse
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private var _readOnBoardStatus = MutableStateFlow<Boolean>(false)
    val readOnBoardStatus : StateFlow<Boolean> = _readOnBoardStatus

    private var _userLoginState  = MutableStateFlow<Boolean>(true)
    val userLoginState: StateFlow<Boolean> = _userLoginState
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _readOnBoardStatus.value =
                useCases.readOnBoardStatusUseCase().stateIn(viewModelScope).value

            when(useCases.userAuthenticateUseCase()){
                is UserSignLoginResponse.Authorized ->{
                    _userLoginState.value = false
                }
                is UserSignLoginResponse.Unauthorized ->{
                    _userLoginState.value = true
                }
                is UserSignLoginResponse.UnKnownError ->{
                    _userLoginState.value = true
                }
                is UserSignLoginResponse.InternetConnection ->{
                    _userLoginState.value = true
                }
                is UserSignLoginResponse.ServerConnection ->{
                    _userLoginState.value = true
                }
            }
        }
    }
}