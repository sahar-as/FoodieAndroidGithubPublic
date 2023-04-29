package ir.saharapps.foodieapp.domain.use_cases.user_usecases

import ir.saharapps.foodieapp.data.remote.dto.user.UserSignLoginResponse
import ir.saharapps.foodieapp.data.repository.Repository
import javax.inject.Inject

class UserSignUpUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(phone: String, user: String, pass: String): UserSignLoginResponse<Unit> {
        return repository.signUpUser(phone,user,pass)
    }
}