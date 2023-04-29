package ir.saharapps.foodieapp.domain.use_cases.preference_usecases

import ir.saharapps.foodieapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveCalenderUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(calender: Long) {
        return repository.saveCalender(calender)
    }
}