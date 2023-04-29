package ir.saharapps.foodieapp.presentation.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import coil.compose.AsyncImage
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.common.Constants.KTOR_SERVER_URL
import ir.saharapps.foodieapp.domain.model.Food
import ir.saharapps.foodieapp.domain.model.FoodOrder
import ir.saharapps.foodieapp.presentation.ui.theme.greenButton
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.redButton
import kotlinx.coroutines.launch

@Composable
fun DishItem(food: Food, viewModel: HomeViewModel, foodOrderCount: Int) {
    val coroutineScope = rememberCoroutineScope()
    var orderCount = foodOrderCount

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Card(elevation = 5.dp,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 6.dp)
                    .requiredHeight(100.dp)
                    .requiredWidth(100.dp)
            ){
                AsyncImage(
                    model = "$KTOR_SERVER_URL${food.image}",
                    placeholder = painterResource(R.drawable.ic_placeholder),
                    contentDescription = "Food Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = {
                        if(orderCount >= 1){ orderCount--
                            val foodOrder = FoodOrder(food.id, orderCount, food.cost)
                            coroutineScope.launch {
                                viewModel.addOrUpdateFoodOrder(foodOrder)
                                val result = viewModel.foodOrderList.value
                            }
                        }
                    }
                ) {
                    Icon(Icons.Filled.IndeterminateCheckBox, contentDescription = "Remove food", tint = redButton)
                }
                Text(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    text = orderCount.toString(),
                    color = Color.DarkGray,
                    style = TextStyle(fontSize = 16.sp)
                )
                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = {
                        if(orderCount<21){
                            orderCount++
                            val foodOrder = FoodOrder(food.id, orderCount, food.cost)
                            coroutineScope.launch {
                                viewModel.addOrUpdateFoodOrder(foodOrder)
                                val result = viewModel.foodOrderList.value
                            }
                        }
                    }
                ) {
                    Icon(Icons.Filled.AddBox, contentDescription = "Add food", tint = greenButton)
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
                text = food.name,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 20.sp)
            )
            Text(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                text = "Ingredients: ${food.ingredient}",
                color = Color.DarkGray,
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                text = "${food.cost} $",
                color = mainColor,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 18.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DishItemPreview() {
    val food = Food(1,"","", "R.drawable.a","",1.0,true,"")
//    DishItem(food)
}