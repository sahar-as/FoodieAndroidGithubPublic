package ir.saharapps.foodieapp.presentation.screens.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.data.remote.dto.user.UserSignLoginResponse
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private var loginResultChannel = Channel<LoginChannelState>()
    val loginResultFlow = loginResultChannel.receiveAsFlow()

    fun signInUser(phone: String, pass: String){
        viewModelScope.launch {
            loginResultChannel.send(LoginChannelState.StartLoading)
            when(useCases.userSignInUseCase (phone, pass)){
                is UserSignLoginResponse.Authorized ->{
                    loginResultChannel.send(LoginChannelState.Success)
                    loginResultChannel.send(LoginChannelState.StopLoading)
                }
                is UserSignLoginResponse.Unauthorized ->{
                    loginResultChannel.send(LoginChannelState.Error)
                    loginResultChannel.send(LoginChannelState.StopLoading)
                }
                is UserSignLoginResponse.UnKnownError ->{
                    loginResultChannel.send(LoginChannelState.Error)
                    loginResultChannel.send(LoginChannelState.StopLoading)
                }
                is UserSignLoginResponse.InternetConnection ->{
                    loginResultChannel.send(LoginChannelState.Internet)
                    loginResultChannel.send(LoginChannelState.StopLoading)
                }
                is UserSignLoginResponse.ServerConnection ->{
                    loginResultChannel.send(LoginChannelState.Server)
                    loginResultChannel.send(LoginChannelState.StopLoading)
                }
            }
        }
    }

    sealed class LoginChannelState{
        object Success: LoginChannelState()
        object Error: LoginChannelState()
        object Internet: LoginChannelState()
        object Server: LoginChannelState()
        object StartLoading : LoginChannelState()
        object StopLoading : LoginChannelState()
    }
}