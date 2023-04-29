package ir.saharapps.foodieapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_address")
data class UserAddress (
    @PrimaryKey(autoGenerate = true)
    val addressId: Int = 0,
    val addressName: String,
    val postalCode: String,
    val countryName: String,
    val cityName: String,
    val address: String
)

