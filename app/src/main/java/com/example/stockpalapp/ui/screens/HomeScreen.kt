package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.ui.theme.StockPalAppTheme

//test commit

@Composable
fun PantryCarousel(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Mitt matskap",
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
            text = "Dagens anbefaling",
            modifier = modifier
        )
        Card {
            Row {
                Text(text = "bilde")
                LazyColumn(){
                    item {
                        Text(text = "Du mangler:")
                    }
                    items(5){ pantry -> Text(text = "test")}
                }
            }

        }
    }
}

@Composable
fun HomeLayout(modifier: Modifier = Modifier){
    AppLayout(content = { paddingValues ->
        Column {
        PantryCarousel()
        RecommendedRecipeCard()
    }}, topAppBarTitle = "StockPal")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StockPalAppTheme {
        HomeLayout()
    }
}