package ir.saharapps.foodieapp.domain.use_cases.user_address

import ir.saharapps.foodieapp.data.repository.Repository
import ir.saharapps.foodieapp.domain.model.UserAddress
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<UserAddress>{
        return repository.getAllAddress()
    }
}