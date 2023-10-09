package com.example.stockpalapp.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.ui.theme.StockPalAppTheme

@Composable
fun FoodItem() {
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
fun FoodItemList(){
    LazyColumn{
        items(3){ shoppingItem -> FoodItem() }
    }
}

@Composable
fun PantryScreenBtn(){
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Del matskap")

    }
}

@Composable
fun PantryScreen(navController: NavController){
   StockPalAppTheme {
     Surface(tonalElevation = 5.dp) {
      AppLayout(content = { paddingValues ->
          Column(modifier = Modifier.padding(paddingValues)) {
             FoodItem()
             FoodItemList()
             PantryScreenBtn()
     }},
          topAppBarTitle = "Matskap",
          navigationIcon = Icons.Default.ArrowBack,
          actionIcon = Icons.Default.Menu,
          navigationContentDescription = null,
          actionContentDescription = null,
          navController = navController,
          leftIconClickHandler = {navController.navigateUp()}
      )
     }
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

@Preview(showBackground = true)
@Composable
fun PantryScreenPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        PantryScreen(navController)
    }
}