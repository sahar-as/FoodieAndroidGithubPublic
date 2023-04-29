package ir.saharapps.foodieapp.domain.use_cases.preference_usecases

import ir.saharapps.foodieapp.data.repository.Repository
import javax.inject.Inject

class SaveDeliveryTimeUseCases @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(time: Int) {
        repository.saveDeliveryTime(time)
    }
}