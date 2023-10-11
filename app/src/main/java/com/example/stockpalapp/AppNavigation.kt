package com.example.stockpalapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.ui.model.Routes
import com.example.stockpalapp.ui.screens.HomeScreen
import com.example.stockpalapp.ui.screens.RecipeScreen
import com.example.stockpalapp.ui.screens.ShoppingScreen
import com.example.stockpalapp.ui.screens.PantryScreen

@Composable
fun AppNavigation() {
    val routes = Routes()
    val navController = rememberNavController()

    NavHost(navController, startDestination = routes.home) {
        composable(routes.home) { HomeScreen(navController) }
        composable(routes.shoppingList) { ShoppingScreen(navController) }
        composable(routes.pantry) { PantryScreen(navController) }
        composable(routes.recipes) { RecipeScreen(navController) }
    }
}