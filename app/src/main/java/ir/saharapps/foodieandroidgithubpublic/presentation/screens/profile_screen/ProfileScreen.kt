package ir.saharapps.foodieapp.presentation.screens.profile_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.saharapps.foodieapp.domain.model.ProfileItem
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.common.TopBar
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.veryLightGray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    val profileItemList = listOf<ProfileItem>(
        ProfileItem("User Information", Icons.Filled.Person, Screen.UserInformation.route),
        ProfileItem("Current Order", Icons.Filled.Fastfood, Screen.FoodOrderHistory.passData("current")),
        ProfileItem("Purchase History", Icons.Default.History, Screen.FoodOrderHistory.passData("history")),
        ProfileItem("Logout", Icons.Default.Logout,"")
    )

    Scaffold(topBar = { TopBar(iconStatus = false, title = "Profile") }) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(color = veryLightGray)
            .padding(8.dp)){
            items(profileItemList.size){index ->
                ProfileItemScreen(profileItem = profileItemList[index]){
                    if (profileItemList[index].itemName != "Logout"){
                        navController.navigate(profileItemList[index].route)
                    }
                    if(profileItemList[index].itemName == "Logout"){
                        coroutineScope.launch {
                            profileViewModel.deleteJwtTokenForSignOut()
                            navController.popBackStack()
                            navController.navigate(Screen.Login.route)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileItemScreen(profileItem: ProfileItem, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(20.dp),
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .weight(0.2f)
                    .requiredSize(38.dp),
                imageVector = profileItem.icon,
                contentDescription = "Profile Item Icon",
                tint = mainColor
            )
            Text(
                modifier = Modifier.weight(0.6f),
                text = profileItem.itemName,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 20.sp
                )
            )
            Icon(
                modifier = Modifier
                    .weight(0.2f)
                    .requiredSize(48.dp),
                imageVector = Icons.Default.ArrowRight,
                contentDescription = "Arrow Icon",
                tint = mainColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileItemPreview() {
//    ProfileScreen()
}