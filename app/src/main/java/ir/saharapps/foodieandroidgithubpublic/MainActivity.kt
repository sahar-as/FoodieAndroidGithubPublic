package ir.saharapps.foodieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.saharapps.foodieapp.navigation.SetUpNavGraph

import ir.saharapps.foodieapp.presentation.ui.theme.FoodieAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodieAppTheme {
                navController = rememberNavController()
                SetUpNavGraph(navController = navController)
            }
        }
    }
}
