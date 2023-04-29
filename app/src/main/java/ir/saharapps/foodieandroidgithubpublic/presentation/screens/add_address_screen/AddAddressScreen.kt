package ir.saharapps.foodieapp.presentation.screens.add_address_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.domain.model.UserAddress
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.common.TopBar
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.redButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddAddressScreen(
    navController: NavHostController,
    addAddressViewModel: AddAddressViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    var addressName by remember{ mutableStateOf("") }
    var postalCode by remember{ mutableStateOf("") }
    var address by remember{ mutableStateOf("") }

    val fromWhere = addAddressViewModel.fromWhere
    val userAddress by addAddressViewModel.address.collectAsState()
    userAddress?.let {
        addressName = userAddress!!.addressName
        postalCode = userAddress!!.postalCode
        address = userAddress!!.address
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(iconStatus = false, title = if(userAddress== null)"Add New Address" else "Edit Address") }
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                modifier = Modifier.padding(16.dp),
                text = "* At the moment our restaurant can only deliver in Tehran.",
                color = redButton,
                style = TextStyle(fontSize = 18.sp),
                textAlign = TextAlign.Left
            )
            
            Row() {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = "Country:",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = "Iran",
                    color = Color.Black,
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = "City:",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Tehran",
                    color = Color.Black,
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Center
                )
            }

            OutlinedTextField(
                modifier = Modifier.padding(top = 8.dp),
                value = addressName,
                onValueChange ={ addressName = it},
                placeholder = { Text(text = "Name" ) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.HomeWork,
                        contentDescription = "Name"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    leadingIconColor = mainColor,
                    placeholderColor = Color.LightGray
                ),
            )

            OutlinedTextField(
                modifier = Modifier.padding(top = 8.dp),
                value = postalCode,
                onValueChange ={ postalCode = it},
                placeholder = { Text(text = "Postal Code" ) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.postbox),
                        contentDescription = "Postal Code",
                        modifier = Modifier.size(30.dp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    leadingIconColor = mainColor,
                    placeholderColor = Color.LightGray
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 8.dp),
                value = address,
                onValueChange ={ address = it},
                placeholder = { Text(text = "Address") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Address"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    leadingIconColor = mainColor,
                    placeholderColor = Color.LightGray
                ), singleLine = false
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .requiredWidth(150.dp),
                    onClick = {
                        keyboardController?.hide()
                        val areFieldsEmpty = addressName.isEmpty() || postalCode.isEmpty() || address.isEmpty()
                        if (areFieldsEmpty){
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Complete all fields",
                                    actionLabel = "Ok"
                                )
                            }
                        }else{
                            coroutineScope.launch {
                                val userAddressCreation = UserAddress(addressId = userAddress?.addressId ?: 0
                                    ,addressName = addressName, postalCode = postalCode, countryName = "Iran", cityName = "Tehran", address = address)
                                if(userAddress == null){
                                    addAddressViewModel.addAddress(userAddressCreation)
                                }else{
                                    addAddressViewModel.updateAddress(userAddressCreation)
                                }
                            }
                            navController.popBackStack()
                            if(fromWhere =="purchase"){
                                navController.navigate(Screen.PurchasePayment.route)
                            }else{
                                navController.navigate(Screen.UserInformation.route)
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = mainColor)
                ) {
                    Text(text = if(userAddress==null) "Save" else "Update",fontWeight = FontWeight.Bold,style = TextStyle(fontSize = 18.sp), color = Color.White)
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun AddAddressScreenPreview() {
//    AddAddressScreen()
//}