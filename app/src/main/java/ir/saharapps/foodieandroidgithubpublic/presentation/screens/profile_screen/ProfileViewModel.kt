package ir.saharapps.foodieapp.presentation.screens.profile_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.foodieapp.domain.use_cases.UseCases
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    suspend fun deleteJwtTokenForSignOut(){
        useCases.saveJwtTokenUseCase("")
    }
}