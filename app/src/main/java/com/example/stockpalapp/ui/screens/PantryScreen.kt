package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockpalapp.ui.theme.StockPalAppTheme

@Composable
fun FoodItem(modifier: Modifier = Modifier) {
    Card {
        Row {
            Text(text = "bilde")
            Column {
                Text(text = "tekst")
                Text(text = "dato")
            }
            Button(onClick = { /*TODO*/ }) { Text(text = "Fjern")}
        }
    }
}

@Composable
fun FoodItemList(modifier: Modifier = Modifier){
    LazyColumn(){
        items(3){ shoppingItem -> FoodItem() }
    }
}

@Composable
fun PantryScreenBtn(modifier: Modifier = Modifier){
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Del matskap")

    }
}



@Preview(showBackground = true)
@Composable
fun PantryScreenPreview() {
    StockPalAppTheme {
        Column {
            FoodItem()
            FoodItemList()
            PantryScreenBtn()
        }


    }
}