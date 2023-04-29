package ir.saharapps.foodieapp.domain.use_cases.preference_usecases

import ir.saharapps.foodieapp.data.repository.Repository
import javax.inject.Inject

class SaveOnBoardStatusUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(complete: Boolean){
        repository.saveOnBoardStatus(complete)
    }
}