package com.example.stockpalapp.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "LightModePreview"
)

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DarkModePreview"
)
 //text
@Composable
fun PantryScreenPreview() {
    StockPalAppTheme {
        Surface(tonalElevation = 5.dp) {
            Column {
                FoodItem()
                FoodItemList()
                PantryScreenBtn()
            }
        }

    }
}