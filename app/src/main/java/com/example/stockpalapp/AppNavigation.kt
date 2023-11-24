package com.example.stockpalapp

import androidx.compose.material3.DrawerValue
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
import com.example.stockpalapp.ui.screens.SettingScreen
import com.example.stockpalapp.ui.screens.SignupScreen
import com.example.stockpalapp.ui.viewmodels.AuthViewModel
import com.example.stockpalapp.ui.viewmodels.ThemeViewModel

@Composable
fun AppNavigation(
    authViewModel: AuthViewModel,
    themeViewModel: ThemeViewModel) {

    val routes = Routes()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavHost(
        navController,
        startDestination = routes.login) {

        composable(routes.home) { HomeScreen(navController, drawerState, scope) }
        composable(routes.signup) { SignupScreen(authViewModel, navController) }
        composable(routes.login) { LoginScreen(authViewModel, navController) }
        composable(routes.shoppingList) { ShoppingScreen(navController, drawerState, scope) }
        composable(routes.pantry) { PantryScreen(navController, drawerState, scope) }
        composable(routes.recipes) { RecipeScreen(navController, drawerState, scope) }
        composable(routes.profile) { ProfileScreen(navController, drawerState, scope, authViewModel) }
        composable(routes.addPantryItem) { AddPantryItemScreen(navController, drawerState, scope)}
        composable(routes.settings) { SettingScreen(navController, drawerState, scope, themeViewModel) }
    }
}