package ir.saharapps.foodieapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferenceManager {
    suspend fun saveOnBoardStatus(status: Boolean)
    fun readOnBoardStatus(): Flow<Boolean>

    suspend fun saveJwtToken(token: String)
    fun readJwtToken(): Flow<String>

    suspend fun saveDeliveryTime(time: Int)
    fun readDeliveryTime(): Flow<Int>

    suspend fun saveDeliveryPercentage(percentage: Float)
    fun readDeliveryPercentage(): Flow<Float>

    suspend fun saveCalender(calender: Long)
    fun readCalender(): Flow<Long>
}