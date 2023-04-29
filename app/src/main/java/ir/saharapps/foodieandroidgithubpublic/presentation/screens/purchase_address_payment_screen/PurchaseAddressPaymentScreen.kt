package ir.saharapps.foodieapp.presentation.screens.purchase_address_payment_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.saharapps.foodieapp.domain.model.BottomBarScreen
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.common.TopBar
import ir.saharapps.foodieapp.presentation.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PurchaseAddressPaymentScreen(
    navController: NavHostController,
    purchaseAddressPaymentViewModel: PurchaseAddressPaymentViewModel = hiltViewModel()
) {
    val orderId = purchaseAddressPaymentViewModel.orderId
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val radioButtonList = mutableListOf("Pay Online", "Pay In Place")
    var selectedRadioItem by remember{ mutableStateOf(radioButtonList[0])}
    var selectedIndex by remember { mutableStateOf(-1) }
    val addressList by purchaseAddressPaymentViewModel.addressList.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        topBar = { TopBar(iconStatus = false, title = "Address & Payment") },
    ) {
        BackHandler(true) {
            navController.navigate(BottomBarScreen.Cart.route)
        }
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row(modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.9f)
                        .background(color = secondColor, shape = RectangleShape)
                        .padding(4.dp)
                        .padding(end = 4.dp),
                    text = "Choose Address",
                    color = mainColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 16.sp)
                )
           }
            Row(modifier = Modifier.requiredSize(48.dp),horizontalArrangement = Arrangement.Start) {
                IconButton(
                    modifier = Modifier
                        .weight(0.1f)
                        .padding(start = 8.dp),
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.AddAddress.passData(-1, "purchase"))
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
                if(addressList.isEmpty()){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = "Please Add Address to continue",
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
                }else{
                    LazyVerticalGrid(GridCells.Fixed(2), content = {
                        items(addressList.size){ index->
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .requiredHeight(150.dp)
                                    .selectable(
                                        selected = selectedIndex == index,
                                        onClick = { selectedIndex = index }
                                    ),
                                shape = RoundedCornerShape(20.dp),
                                backgroundColor = lightGray,
                                border = BorderStroke(if (selectedIndex == index) 4.dp else 0.dp, secondColor),
                                onClick = {
                                    selectedIndex = if (selectedIndex == index) -1 else index
                                }
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = addressList[index].addressName,
                                        color = mainColor,
                                        fontWeight = FontWeight.Bold,
                                        style = TextStyle(fontSize = 20.sp)
                                    )
                                    Row() {
                                        Text(
                                            text = "${addressList[index].countryName}, ",
                                            color = Color.DarkGray,
                                            fontWeight = FontWeight.Bold,
                                            style = TextStyle(fontSize = 18.sp)
                                        )
                                        Text(
                                            text = addressList[index].cityName,
                                            color = Color.DarkGray,
                                            fontWeight = FontWeight.Bold,
                                            style = TextStyle(fontSize = 18.sp)
                                        )
                                    }
                                    Text(
                                        text = addressList[index].address,
                                        color = Color.DarkGray,
                                        style = TextStyle(fontSize = 18.sp), maxLines = 3
                                    )
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                        IconButton(
                                            modifier = Modifier.size(36.dp),
                                            onClick = { navController.navigate(Screen.AddAddress.passData(addressList[index].addressId, "purchase"))}
                                        ) {
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
                    } )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.padding(8.dp)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = secondColor, shape = RectangleShape)
                        .padding(4.dp),
                    text = "Choose Payment Method",
                    color = mainColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
            Column() {
                radioButtonList.forEach { item ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = item == selectedRadioItem,
                            onClick = { selectedRadioItem = item },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = mainColor,
                                unselectedColor = mainColor
                            )
                        )
                        Text(text = item, modifier = Modifier.clickable {  selectedRadioItem = item})
                    }
                }
            }
            Spacer(modifier = Modifier.size(28.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    onClick = {
                        if(selectedIndex != -1){
                            coroutineScope.launch {
                                purchaseAddressPaymentViewModel.saveOrderListInHistory(addressList[selectedIndex].addressId, selectedRadioItem)
                            }
                            if(selectedRadioItem == "Pay Online"){
                                navController.navigate(Screen.Payment.passOrderId(orderId))
                            }else{
                                navController.navigate(Screen.Delivery.passOrderId(orderId))
                            }
                        }
                        else{
                            Toast.makeText(context, "Choose an Address", Toast.LENGTH_LONG).show()
                        }
                      },
                    colors = ButtonDefaults.buttonColors(backgroundColor = secondColor)
                ) {
                    Text(text = "Purchase",fontWeight = FontWeight.Bold,style = TextStyle(fontSize = 18.sp), color = Color.Black)
                }
            }
        }
    }
}

@Preview
@Composable
fun PurchaseAddressPaymentScreenPreview() {
//    PurchaseAddressPaymentScreen()
}