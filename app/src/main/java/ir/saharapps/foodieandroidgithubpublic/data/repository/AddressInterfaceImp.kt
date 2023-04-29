package ir.saharapps.foodieapp.data.repository

import ir.saharapps.foodieapp.data.local.FoodOrderDataBase
import ir.saharapps.foodieapp.domain.model.UserAddress
import ir.saharapps.foodieapp.domain.repository.AddressInterFace
import javax.inject.Inject

class AddressInterfaceImp @Inject constructor(
    dataBase: FoodOrderDataBase
): AddressInterFace {

    private val userAddressDao = dataBase.userAddressDao()

    override suspend fun addAddress(address: UserAddress) {
        userAddressDao.addAddress(address)
    }

    override suspend fun getAllAddress(): List<UserAddress> {
        return userAddressDao.getAllAddress()
    }

    override suspend fun deleteAddressById(addressId: Int) {
        userAddressDao.deleteById(addressId)
    }

    override suspend fun updateAddress(address: UserAddress) {
        userAddressDao.updateAddress(address)
    }

    override suspend fun getAddressById(addressId: Int): UserAddress {
        return userAddressDao.getAddressById(addressId)
    }
}