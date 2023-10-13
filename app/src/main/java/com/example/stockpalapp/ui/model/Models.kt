package com.example.stockpalapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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

data class Titles(
   val home: String = "Hjem",
   val shoppingList: String = "Handleliste",
   val pantry: String = "Matskap",
   val recipes: String = "Oppskrifter",
   val profile: String = "Profil"
)

sealed class BottomNavItem(var title: String, var icon: ImageVector, var screen_route: String){
   object Home: BottomNavItem(Titles().home, Icons.Filled.Home,Routes().home)
   object Shoppinglist: BottomNavItem(Titles().shoppingList, Icons.Filled.Edit,Routes().shoppingList)
   object Pantry: BottomNavItem(Titles().pantry, Icons.Filled.ShoppingCart,Routes().pantry)
   object Recipe: BottomNavItem(Titles().recipes, Icons.Filled.List,Routes().recipes)
}

sealed class DrawerItem(var screen_route: String, var icon: ImageVector, var label: String){
   object Home: DrawerItem(Routes().home, Icons.Default.Home, Titles().home )
   object Shoppinglist: DrawerItem(Routes().shoppingList, Icons.Default.Edit,Titles().shoppingList)
   object Pantry: DrawerItem(Routes().pantry, Icons.Default.ShoppingCart,Titles().pantry)
   object Recipe: DrawerItem(Routes().recipes, Icons.Default.List,Titles().recipes)
   object Profile: DrawerItem(Routes().profile, Icons.Default.AccountCircle, Titles().profile)
}
