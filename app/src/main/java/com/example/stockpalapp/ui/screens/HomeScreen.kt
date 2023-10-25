package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import coil.compose.rememberImagePainter


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
                items(pantryItems){item ->
                    Text(text = item.cookingTime)
                    Text(text = item.vendor)
                    Text(text = item.eannumber.toString())
                }
            }
        }
    }
}

@Composable
fun RecommendedRecipeCard(){
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val recipeList by homeScreenViewModel.recipes.collectAsState(initial = emptyList())

    //Det må endres på hva som hentes inn etterhvert. Foreløpig henter den
    //bare en liste med oppskrifter, men den skal egentlig gi anbefaling ut fra ingredienser i pantry

    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = stringResource(R.string.recommodation))
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(recipeList){recipe ->
                Card(modifier = Modifier
                    .fillMaxWidth())
                {
                    Row(
                        modifier = Modifier
                            .height(300.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(0.3f)
                        )
                        {
                            val imagePainter = rememberImagePainter(data = recipe.image)
                            Image(
                                painter = imagePainter,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Column(modifier = Modifier.fillMaxSize()
                            .weight(0.7f)
                            .padding(8.dp))
                        {
                            Text(text = recipe.title)
                            Spacer(modifier = Modifier.padding(5.dp))
                            Text(text = "Cooking time: " + recipe.cookingTime)
                            Spacer(modifier = Modifier.padding(5.dp))
                            Column{
                                Text(text = "Ingredients:")
                                recipe.ingredients.forEach { ingredient ->
                                    Text(text = ingredient)
                                }
                            }
                        }
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
                PantryCarousel()
                RecommendedRecipeCard()
            }
        },
        topAppBarTitle = stringResource(R.string.stockpal),
        navigationIcon = null,
        actionIcon = Icons.Default.Menu,
        navigationContentDescription = stringResource(R.string.navigation_drawer),
        actionContentDescription = null,
        navController = navController,
        arrowBackClickHandler = {scope.launch { drawerState.open() }},
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

