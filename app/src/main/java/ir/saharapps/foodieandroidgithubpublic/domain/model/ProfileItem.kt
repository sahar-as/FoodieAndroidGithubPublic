package ir.saharapps.foodieapp.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ProfileItem(
    val itemName: String,
    val icon: ImageVector,
    val route: String
)
