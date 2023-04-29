package ir.saharapps.foodieapp.presentation.screens.welcome_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.data.remote.dto.user.UserSignLoginResponse
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel(){

    private var _userLoginState  = MutableStateFlow<Boolean>(true)
    val userLoginState: StateFlow<Boolean> = _userLoginState

    init{
        viewModelScope.launch(Dispatchers.IO) {
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
                    println("################## error3 connection")
                }
                is UserSignLoginResponse.ServerConnection ->{
                    println("################## error4 server")
                }
            }
        }
    }

    fun saveOnBoardStatus(complete: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveOnBoardStatusUseCase(complete)
        }
    }
}