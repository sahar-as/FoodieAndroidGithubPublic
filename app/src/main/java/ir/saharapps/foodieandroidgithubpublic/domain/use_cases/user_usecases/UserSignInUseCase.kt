package ir.saharapps.foodieapp.domain.use_cases.user_usecases

import ir.saharapps.foodieapp.data.remote.dto.user.UserSignLoginResponse
import ir.saharapps.foodieapp.data.repository.Repository
import javax.inject.Inject

class UserSignInUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(phone: String, pass: String): UserSignLoginResponse<Unit> {
        return repository.signInUser(phone, pass)
    }
}