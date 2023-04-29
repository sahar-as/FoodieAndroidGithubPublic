package ir.saharapps.foodieapp.domain.use_cases.preference_usecases

import ir.saharapps.foodieapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadDeliveryTimeUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Int> {
        return repository.readDeliveryTime()
    }
}