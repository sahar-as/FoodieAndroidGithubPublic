package ir.saharapps.foodieapp.domain.use_cases.user_address

import ir.saharapps.foodieapp.data.repository.Repository
import ir.saharapps.foodieapp.domain.model.UserAddress
import javax.inject.Inject

class DeleteAddressUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(addressId: Int){
        repository.deleteAddress(addressId)
    }
}