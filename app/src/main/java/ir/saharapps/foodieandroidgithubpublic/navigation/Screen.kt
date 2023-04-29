package ir.saharapps.foodieapp.navigation

sealed class Screen(val route: String){
    object Splash: Screen("splash_screen")
    object Welcome: Screen("onboard_screen")
    object Login: Screen("login_screen")
    object SignUpPhone: Screen("signup_phone_screen")
    object PhoneCodeAuth: Screen("phone_code_auth_screen/{phoneNumber}/{fromWhere}"){
        fun passData(phoneNumber: String, fromWhere: String): String{
            return "phone_code_auth_screen/$phoneNumber/$fromWhere"
        }
    }
    object SignUpInfo: Screen("signup_info_screen/{phoneNumber}"){
        fun passPhoneNumber(phoneNumber: String): String{
            return "signup_info_screen/$phoneNumber"
        }
    }
    object ResetPassword: Screen("reset_password_screen")
    object NewPassword: Screen("new_password_screen/{phoneNumber}"){
        fun passPhoneNumber(phoneNumber: String): String{
            return "new_password_screen/$phoneNumber"
        }
    }
    object Home: Screen("home_screen")
    object Main: Screen("main_screen")

    object PurchasePayment: Screen("purchase_screen")
    object AddAddress: Screen("add_address/{addressId}/{fromWhere}"){
        fun passData(addressId: Int, fromWhere: String): String{
            return "add_address/$addressId/$fromWhere"
        }
    }
    object UserInformation: Screen("user_info")
    object FoodOrderHistory: Screen("food_order_history/{fromWhere}"){
        fun passData(fromWhere: String): String{
            return "food_order_history/$fromWhere"
        }
    }
    object Error: Screen("error/{errorMsg}"){
        fun passErrorMsg(errorMsg: String): String{
            return "error/$errorMsg"
        }
    }
    object Payment: Screen("payment_screen/{orderId}"){
        fun passOrderId(orderId: Int): String{
            return "payment_screen/$orderId"
        }
    }
    object Delivery: Screen("delivery_screen/{orderId}"){
        fun passOrderId(orderId: Int): String{
            return "delivery_screen/$orderId"
        }
    }

}
