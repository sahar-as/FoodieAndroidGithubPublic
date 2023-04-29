package ir.saharapps.foodieapp.data.repository

import ir.saharapps.foodieapp.data.remote.FoodOrderAPI
import ir.saharapps.foodieapp.data.remote.dto.food.FoodResult
import ir.saharapps.foodieapp.data.remote.dto.user.UserSignLoginResponse
import ir.saharapps.foodieapp.domain.model.*
import ir.saharapps.foodieapp.domain.repository.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val preferenceManager: PreferenceManager,
    private val foodInterface: FoodInterface,
    private val userPhoneAuthInterface: UserPhoneAuthInterface,
    private val userInterface: UserInterface,
    private val localFoodOrderInterface: LocalFoodOrderInterface,
    private val addressInterface: AddressInterFace,
    private val foodOrderHistoryInterface: FoodOrderHistoryInterface,
    private val foodOrderAPI: FoodOrderAPI
) {
    suspend fun saveOnBoardStatus(completeStatus: Boolean){
        preferenceManager.saveOnBoardStatus(completeStatus)
    }

    fun readOnBoardStatus(): Flow<Boolean> {
        return preferenceManager.readOnBoardStatus()
    }

    suspend fun saveDeliveryTime(time: Int){
        preferenceManager.saveDeliveryTime(time)
    }
    fun readDeliveryTime(): Flow<Int>{
        return preferenceManager.readDeliveryTime()
    }

    suspend fun saveDeliveryPercentage(percentage: Float){
        preferenceManager.saveDeliveryPercentage(percentage)
    }

    fun readDeliveryPercentage(): Flow<Float>{
        return preferenceManager.readDeliveryPercentage()
    }

    suspend fun saveJwtToken(token: String){
        preferenceManager.saveJwtToken("")
    }

    suspend fun saveCalender(calender: Long){
        preferenceManager.saveCalender(calender)
    }

    suspend fun readCalender(): Flow<Long>{
        return preferenceManager.readCalender()
    }

    suspend fun getAllFood(): FoodResult {
        return foodInterface.getAllFood()
    }

    suspend fun getFoodById(foodId: Int): FoodResult{
        return foodInterface.getFoodById(foodId)
    }

    suspend fun getFoodByDishType(dishType: String) : FoodResult{
        return foodInterface.getFoodByDishType(dishType)
    }

    suspend fun getFavFood(rank: Double): FoodResult{
        return foodInterface.getFavFood(rank)
    }
    suspend fun sendPhoneGetCode(user: String, pass: String, phone: String, footer: String): String{
        return userPhoneAuthInterface.sendPhoneGetCode(user, pass, phone, footer)
    }

    suspend fun checkCode(user: String, pass: String, phone: String, code: String): Boolean{
        return userPhoneAuthInterface.checkCode(user, pass, phone, code)
    }

    suspend fun signUpUser(phone: String, user: String, pass: String): UserSignLoginResponse<Unit>{
        return userInterface.signUpUser(phone,user,pass)
    }

    suspend fun signInUser(phone: String, pass: String): UserSignLoginResponse<Unit>{
        return userInterface.signInUser(phone,pass)
    }

    suspend fun authenticate(): UserSignLoginResponse<Unit>{
        return userInterface.authenticate()
    }

    suspend fun getUserInfo(): User {
        return userInterface.getUserInfo()
    }

    suspend fun deleteUserByPhone(phone: String): Boolean{
        return userInterface.deleteUserByPhone(phone)
    }
    suspend fun getUserByPhone(phone: String): User?{
        return userInterface.getUserByPhone(phone)
    }

    suspend fun updateUserAddress(phone: String, address: String){
        userInterface.updateUserAddress(phone, address)
    }

    suspend fun localFoodOrderInsert(foodOrder: FoodOrder){
        localFoodOrderInterface.insertFoodOrder(foodOrder)
    }
    suspend fun localGetAllFoodOrder():List<FoodOrder>{
        return localFoodOrderInterface.getAllFoodOrders()
    }
    suspend fun localUpdateFoodOrder(foodId: Int, count: Int){
        localFoodOrderInterface.updateFoodOrder(foodId, count)
    }
    suspend fun localDeleteAllFoodOrder(){
        localFoodOrderInterface.deleteAllOrders()
    }
    suspend fun localDeleteZeroCountOrder(zero: Int){
        localFoodOrderInterface.deleteZeroCountOrders(zero)
    }

    suspend fun localDeleteFoodById(foodId: Int){
        localFoodOrderInterface.deleteFoodById(foodId)
    }

    suspend fun localSearchFoodOrder(foodId: Int): FoodOrder{
        return localFoodOrderInterface.searchFoodOrder(foodId)
    }

    suspend fun addAddress(address: UserAddress){
        return addressInterface.addAddress(address)
    }
    suspend fun getAllAddress(): List<UserAddress>{
        return addressInterface.getAllAddress()
    }
    suspend fun deleteAddress(addressId: Int){
        addressInterface.deleteAddressById(addressId)
    }
    suspend fun updateAddress(address: UserAddress){
        addressInterface.updateAddress(address)
    }
    suspend fun getAddressById(addressId: Int): UserAddress{
        return addressInterface.getAddressById(addressId)
    }

    suspend fun addFoodOrderHistory(foodOrderHistory: FoodOrderHistory){
        foodOrderHistoryInterface.addFoodOrderHistory(foodOrderHistory)
    }
    suspend fun getAllFoodOrderHistory(): List<FoodOrderHistory>{
        return foodOrderHistoryInterface.getAllFoodOrderHistory()
    }

    suspend fun deleteOrderHistoryById(orderId: Int){
        foodOrderHistoryInterface.deleteOrderHistoryById(orderId)
    }

    suspend fun getFoodOrderHistoryById(orderId: Int): FoodOrderHistory{
        return foodOrderHistoryInterface.getFoodOrderHistoryById(orderId)
    }

    suspend fun addFoodOrderRemote(foodOrderHistory: FoodOrderHistory){
        foodOrderAPI.addFoodOrder(foodOrderHistory)
    }
    suspend fun getAllFoodOrderRemoteByPhone(phone: String): List<FoodOrderHistory>{
        return foodOrderAPI.getAllFoodOrderByPhone(phone)
    }
}