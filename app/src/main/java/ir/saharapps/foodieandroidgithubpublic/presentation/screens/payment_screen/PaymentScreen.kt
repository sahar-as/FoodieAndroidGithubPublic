package ir.saharapps.foodieapp.presentation.screens.payment_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.ui.theme.blueButton
import ir.saharapps.foodieapp.presentation.ui.theme.lightGray
import ir.saharapps.foodieapp.presentation.ui.theme.transparentColor
import kotlinx.coroutines.launch

@Composable
fun PaymentScreen(
    navController: NavHostController,
    paymentViewModel: PaymentViewModel = hiltViewModel()
) {
    val cost = 10.0
    val orderId = paymentViewModel.orderId
    val coroutineScope = rememberCoroutineScope()

    BackHandler(true) {
        coroutineScope.launch{
            orderId?.let {
                paymentViewModel.deleteOrderHistory(orderId)
            }
        }
        navController.navigate(Screen.PurchasePayment.route)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.DarkGray)) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = "This payment is related to Foodie Restaurant with cost of $cost $",
            color = Color.Yellow,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 18.sp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card() {
                Image(
                    modifier = Modifier.size(width = 130.dp, height = 100.dp),
                    painter = painterResource(id = R.drawable.pay_online),
                    contentDescription = "Online Payment",
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(bottom = 16.dp, start = 24.dp, end = 24.dp, top =24.dp),
            text = "Add your payment information",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            style = TextStyle(fontSize = 18.sp)
        )

        Text(
            modifier = Modifier
                .padding(start = 24.dp),
            text = "card information",
            color = lightGray,
            textAlign = TextAlign.Start,
            style = TextStyle(fontSize = 14.sp)
        )

        Card(
            modifier = Modifier
                .requiredHeight(200.dp)
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 24.dp, end = 24.dp, top = 4.dp)
            ) {
            Column(modifier = Modifier
                .background(Color.DarkGray)
                .border(1.dp, lightGray, shape = RoundedCornerShape(20.dp))
            ) {
                Row(modifier = Modifier.weight(0.5f), verticalAlignment = Alignment.CenterVertically) {

                    var cardNumber by remember{ mutableStateOf("") }
                    TextField(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .weight(0.6f),
                        value = cardNumber,
                        onValueChange ={ cardNumber = it},
                        placeholder = { Text(text = "Card Number" ) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.CreditCard,
                                contentDescription = "Card Number"
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.DarkGray,
                            leadingIconColor = Color.White,
                            placeholderColor = Color.LightGray,
                            unfocusedIndicatorColor = transparentColor,
                            focusedIndicatorColor = transparentColor,
                            textColor = Color.White,
                            cursorColor = Color.White
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Image(
                        modifier = Modifier
                            .weight(0.4f)
                            .size(width = 300.dp, height = 250.dp),
                        painter = painterResource(id = R.drawable.payment_carts),
                        contentDescription = "Credit image")

                }
                Divider(color = lightGray)
                Row(modifier = Modifier.weight(0.45f)) {
                    var date by remember{ mutableStateOf("") }
                    TextField(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .weight(0.5f),
                        value = date,
                        onValueChange ={ date = it},
                        placeholder = { Text(text = "MM/YY" ) },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.DarkGray,
                            leadingIconColor = Color.White,
                            placeholderColor = Color.LightGray,
                            unfocusedIndicatorColor = transparentColor,
                            focusedIndicatorColor = transparentColor,
                            textColor = Color.White,
                            cursorColor = Color.White
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )
                    Divider(color = lightGray, modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp))
                    var cvc by remember{ mutableStateOf("") }
                    TextField(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .weight(0.45f),
                        value = cvc,
                        onValueChange ={ cvc = it},
                        placeholder = { Text(text = "CVC" ) },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.DarkGray,
                            leadingIconColor = Color.White,
                            placeholderColor = Color.LightGray,
                            unfocusedIndicatorColor = transparentColor,
                            focusedIndicatorColor = transparentColor,
                            textColor = Color.White,
                            cursorColor = Color.White
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)) {
            Text(
                modifier = Modifier
                    .padding(2.dp),
                text = "Billing Address",
                color = lightGray,
                textAlign = TextAlign.Start,
                style = TextStyle(fontSize = 14.sp)
            )
            var address by remember{ mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 8.dp),
                value = address,
                onValueChange ={ address = it},
                placeholder = { Text(text = "Address" ) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.DarkGray,
                    leadingIconColor = Color.White,
                    placeholderColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedIndicatorColor = Color.LightGray,
                    textColor = Color.White,
                    cursorColor = Color.White
                ),
            )

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    modifier = Modifier
                        .padding(24.dp)
                        .requiredWidth(200.dp),
                    onClick = {
                        orderId?.let {
                            navController.navigate(Screen.Delivery.passOrderId(orderId))
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = blueButton)
                ) {
                    Text(text = "Pay",fontWeight = FontWeight.Bold,style = TextStyle(fontSize = 18.sp), color = Color.White)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PaymentPreview() {
//    PaymentScreen()
//}