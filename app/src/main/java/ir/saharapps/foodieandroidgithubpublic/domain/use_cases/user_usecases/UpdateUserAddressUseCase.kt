package ir.saharapps.foodieapp.domain.use_cases.user_usecases

import ir.saharapps.foodieapp.data.repository.Repository
import javax.inject.Inject

class UpdateUserAddressUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(phone: String, address: String){
        repository.updateUserAddress(phone, address)
    }
}