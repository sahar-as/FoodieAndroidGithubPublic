package ir.saharapps.foodieapp.presentation.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.IndeterminateCheckBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.common.Constants
import ir.saharapps.foodieapp.domain.model.Food
import ir.saharapps.foodieapp.domain.model.FoodOrder
import ir.saharapps.foodieapp.presentation.components.RatingWidget
import ir.saharapps.foodieapp.presentation.ui.theme.greenButton
import ir.saharapps.foodieapp.presentation.ui.theme.redButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FaveDishItem(food: Food, foodOrderCount: Int, viewModel: HomeViewModel, onItemClicked: (food: Food) -> Unit) {
    var orderCount = foodOrderCount
    val coroutineScope = rememberCoroutineScope()

    Card(elevation = 5.dp,
        shape = RoundedCornerShape(30.dp),
        onClick = {onItemClicked.invoke(food)},
        modifier = Modifier
            .padding(8.dp)
            .requiredHeight(250.dp)
            .requiredWidth(200.dp)
    ){
        AsyncImage(
            model = "${Constants.KTOR_SERVER_URL}${food.image}",
            placeholder = painterResource(R.drawable.ic_placeholder),
            contentDescription = "Fav Food Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(
                        shape = RectangleShape,
                        brush = Brush.linearGradient(listOf(Color.Black, Color.Black)),
                        alpha = 0.5f
                    )
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = food.name,
                        color = Color.White,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Column(
                        Modifier.padding(start= 6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top) {
                        RatingWidget(modifier = Modifier.padding(2.dp), rating = food.rank)
                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.background(
                                shape = RoundedCornerShape(16.dp),
                                brush = Brush.linearGradient(listOf(Color.Black, Color.Black)),
                                alpha = 0.3f)
                        ) {
                            IconButton(
                                modifier = Modifier.size(48.dp),
                                onClick = {
                                    if(orderCount >= 1){ orderCount--
                                        val foodOrder = FoodOrder(food.id, orderCount, food.cost)
                                        coroutineScope.launch {
                                            viewModel.addOrUpdateFoodOrder(foodOrder)
                                        }
                                    }
                                }
                            ) {
                                Icon(Icons.Filled.IndeterminateCheckBox, contentDescription = "Remove food", tint = redButton)
                            }
                            Text(
                                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                text = orderCount.toString(),
                                color = Color.White,
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
                                        }
                                    }
                                }
                            ) {
                                Icon(Icons.Filled.AddBox, contentDescription = "Add food", tint = greenButton)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable()
fun FaveDishPreview() {
//    FaveDishItem(food = Food(1,"","", "R.drawable.a","",1.0,true,""))
}