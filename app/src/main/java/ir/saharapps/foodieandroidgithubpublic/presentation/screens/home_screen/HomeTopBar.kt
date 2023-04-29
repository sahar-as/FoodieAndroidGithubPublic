package ir.saharapps.foodieapp.presentation.screens.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor

import ir.saharapps.foodieapp.presentation.ui.theme.secondColor

@Composable
fun HomeTopBar(onCartClick: () -> Unit, oneIconStatus: Boolean){
    TopAppBar(
        title = {
            Text(
                text = "Foodie Restaurant",
                color = Color.White
            )
        },
        backgroundColor = mainColor,
        actions = {
            Box() {
                IconButton(modifier = Modifier.padding(top = 14.dp),onClick = onCartClick) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Search Icon", tint = Color.White)
                }
                if(oneIconStatus){
                    IconButton(modifier = Modifier.padding(start = 12.dp),onClick = onCartClick) {
                        Icon(
                            imageVector = Icons.Default.LooksOne,
                            contentDescription = "Search Icon", tint = secondColor
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar({}, true)
}