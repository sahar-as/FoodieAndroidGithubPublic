package ir.saharapps.foodieapp.domain.use_cases.phone_auth_usecases

import ir.saharapps.foodieapp.data.repository.Repository
import javax.inject.Inject

class SendPhoneGetCodeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(user: String, pass: String, phone: String, footer: String): String{
        return repository.sendPhoneGetCode(user, pass, phone, footer)
    }
}