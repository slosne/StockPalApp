package com.example.stockpalapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.ui.screens.HomeScreen
import com.example.stockpalapp.ui.screens.PantryScreen
import com.example.stockpalapp.ui.screens.RecipeScreen
import com.example.stockpalapp.ui.screens.ShoppingScreen

data class Routes(
    val home: String = "home",
    val shoppingList: String = "shoppinglist",
    val pantry: String = "pantry",
    val recipes: String = "recipes",
    val profile: String = "profile",
    val login: String = "login"
)

@Composable
fun AppNavigation() {
    val routes = Routes()
    val navController = rememberNavController()

    NavHost(navController, startDestination = routes.home) {
        composable(routes.home) { HomeScreen(navController) }
        composable(routes.shoppingList) { ShoppingScreen(navController) }
        composable(routes.pantry) { PantryScreen(navController) }
        composable(routes.recipes) { RecipeScreen(navController) }
        //composable(routes.profile) { ProfileScreen(navController) }
        //composable(routes.login) { LoginScreen(navController) }
    }
}