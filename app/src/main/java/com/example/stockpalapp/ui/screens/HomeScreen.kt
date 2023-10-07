package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.theme.StockPalAppTheme

@Composable
fun PantryCarousel(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(R.string.mypantry),
            modifier = modifier
        )
        Card {
            LazyRow(){
                items(5){ pantry -> Text(text = "test")}
            }
        }
    }
}

@Composable
fun RecommendedRecipeCard(modifier: Modifier = Modifier){
    Column {
        Text(
            text = stringResource(R.string.recommodation),
            modifier = modifier
        )
        Card {
            Row {
                Text(text = "bilde")
                LazyColumn(){
                    item {
                        Text(text = stringResource(R.string.missing))
                    }
                    items(5){ pantry -> Text(text = "test")}
                }
            }

        }
    }
}

@Composable
fun HomeScreen(navController: NavController){
    AppLayout(
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                PantryCarousel()
                RecommendedRecipeCard()
            }
        },
        topAppBarTitle = stringResource(R.string.stockpal),
        navigationIcon = Icons.Default.Menu,
        actionIcon = null,
        navigationContentDescription = null,
        actionContentDescription = null,
        navController = navController
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