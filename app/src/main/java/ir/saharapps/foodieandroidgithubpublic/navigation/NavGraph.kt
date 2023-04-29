package ir.saharapps.foodieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.saharapps.foodieapp.presentation.common.ErrorScreen
import ir.saharapps.foodieapp.presentation.screens.home_screen.HomeScreen
import ir.saharapps.foodieapp.presentation.screens.login_screen.LoginScreen
import ir.saharapps.foodieapp.presentation.screens.main_screen.MainScreen
import ir.saharapps.foodieapp.presentation.screens.new_password_screen.NewPasswordScreen
import ir.saharapps.foodieapp.presentation.screens.phone_code_auth_screen.PhoneCodeAuthScreen
import ir.saharapps.foodieapp.presentation.screens.reset_password_screen.ResetPasswordScreen
import ir.saharapps.foodieapp.presentation.screens.signup_info_screen.SignUpInfoScreen
import ir.saharapps.foodieapp.presentation.screens.signup_phone_screen.SignUpPhoneScreen
import ir.saharapps.foodieapp.presentation.screens.splash_screen.SplashScreen
import ir.saharapps.foodieapp.presentation.screens.welcome_screen.WelcomeScreen

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(Screen.Login.route){
            LoginScreen(navController = navController)
        }
        composable(Screen.SignUpPhone.route){
            SignUpPhoneScreen(navController = navController)
        }
        composable(
            route = Screen.PhoneCodeAuth.route,
            arguments = listOf(
                navArgument("phoneNumber"){type = NavType.StringType},
                navArgument("fromWhere"){type = NavType.StringType}
            )
        ){
            PhoneCodeAuthScreen(navController = navController)
        }
        composable(
            Screen.SignUpInfo.route,
            arguments = listOf(navArgument("phoneNumber"){type = NavType.StringType})
        ){
            SignUpInfoScreen(navController = navController)
        }
        composable(Screen.ResetPassword.route){
            ResetPasswordScreen(navController = navController)
        }
        composable(
            Screen.NewPassword.route,
            arguments = listOf(navArgument("phoneNumber"){type = NavType.StringType})
        ){
            NewPasswordScreen(navController = navController)
        }
        composable(Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(Screen.Main.route){
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.Error.route,
            arguments = listOf(navArgument("errorMsg"){type = NavType.StringType})
        ){
            ErrorScreen()
        }
    }
}