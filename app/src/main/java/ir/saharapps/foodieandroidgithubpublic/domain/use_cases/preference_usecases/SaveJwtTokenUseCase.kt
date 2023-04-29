package ir.saharapps.foodieapp.domain.use_cases.preference_usecases

import ir.saharapps.foodieapp.data.repository.Repository
import javax.inject.Inject

class SaveJwtTokenUseCase  @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(token: String) {
        repository.saveJwtToken(token)
    }
}