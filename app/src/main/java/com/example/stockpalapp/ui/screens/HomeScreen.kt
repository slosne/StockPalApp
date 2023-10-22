package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun PantryCarousel(modifier: Modifier = Modifier) {
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val pantryItems by homeScreenViewModel.pantry.collectAsState(initial = emptyList())

    Column {
        Text(
            text = stringResource(R.string.my_pantry),
            modifier = modifier
        )
        Card {
            LazyRow{
                items(pantryItems){item -> Text(text = item.name)}
            }
        }
    }
}


@Composable
fun RecommendedRecipeCard(modifier: Modifier = Modifier){
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val recipeList by homeScreenViewModel.recipes.collectAsState(initial = emptyList())

    //Det må endres på hva som hentes inn etterhvert. Foreløpig henter den bare
    //random data for å se at det fungerer. Det passer ikke med hva vi har tenkt.

    Column {
        Card {
            Row {
                Text(text = stringResource(R.string.image))
                LazyColumn{
                    item {
                        Text(text = stringResource(R.string.recommodation))
                    }
                    items(recipeList){recipe ->
                        Column {
                            Text(text = recipe.title)}
                            Text(text = "Cooking time: " + recipe.cookingTime)
                            Text(text = "Cuisine: " + recipe.cuisine)
                        }
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope){

    AppLayout(
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                //Kommentert ut PantryCarousel, fordi det er noe galt med innehenting av pantryitems
                //PantryCarousel()
                RecommendedRecipeCard()
            }
        },
        topAppBarTitle = stringResource(R.string.stockpal),
        navigationIcon = Icons.Default.Menu,
        actionIcon = null,
        navigationContentDescription = stringResource(R.string.navigation_drawer),
        actionContentDescription = null,
        navController = navController,
        leftIconClickHandler = {scope.launch { drawerState.open() }},
        drawerState = drawerState,
        scope = scope,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        HomeScreen(navController, drawerState, scope)
    }
}

