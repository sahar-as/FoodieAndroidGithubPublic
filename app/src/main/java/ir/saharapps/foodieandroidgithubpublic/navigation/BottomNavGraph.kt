package ir.saharapps.foodieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.saharapps.foodieapp.domain.model.BottomBarScreen
import ir.saharapps.foodieapp.presentation.common.ErrorScreen
import ir.saharapps.foodieapp.presentation.screens.add_address_screen.AddAddressScreen
import ir.saharapps.foodieapp.presentation.screens.delivery_screen.DeliveryScreen
import ir.saharapps.foodieapp.presentation.screens.food_order_history.FoodOrderHistoryScreen
import ir.saharapps.foodieapp.presentation.screens.home_screen.HomeScreen
import ir.saharapps.foodieapp.presentation.screens.login_screen.LoginScreen
import ir.saharapps.foodieapp.presentation.screens.main_screen.MainScreen
import ir.saharapps.foodieapp.presentation.screens.payment_screen.PaymentScreen
import ir.saharapps.foodieapp.presentation.screens.profile_screen.ProfileScreen
import ir.saharapps.foodieapp.presentation.screens.purchase_address_payment_screen.PurchaseAddressPaymentScreen
import ir.saharapps.foodieapp.presentation.screens.shopping_cart_screen.ShoppingCartScreen
import ir.saharapps.foodieapp.presentation.screens.user_information.UserInformationScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.HomePage.route ){
        composable(route = BottomBarScreen.HomePage.route){
            HomeScreen(navController = navController)
        }
        composable(Screen.Login.route){
            LoginScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Cart.route){
            ShoppingCartScreen(navController= navController)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(navController = navController)
        }
        composable(route = Screen.PurchasePayment.route) {
            PurchaseAddressPaymentScreen(navController = navController)
        }
        composable(
            route = Screen.AddAddress.route,
            arguments = listOf(
                navArgument("addressId"){type = NavType.IntType},
                navArgument("fromWhere"){type = NavType.StringType},
            )
        ){
            AddAddressScreen(navController = navController)
        }
        composable(route = Screen.UserInformation.route){
            UserInformationScreen(navController = navController)
        }
        composable(
            route= Screen.FoodOrderHistory.route,
            arguments = listOf(navArgument("fromWhere"){type = NavType.StringType})
        ){
            FoodOrderHistoryScreen()
        }
        composable(
            route = Screen.Error.route,
            arguments = listOf(navArgument("errorMsg"){type = NavType.StringType})
        ){
            ErrorScreen()
        }
        composable(
            route = Screen.Payment.route,
            arguments = listOf(navArgument("orderId"){type = NavType.IntType})
        ){
            PaymentScreen(navController = navController)
        }
        composable(
            route = Screen.Delivery.route,
            arguments = listOf(navArgument("orderId"){type = NavType.IntType})
        ){
            DeliveryScreen(navController = navController)
        }
        composable(Screen.Main.route){
            MainScreen(navController = navController)
        }
    }

}