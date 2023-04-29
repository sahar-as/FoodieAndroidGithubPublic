package ir.saharapps.foodieapp.domain.use_cases.preference_usecases

import ir.saharapps.foodieapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadCalenderUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<Long> {
        return repository.readCalender()
    }
}