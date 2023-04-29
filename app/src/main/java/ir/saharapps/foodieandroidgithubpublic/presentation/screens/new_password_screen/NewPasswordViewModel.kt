package ir.saharapps.foodieapp.presentation.screens.new_password_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.data.remote.dto.user.UserSignLoginResponse
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import ir.saharapps.foodieapp.presentation.screens.login_screen.LoginViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    val phoneNumber = savedStateHandle.get<String>("phoneNumber")

    private val updateUserPassResultChannel = Channel<NewPasswordChannelState>()
    val updateUserPassResultFlow = updateUserPassResultChannel.receiveAsFlow()
    suspend fun updateUserPassword(phone: String, pass: String){
        val user = useCases.getUserByPhoneUseCase(phone)
        if(user == null){
            updateUserPassResultChannel.send(NewPasswordChannelState.Error)
        }else{
            val result = useCases.deleteUserByPhoneUseCase(phone)
            updateUserPassResultChannel.send(NewPasswordChannelState.StartLoading)
            when(useCases.userSignUpUseCase(phone, user.userName, pass)){
                is UserSignLoginResponse.Authorized ->{
                    updateUserPassResultChannel.send(NewPasswordChannelState.Success)
                    updateUserPassResultChannel.send(NewPasswordChannelState.StopLoading)
                }
                is UserSignLoginResponse.Unauthorized ->{
                    updateUserPassResultChannel.send(NewPasswordChannelState.Error)
                    updateUserPassResultChannel.send(NewPasswordChannelState.StopLoading)
                }
                is UserSignLoginResponse.UnKnownError ->{
                    updateUserPassResultChannel.send(NewPasswordChannelState.Error)
                    updateUserPassResultChannel.send(NewPasswordChannelState.StopLoading)
                }
                is UserSignLoginResponse.InternetConnection ->{
                    updateUserPassResultChannel.send(NewPasswordChannelState.Internet)
                    updateUserPassResultChannel.send(NewPasswordChannelState.StopLoading)
                }
                is UserSignLoginResponse.ServerConnection ->{
                    updateUserPassResultChannel.send(NewPasswordChannelState.Server)
                    updateUserPassResultChannel.send(NewPasswordChannelState.StopLoading)
                }
            }
        }
    }

    sealed class NewPasswordChannelState{
        object Success: NewPasswordChannelState()
        object Error: NewPasswordChannelState()
        object Internet: NewPasswordChannelState()
        object Server: NewPasswordChannelState()
        object StartLoading : NewPasswordChannelState()
        object StopLoading : NewPasswordChannelState()
    }

}