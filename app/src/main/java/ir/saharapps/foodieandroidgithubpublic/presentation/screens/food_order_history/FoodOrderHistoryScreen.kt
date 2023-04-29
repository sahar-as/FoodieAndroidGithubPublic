package ir.saharapps.foodieapp.presentation.screens.food_order_history

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.History
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
import ir.saharapps.foodieapp.domain.model.FoodOrderHistory
import ir.saharapps.foodieapp.presentation.common.TopBar
import ir.saharapps.foodieapp.presentation.ui.theme.greenButton
import ir.saharapps.foodieapp.presentation.ui.theme.veryLightGray
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import java.util.Calendar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FoodOrderHistoryScreen (
    foodOrderHistoryViewModel: FoodOrderHistoryViewModel = hiltViewModel()
) {
    val foodOrderHistoryList by foodOrderHistoryViewModel.allOrderHistoryList.collectAsState()
    val fromWhere = foodOrderHistoryViewModel.fromWhere ?: ""

    Scaffold(topBar = {
        TopBar(
            iconStatus = true ,
            title = if(fromWhere == "history") "Order History" else "Current Order",
            icon = if(fromWhere == "history") Icons.Default.History else Icons.Default.Fastfood)}
    ) {

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(color = veryLightGray)
            .padding(top = 16.dp)){
            items(foodOrderHistoryList.size){index ->
                FoodHistoryItem(foodOrderHistory = foodOrderHistoryList[index], fromWhere)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodHistoryItem(foodOrderHistory: FoodOrderHistory, fromWhere: String) {
    var showDetail by remember { mutableStateOf(false) }
    var foodNameList by remember { mutableStateOf("") }
    var foodCostList by remember { mutableStateOf("") }
    var foodCountList by remember { mutableStateOf("") }

    var currentTimeToMinuets by remember {
        mutableStateOf(
            (Calendar.getInstance().get(Calendar.HOUR) * 60 + Calendar.getInstance()
                .get(Calendar.MINUTE))
        )
    }

    LaunchedEffect(key1 = true) {
        while (foodOrderHistory.deliveredTime - currentTimeToMinuets > 1) {
            delay(60000)
            currentTimeToMinuets += 1
        }
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            foodNameList = foodOrderHistory.foodName.replace("*", "\n")
            foodCostList = foodOrderHistory.foodCosts.replace("*", "$\n")
            foodCountList = foodOrderHistory.foodCount.replace("*", "\n")
            showDetail = !showDetail
        }
    ) {
        Column() {
            Row() {
                Column(
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(16.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Order Id: ${foodOrderHistory.orderId}",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 16.sp)
                    )
                    Text(
                        text = foodOrderHistory.date,
                        color = Color.DarkGray,
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(16.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${foodOrderHistory.totalCost}$",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 16.sp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text(
                    text = if (fromWhere == "current")
                        "Will be delivered less than ${foodOrderHistory.deliveredTime - currentTimeToMinuets} minuets"
                    else "Delivered",
                    color = greenButton,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
            if (showDetail) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row() {
                        Text(
                            modifier = Modifier.weight(0.6f),
                            text = "Name",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 16.sp)
                        )
                        Text(
                            modifier = Modifier.weight(0.2f),
                            text = "Cost",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 16.sp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier.weight(0.2f),
                            text = "Count",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 16.sp),
                            textAlign = TextAlign.Center
                        )
                    }
                    Row() {
                        Text(
                            modifier = Modifier.weight(0.6f),
                            text = foodNameList,
                            color = Color.DarkGray,
                            style = TextStyle(fontSize = 16.sp)
                        )
                        Text(
                            modifier = Modifier.weight(0.2f),
                            text = foodCostList,
                            color = Color.DarkGray,
                            style = TextStyle(fontSize = 16.sp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier.weight(0.2f),
                            text = foodCountList,
                            color = Color.DarkGray,
                            style = TextStyle(fontSize = 16.sp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun FoodOrderHistoryPreview() {
//    FoodOrderHistoryScreen()
//}