package ir.saharapps.foodieapp.data.preference

import android.content.Context
import androidx.compose.runtime.State
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.saharapps.foodieapp.domain.repository.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PreferenceManagerImp @Inject constructor(@ApplicationContext context: Context) : PreferenceManager {

    private val dataStore = context.createDataStore("user_preference")

    private object PreferenceKeys{
        val showWelcomePage = preferencesKey<Boolean>("welcome_page_status")
        val jwtToken = preferencesKey<String>("jwt_token")
        val deliveryCurrentTime = preferencesKey<Int>("delivery_time")
        val deliveryCurrentPercentage = preferencesKey<Float>("delivery_percentage")
        val deliveryCalender = preferencesKey<Long>("delivery_calender")
    }

    override suspend fun saveOnBoardStatus(status: Boolean) {
        dataStore.edit {preferences ->
            preferences[PreferenceKeys.showWelcomePage] = status
        }
    }

    override fun readOnBoardStatus(): Flow<Boolean> {
        return dataStore.data
            .catch {exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map { preferences ->
                val welcomePageStatus = preferences[PreferenceKeys.showWelcomePage] ?: false
                welcomePageStatus
            }
    }

    override suspend fun saveJwtToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.jwtToken] = token
        }
    }

    override fun readJwtToken(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map { preferences ->
                val jwtToken = preferences[PreferenceKeys.jwtToken] ?:""
                jwtToken
            }
    }

    override suspend fun saveDeliveryTime(time: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.deliveryCurrentTime] = time
        }
    }

    override fun readDeliveryTime(): Flow<Int> {
        return dataStore.data
            .catch {exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map {preferences ->
                val time = preferences[PreferenceKeys.deliveryCurrentTime]?: -1
                time
            }
    }

    override suspend fun saveDeliveryPercentage(percentage: Float) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.deliveryCurrentPercentage] = percentage
        }
    }

    override fun readDeliveryPercentage(): Flow<Float> {
        return dataStore.data
            .catch {exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map { preferences ->
                val percentage = preferences[PreferenceKeys.deliveryCurrentPercentage]?: -1.0f
                percentage
            }
    }

    override suspend fun saveCalender(calender: Long) {
        dataStore.edit {preferences ->
            preferences[PreferenceKeys.deliveryCalender] = calender
        }
    }

    override fun readCalender(): Flow<Long> {
        return dataStore.data
            .catch {exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map { preferences ->
                val calender = preferences[PreferenceKeys.deliveryCalender]?: -1L
                calender
            }
    }
}