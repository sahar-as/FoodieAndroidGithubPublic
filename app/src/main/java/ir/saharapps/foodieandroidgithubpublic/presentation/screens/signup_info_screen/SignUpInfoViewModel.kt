package ir.saharapps.foodieapp.presentation.screens.signup_info_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.data.remote.dto.user.UserSignLoginResponse
import ir.saharapps.foodieapp.domain.use_cases.user_usecases.UserSignUpUseCase
import ir.saharapps.foodieapp.presentation.screens.login_screen.LoginViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpInfoViewModel @Inject constructor(
    private val signUpUseCase: UserSignUpUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private var signUpInfoResultChannel = Channel<SignUpChannelState>()
    val signUpInfoResultFlow = signUpInfoResultChannel.receiveAsFlow()

    val phoneNumber = savedStateHandle.get<String>("phoneNumber")
    fun signUpUser(phone: String, user: String, pass: String){
        viewModelScope.launch {
            signUpInfoResultChannel.send(SignUpChannelState.StartLoading)
            when(signUpUseCase(phone, user, pass)){
                is UserSignLoginResponse.Authorized ->{
                    signUpInfoResultChannel.send(SignUpChannelState.Success)
                    signUpInfoResultChannel.send(SignUpChannelState.StopLoading)
                }
                is UserSignLoginResponse.Unauthorized ->{
                    signUpInfoResultChannel.send(SignUpChannelState.Error)
                    signUpInfoResultChannel.send(SignUpChannelState.StopLoading)
                }
                is UserSignLoginResponse.UnKnownError ->{
                    signUpInfoResultChannel.send(SignUpChannelState.Error)
                    signUpInfoResultChannel.send(SignUpChannelState.StopLoading)
                }
                is UserSignLoginResponse.InternetConnection ->{
                    signUpInfoResultChannel.send(SignUpChannelState.Internet)
                    signUpInfoResultChannel.send(SignUpChannelState.StopLoading)
                }
                is UserSignLoginResponse.ServerConnection ->{
                    signUpInfoResultChannel.send(SignUpChannelState.Server)
                    signUpInfoResultChannel.send(SignUpChannelState.StopLoading)
                }
            }
        }
    }

    sealed class SignUpChannelState{
        object Success: SignUpChannelState()
        object Error: SignUpChannelState()
        object Internet: SignUpChannelState()
        object Server: SignUpChannelState()
        object StartLoading : SignUpChannelState()
        object StopLoading : SignUpChannelState()
    }
}