package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSearch(){
    Row {
        TextField(value = "", onValueChange = {})
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Søk")
        }
    }
}

@Composable
fun RecipeItem() {
    Card {
        Row {
            Text(text = "tekst")
            Text(text = "bilde")
        }
    }
}
@Composable
fun RecipeList(){
    LazyColumn(){
        item { Text(text = "Mangler:") }
        items(3){ recipeItem -> RecipeItem() }
    }
}
@Composable
fun RecipePage(){
    Column {
        RecipeSearch()
        Text(text = "Middagsforslag")
        RecipeList()
        RecipeList()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope){
    AppLayout(content = { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            RecipePage()
        }},
        topAppBarTitle = "Oppskrifter",
        navigationIcon = Icons.Default.ArrowBack,
        actionIcon = Icons.Default.Menu,
        navigationContentDescription = null,
        actionContentDescription = null,
        navController = navController,
        leftIconClickHandler = {navController.navigateUp()},
        drawerState = drawerState,
        scope = scope,
        rightIconClickHandler = {scope.launch { drawerState.open() }}
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RecipeScreenPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        RecipeScreen(navController, drawerState, scope)

    }
}

