package ir.saharapps.foodieapp.data.local

import androidx.room.*
import ir.saharapps.foodieapp.domain.model.UserAddress

@Dao
interface UserAddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAddress(address: UserAddress)

    @Query("SELECT * FROM user_address")
    suspend fun getAllAddress(): List<UserAddress>

    @Query("DELETE FROM user_address WHERE addressId = :addressId")
    suspend fun deleteById(addressId: Int)

    @Update
    suspend fun updateAddress(address: UserAddress)

    @Query("SELECT * FROM user_address WHERE addressId = :addressId")
    suspend fun getAddressById(addressId: Int): UserAddress
}