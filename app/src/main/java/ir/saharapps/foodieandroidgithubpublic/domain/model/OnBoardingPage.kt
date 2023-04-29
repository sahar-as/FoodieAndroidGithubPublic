package ir.saharapps.foodieapp.domain.model

import androidx.annotation.DrawableRes
import ir.saharapps.foodieapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val subjectImage: Int,
    @DrawableRes
    val chefImage: Int,
    val text: String
){
    object First: OnBoardingPage(
        subjectImage = R.drawable.img_resturant,
        chefImage = R.drawable.img_chef_first,
        text = "Welcome to Foodie restaurant"
    )
    object Second: OnBoardingPage(
        subjectImage = R.drawable.img_choose_food2,
        chefImage = R.drawable.img_chef_second,
        text = "You can choose your desire food from your favorite dish"
    )
    object Third: OnBoardingPage(
        subjectImage = R.drawable.img_visa_card,
        chefImage = R.drawable.img_chef_first,
        text = "Different Payment Methode:\n Online payment \n or \n Cash on delivery"
    )
    object Forth: OnBoardingPage(
        subjectImage = R.drawable.img_delivered_food,
        chefImage = R.drawable.img_chef_third,
        text = "Your order would be delivered in less than 30 minuets"
    )
}
