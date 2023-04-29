package ir.saharapps.foodieapp.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object HomePage: BottomBarScreen(
        route = "homePage",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Cart: BottomBarScreen(
        route = "cart",
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}
