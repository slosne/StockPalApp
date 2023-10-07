package com.example.stockpalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.ui.screens.HomeScreen
import com.example.stockpalapp.ui.screens.PantryScreen
import com.example.stockpalapp.ui.screens.RecipeScreen
import com.example.stockpalapp.ui.screens.ShoppingScreen
import com.example.stockpalapp.ui.theme.StockPalAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockPalAppTheme {
                val routes = Routes()
                val navController = rememberNavController()
                NavHost(navController, startDestination = routes.home) {
                    composable(routes.home) { HomeScreen(navController) }
                    composable(routes.shoppingList) { ShoppingScreen(navController) }
                    composable(routes.pantry) { PantryScreen(navController) }
                    composable(routes.recipes) { RecipeScreen(navController) }
                }
            }
        }
    }
}

//Dataklasse for å hindre typo i routings
data class Routes(
    val home: String = "home",
    val shoppingList: String = "shoppinglist",
    val pantry: String = "pantry",
    val recipes: String = "recipes"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayout(
    content: @Composable (PaddingValues) -> Unit,
    //navigationClickHandler: () -> Unit,
    //actionClickHandler: () -> Unit = {},
    topAppBarTitle: String,
    navigationIcon: ImageVector,
    actionIcon: ImageVector?,
    navigationContentDescription: String?,
    actionContentDescription: String?,
    navController: NavController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = topAppBarTitle) },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = navigationIcon,
                            contentDescription = navigationContentDescription
                        )
                    }
                }, actions = {
                    IconButton(onClick = {}) {
                        if (actionIcon != null) {
                            Icon(
                                imageVector = actionIcon,
                                contentDescription = actionContentDescription
                            )
                        }
                    }
                }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xE7F0F5ff))
            )
        },
        bottomBar = {
            BottomAppBar {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                        IconButton(onClick = {navController.navigate("shoppinglist")}) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null
                            )
                        }
                    }
                        FloatingActionButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }
            }
        },
        content = { paddingValues ->
            content(paddingValues)
        },
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}