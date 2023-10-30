package com.example.stockpalapp

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.ui.model.Routes
import com.example.stockpalapp.ui.screens.AddPantryItemScreen
import com.example.stockpalapp.ui.screens.HomeScreen
import com.example.stockpalapp.ui.screens.LoginScreen
import com.example.stockpalapp.ui.screens.RecipeScreen
import com.example.stockpalapp.ui.screens.ShoppingScreen
import com.example.stockpalapp.ui.screens.PantryScreen
import com.example.stockpalapp.ui.screens.ProfileScreen
import com.example.stockpalapp.ui.screens.SignupScreen
import com.example.stockpalapp.ui.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(viewModel: AuthViewModel) {
    val routes = Routes()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavHost(navController, startDestination = routes.login) {
        composable(routes.home) { HomeScreen(navController, drawerState, scope) }
        composable(routes.signup) { SignupScreen(viewModel, navController) }
        composable(routes.login) { LoginScreen(viewModel, navController) }
        composable(routes.shoppingList) { ShoppingScreen(navController, drawerState, scope) }
        composable(routes.pantry) { PantryScreen(navController, drawerState, scope) }
        composable(routes.recipes) { RecipeScreen(navController, drawerState, scope) }
        composable(routes.profile) { ProfileScreen(navController, drawerState, scope, viewModel) }
        composable(routes.addPantryItem) { AddPantryItemScreen(navController, drawerState, scope)}
    }
}