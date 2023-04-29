package ir.saharapps.foodieapp.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.secondColor

@Composable
fun TopBar(
    iconStatus: Boolean,
    title: String,
    icon: ImageVector = Icons.Default.LooksOne,
    onCartClick: () -> Unit = {}
    ){
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White
            )
        },
        backgroundColor = mainColor,
        actions = {
            if (iconStatus) {
                IconButton(modifier = Modifier.padding(start = 12.dp), onClick = onCartClick) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Search Icon", tint = secondColor
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(true, "title")
}