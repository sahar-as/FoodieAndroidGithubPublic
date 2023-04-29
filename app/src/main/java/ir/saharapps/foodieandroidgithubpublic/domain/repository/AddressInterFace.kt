package ir.saharapps.foodieapp.domain.repository

import ir.saharapps.foodieapp.domain.model.UserAddress

interface AddressInterFace {
    suspend fun addAddress(address: UserAddress)
    suspend fun getAllAddress(): List<UserAddress>
    suspend fun deleteAddressById(addressId: Int)
    suspend fun updateAddress(address: UserAddress)
    suspend fun getAddressById(addressId: Int): UserAddress
}