package ir.saharapps.foodieapp.domain.use_cases.user_usecases

import ir.saharapps.foodieapp.data.repository.Repository
import ir.saharapps.foodieapp.domain.model.User
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): User{
        return repository.getUserInfo()
    }
}