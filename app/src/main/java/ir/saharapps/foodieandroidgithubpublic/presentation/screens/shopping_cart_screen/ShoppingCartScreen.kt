package ir.saharapps.foodieapp.presentation.screens.shopping_cart_screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.IndeterminateCheckBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import ir.saharapps.foodieapp.domain.model.FoodOrder
import ir.saharapps.foodieapp.presentation.common.TopBar
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.common.Constants
import ir.saharapps.foodieapp.domain.model.BottomBarScreen
import ir.saharapps.foodieapp.domain.model.Food
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.screens.home_screen.HomeViewModel
import ir.saharapps.foodieapp.presentation.screens.home_screen.ShimmerFoodItem
import ir.saharapps.foodieapp.presentation.ui.theme.greenButton
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.redButton
import ir.saharapps.foodieapp.presentation.ui.theme.secondColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShoppingCartScreen(
    navController: NavHostController,
    shoppingCartViewModel: ShoppingCartViewModel = hiltViewModel()
) {
    val shimmerState by shoppingCartViewModel.shimmerStatus.collectAsState()
    val foodOrderList by shoppingCartViewModel.foodOrderList.collectAsState()
    var foodList = remember { mutableListOf<Food>() }
    var totalCost = remember { mutableStateOf(0.0) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true){
        if (foodOrderList.isNotEmpty()){
            for (order in foodOrderList){
                foodList.add(shoppingCartViewModel.getFoodInfo(order.foodId).getAllFood[0])
                totalCost.value = (order.count * order.cost.toDouble()) + totalCost.value
            }
            shoppingCartViewModel.changeShimmerState(false)
        }else{
            shoppingCartViewModel.changeShimmerState(false)
        }

        shoppingCartViewModel.ktorErrorFlow.collect{state ->
            when(state){
                HomeViewModel.KtorErrorMessage.Internet ->{
                    navController.popBackStack()
                    navController.navigate(Screen.Error.passErrorMsg("Check your Internet connection"))
                }
                HomeViewModel.KtorErrorMessage.Server ->{
                    navController.popBackStack()
                    navController.navigate(Screen.Error.passErrorMsg("Server is disconnected"))
                }
                HomeViewModel.KtorErrorMessage.UnKnown ->{
                    navController.popBackStack()
                    navController.navigate(Screen.Error.passErrorMsg("Something went wrong"))
                }
            }
        }
    }

    Scaffold(
        topBar = {TopBar(false, "Shopping Cart")}
    ) {
        BackHandler(true) {
            navController.navigate(BottomBarScreen.HomePage.route)
        }
        if(shimmerState){
            repeat(2){
                ShimmerFoodItem()
            }
        }else{
            if (foodOrderList.isEmpty() || foodList.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "You haven't choose any food to order!",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 20.sp),
                    )
                    Image(
                        painter = painterResource(id = R.drawable.hungry_600),
                        contentDescription = "Hungry Image"
                    )
                }
            }else{
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    LazyColumn(modifier = Modifier.weight(0.8f)){
                        items(foodOrderList.size){index ->
                            ShoppingCartItem(foodOrderList[index], foodList[index], shoppingCartViewModel){status, cost ->
                                coroutineScope.launch {
                                    when(status){
                                        "add" -> { totalCost.value += cost }
                                        "minus" -> { totalCost.value -= cost }
                                        "remove" -> {
                                            totalCost.value -= cost
//                                            foodList.remove(foodList[index])
                                            shoppingCartViewModel.removeFoodFromFoodOrderList(foodOrderList[index].foodId)
                                        }
                                    }
                                }
                            }
                            Divider(color = mainColor)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(0.1f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(0.5f), horizontalAlignment = Alignment.Start) {
                            Text(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .padding(end = 24.dp),
                                text = "Total: ${totalCost.value}",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(fontSize = 18.sp)

                            )
                        }
                        Column(modifier = Modifier.weight(0.5f), horizontalAlignment = Alignment.End) {
                            Button(
                                modifier = Modifier
                                    .padding(end = 8.dp),
                                onClick = {
                                    navController.popBackStack()
                                    navController.navigate(Screen.PurchasePayment.route)
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = secondColor)
                            ) {
                                Text(text = "Purchase",fontWeight = FontWeight.Bold,style = TextStyle(fontSize = 18.sp), color = mainColor)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShoppingCartItem(foodOrder: FoodOrder, food: Food, viewModel:ShoppingCartViewModel, addRemoveButtonClicked: (status: String,foodCost: Double) -> Unit, ) {
    val coroutineScope = rememberCoroutineScope()
    var foodOrderCount = foodOrder.count

    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .weight(0.25f)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Card(elevation = 5.dp,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 6.dp)
                    .requiredHeight(80.dp)
                    .requiredWidth(80.dp)
            ){
                AsyncImage(
                    model = "${Constants.KTOR_SERVER_URL}${food.image}",
                    placeholder = painterResource(R.drawable.ic_placeholder),
                    contentDescription = "Food Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.6f)
                .padding(8.dp)
                .padding(start = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = food.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 18.sp)
            )
            Divider(color = mainColor, thickness = 1.dp)
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "Cost: ${foodOrder.cost}",
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp)
            )
        }
        Column(
            modifier = Modifier
                .weight(0.15f)
                .requiredHeight(100.dp)
                .padding(4.dp)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = {
                    if(foodOrderCount<21){
                        foodOrderCount++
                        val foodOrder = FoodOrder(food.id, foodOrderCount, food.cost)
                        coroutineScope.launch {
                            viewModel.addOrUpdateFoodOrder(foodOrder)
                        }
                        addRemoveButtonClicked.invoke("add",food.cost.toDouble())
                    }
                }
            ) {
                Icon(Icons.Filled.AddBox, contentDescription = "Add food", tint = greenButton)
            }

            Text(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                text = foodOrderCount.toString(),
                color = Color.DarkGray,
                style = TextStyle(fontSize = 16.sp)
            )

            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = {
                    if(foodOrderCount > 1){ foodOrderCount--
                        val foodOrder = FoodOrder(food.id, foodOrderCount, food.cost)
                        coroutineScope.launch {
                            viewModel.addOrUpdateFoodOrder(foodOrder)
                            val result = viewModel.foodOrderList.value
                            addRemoveButtonClicked.invoke("minus",food.cost.toDouble())
                        }
                    }else if (foodOrderCount == 1){
                        addRemoveButtonClicked.invoke("remove",food.cost.toDouble())
                    }
                }
            ) {
                Icon(Icons.Filled.IndeterminateCheckBox, contentDescription = "Remove food", tint = redButton)
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ShoppingCartItemPreview() {
    val foodOrder = FoodOrder(1,5,"15")
    val food = Food(1,"","", "R.drawable.a","",1.0,true,"")
//    ShoppingCartItem(foodOrder, food,{})
}