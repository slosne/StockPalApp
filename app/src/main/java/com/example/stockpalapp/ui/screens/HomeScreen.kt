package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import com.example.stockpalapp.ui.components.SmallRecipeCard
import com.example.stockpalapp.ui.viewmodels.PantryViewModel


@Composable
fun PantryCarousel() {
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val sortedList by homeScreenViewModel.sortedProductsByExpDate.collectAsState(initial = emptyList())

    val pantryViewModel: PantryViewModel = hiltViewModel()

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = stringResource(R.string.closest_expdate), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.size(10.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            items(sortedList.take(3)){item ->
                ElevatedCard(modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(4.dp),
                    elevation = CardDefaults.cardElevation(2.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically){
                        Text(text = item.name)
                        if (item.expDate != null) {
                            Text(text = pantryViewModel.convertTimestampToString(item.expDate))
                        }
                    }


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
                SmallRecipeCard(
                    recipe.title, 
                    recipe.image,
                    recipe.ingredients)
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

