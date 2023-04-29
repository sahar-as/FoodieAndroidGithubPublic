package ir.saharapps.foodieapp.presentation.screens.user_information

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.saharapps.foodieapp.domain.model.UserAddress
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.common.TopBar
import ir.saharapps.foodieapp.presentation.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserInformationScreen(
    navController: NavHostController,
    userInformationViewModel: UserInformationViewModel = hiltViewModel()
) {

    val userInfo by userInformationViewModel.userInfo.collectAsState()
    val addressList by userInformationViewModel.userAddressList.collectAsState()

    Scaffold(topBar = { TopBar(iconStatus = true, title = "User Information", Icons.Default.Person) }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
            ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Account information:",
                    color = Color.DarkGray,
                    style = TextStyle(fontSize = 18.sp),
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                modifier = Modifier.padding(8.dp),
                text = "Phone Number: ${userInfo?.phoneNumber}",
                color = Color.DarkGray,
                style = TextStyle(fontSize = 16.sp),
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = "User Name: ${userInfo?.userName}",
                color = Color.DarkGray,
                style = TextStyle(fontSize = 16.sp),
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(top = 16.dp),
                    text = "Addresses",
                    color = Color.DarkGray,
                    style = TextStyle(fontSize = 18.sp),
                    fontWeight = FontWeight.Bold
                    )
            }

            Row(modifier = Modifier.requiredSize(48.dp),horizontalArrangement = Arrangement.Start) {
                IconButton(
                    modifier = Modifier
                        .weight(0.1f)
                        .padding(start = 8.dp),
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.AddAddress.passData(-1,"user_info"))
                    }
                ) {
                    Icon(
                        Icons.Filled.AddBox,
                        contentDescription = "Add Address",
                        tint = mainColor,
                        modifier = Modifier.requiredSize(48.dp)
                    )
                }
            }
            Box( modifier = Modifier
                .requiredHeight(360.dp)
                .background(color = veryLightGray)) {
                if (addressList.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = "No Address Found",
                            color = mainColor,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            style = TextStyle(fontSize = 16.sp)
                        )
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            modifier = Modifier.size(150.dp),
                            tint = mainColor
                        )
                    }
                } else {
                    LazyVerticalGrid(GridCells.Fixed(2), content = {
                        items(addressList.size) { index ->
                            AddressItem(address = addressList[index], navController = navController)
                        }
                    })
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .requiredWidth(150.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = mainColor)
                ) {
                    Text(text = "Save",fontWeight = FontWeight.Bold,style = TextStyle(fontSize = 18.sp), color = Color.White)
                }
                Button(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .requiredWidth(150.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = mainColor)
                ) {
                    Text(text = "Cancel",fontWeight = FontWeight.Bold,style = TextStyle(fontSize = 18.sp), color = Color.White)
                }
            }
        }
    }
}

@Composable
fun AddressItem(address: UserAddress, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .requiredHeight(150.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = lightGray,

    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = address.addressName,
                color = mainColor,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 20.sp)
            )
            Row() {
                Text(
                    text = "${address.countryName}, ",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 18.sp)
                )
                Text(
                    text = address.cityName,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
            Text(
                text = address.address,
                color = Color.DarkGray,
                style = TextStyle(fontSize = 18.sp), maxLines = 3
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(modifier = Modifier.size(36.dp),onClick = {
                    navController.navigate(Screen.AddAddress.passData(address.addressId, "user_info"))
                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit button",
                        tint = mainColor
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun UserInformationPreview() {
//    UserInformationScreen()
//}