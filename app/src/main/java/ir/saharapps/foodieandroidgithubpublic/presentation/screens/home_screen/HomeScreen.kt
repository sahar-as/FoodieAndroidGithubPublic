package ir.saharapps.foodieapp.presentation.screens.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.domain.model.BottomBarScreen
import ir.saharapps.foodieapp.domain.model.Food
import ir.saharapps.foodieapp.domain.model.Menu
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.ui.theme.lightGray
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.secondColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController ,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val shimmerStatus by homeViewModel.shimmerStatus.collectAsState()
    val dishItemList by homeViewModel.foodList.collectAsState()
    val faveDish by homeViewModel.foodFavList.collectAsState()
//    val userInfo by homeViewModel.userInfo.collectAsState()

    var selectedIndex by remember { mutableStateOf(0) }

    val foodOrderList by homeViewModel.foodOrderList.collectAsState()
    var topBarIconStatus by remember{ mutableStateOf(false)}
    topBarIconStatus = foodOrderList.isNotEmpty()

    var openDialogState by remember { mutableStateOf(false) }
    var favDishSelectInfo by remember { mutableStateOf(Food(0,"","","","",0.0,true,"")) }
    val coroutineScope = rememberCoroutineScope()

    val menuItemList by remember {
        mutableStateOf(listOf<Menu>(
            Menu(name = "Pizza", image = R.drawable.menu1, dbName = "pizza"),
            Menu(name = "Burger", image = R.drawable.menu2, dbName = "burger"),
            Menu(name = "Hot dog", image = R.drawable.menu6, dbName = "hotdog"),
            Menu(name = "Chicken", image = R.drawable.menu4, dbName = "Chicken"),
            Menu(name = "Potato", image = R.drawable.menu3, dbName = "potato"),
            Menu(name = "Drink", image = R.drawable.menu5, dbName ="drink")
        ))
    }

    LaunchedEffect(key1 = true){
        homeViewModel.ktorErrorFlow.collect{state ->
            when(state){
                HomeViewModel.KtorErrorMessage.Internet ->{
                    navController.navigate(Screen.Error.passErrorMsg("Check your Internet connection"))
                }
                HomeViewModel.KtorErrorMessage.Server ->{
                    navController.navigate(Screen.Error.passErrorMsg("Server is disconnected"))
                }
                HomeViewModel.KtorErrorMessage.UnKnown ->{
                    navController.navigate(Screen.Error.passErrorMsg("Something went wrong"))
                }
            }
        }
    }

    Scaffold(
        topBar = { HomeTopBar(onCartClick = { navController.navigate(BottomBarScreen.Cart.route)},topBarIconStatus) }
    ) {
        LazyColumn(
        ) {
            item {

                if (!shimmerStatus) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.horizontalGradient(listOf(secondColor, secondColor)),
                                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Favorite",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                    Box(modifier = Modifier.background(color = secondColor)) {
                        LazyRow() {
                            items(faveDish.getAllFood.size) { faveItem ->
                                var foodOrderCount: Int = 0
                                if (foodOrderList.isNotEmpty()) {
                                    for (foodOrder in foodOrderList) {
                                        if (foodOrder.foodId == faveDish.getAllFood[faveItem].id) {
                                            foodOrderCount = foodOrder.count
                                        }
                                    }
                                }
                                FaveDishItem(faveDish.getAllFood[faveItem], foodOrderCount, homeViewModel){
                                    favDishSelectInfo = it
                                    openDialogState = true
                                }

                                if (openDialogState) {
                                    AlertDialog(
                                        onDismissRequest = { openDialogState = false },
                                        backgroundColor = secondColor,
                                        title = { Text(text = "${favDishSelectInfo.name} Ingredients:", fontWeight = FontWeight.Bold) },
                                        text = { Text(text = favDishSelectInfo.ingredient) },
                                        confirmButton = {
                                            Button(
                                                onClick = { openDialogState = false },
                                                colors = ButtonDefaults.buttonColors(backgroundColor = mainColor)
                                            ) {
                                                Text(text = "Ok", color = Color.White)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                } else {
                    Row() {
                        repeat(2) {
                            ShimmerFav()
                        }
                    }
                }
            }
            item {
                if (!shimmerStatus) {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.horizontalGradient(listOf(lightGray, lightGray)),
                                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Favorite",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(color = lightGray)
                            .padding(bottom = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
//                            IconButton(
//                                modifier = Modifier
//                                    .size(80.dp)
//                                    .weight(0.1f)
//                                    .padding(start = 8.dp),
//                                onClick = { /*TODO*/
//                                }) {
//                                Icon(
//                                    Icons.Default.Search,
//                                    contentDescription = "Search food",
//                                    tint = Color.Gray
//                                )
//                            }
                            LazyRow(
                                modifier = Modifier
                                    .weight(0.9f)
                                    .padding(2.dp)
                            ) {
                                items(menuItemList.size) { index ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Card(elevation = 5.dp,
                                            shape = RoundedCornerShape(10.dp),
                                            modifier = Modifier
                                                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                                                .requiredHeight(50.dp)
                                                .requiredWidth(50.dp)
                                                .selectable(
                                                    selected = selectedIndex == index,
                                                    onClick = {
                                                        selectedIndex = index
                                                    }
                                                ),
                                            border = BorderStroke
                                                (
                                                if (selectedIndex == index) 4.dp else 0.dp,
                                                secondColor
                                            ),
                                            onClick = {
                                                selectedIndex =
                                                    if (selectedIndex == index) -1 else index
                                                coroutineScope.launch {

                                                    homeViewModel.getFoodByDishType(menuItemList[index].dbName)
                                                }
                                            }

                                        ) {
                                            Image(
                                                painter = painterResource(id = menuItemList[index].image),
                                                contentDescription = "menu item"
                                            )

                                        }
                                        Text(
                                            modifier = Modifier.padding(2.dp),
                                            text = menuItemList[index].name,
                                            color = Color.DarkGray,
                                            style = TextStyle(fontSize = 14.sp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Divider(color = mainColor, thickness = 1.dp)
                } else {
                    ShimmerMenu()
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .background(color = lightGray)
                        .padding(8.dp)
                ) {
                    if (!shimmerStatus) {
                        Column() {
                            (dishItemList.getAllFood).forEach { foodItem ->
                                var foodOrderCount: Int = 0
                                if (foodOrderList.isNotEmpty()) {
                                    for (foodOrder in foodOrderList) {
                                        if (foodOrder.foodId == foodItem.id) {
                                            foodOrderCount = foodOrder.count
                                        }
                                    }
                                }
                                DishItem(foodItem, homeViewModel, foodOrderCount)
                                Divider(color = mainColor)
                            }
                        }
                    } else {
                        Column {
                            repeat(2) {
                                ShimmerFoodItem()
                            }
                        }
                    }
                }
            }
        }
    }
}

