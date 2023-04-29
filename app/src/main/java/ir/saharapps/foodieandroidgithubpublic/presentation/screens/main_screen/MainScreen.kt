package ir.saharapps.foodieapp.presentation.screens.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.saharapps.foodieapp.navigation.BottomNavGraph
import ir.saharapps.foodieapp.presentation.screens.home_screen.BottomBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {

    val bottomBarNavController = rememberNavController()



    Scaffold(
        bottomBar = {
            if (currentRoute(bottomBarNavController)){
                BottomBar(navController = bottomBarNavController) }
            }
    ) { innerPadding ->
        //innerPadding will prevent overlaps of bottom bar
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomNavGraph(navController = bottomBarNavController)
        }
    }

}

//this is for recognize if BottomBar is needed or not
@Composable
private fun currentRoute(bottomBarNavController: NavHostController): Boolean {
    var result = false
    val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
    if(navBackStackEntry?.destination?.route == "homePage") result = true
    if(navBackStackEntry?.destination?.route == "cart") result = true
    if(navBackStackEntry?.destination?.route == "profile") result = true
    return result
}