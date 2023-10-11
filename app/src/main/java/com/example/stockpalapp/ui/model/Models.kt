package com.example.stockpalapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class Models(
   @StringRes val stringResourceId: Int,
   @DrawableRes val imageResourceId: Int
)

data class Routes(
   val home: String = "home",
   val shoppingList: String = "shoppinglist",
   val pantry: String = "pantry",
   val recipes: String = "recipes",
   val profile: String = "profile",
   val login: String = "login"
)

sealed class BottomNavItem(var title: String, var icon: ImageVector, var screen_route: String){
   object Home: BottomNavItem("Home", Icons.Filled.Home,"home")
   object Shoppinglist: BottomNavItem("Shopping", Icons.Filled.Edit,"shoppinglist")
   object Pantry: BottomNavItem("Pantry", Icons.Filled.ShoppingCart,"pantry")
   object Recipe: BottomNavItem("Recipe", Icons.Filled.List,"recipes")
}