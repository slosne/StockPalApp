package com.example.stockpalapp.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.data.Datasource
import com.example.stockpalapp.ui.model.Models
import com.example.stockpalapp.ui.theme.StockPalAppTheme

@Composable
fun FoodItem(models: Models, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .height(100.dp)
        // Legg til padding fra top navigasjonbaren
    ) {
        Row {
            Image (
                painter = painterResource(models.imageResourceId),
                contentDescription = stringResource(models.stringResourceId),
                modifier = Modifier
                    // put inn riktig logikk til at bilde størrelse er automatisk
                    .width(100.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
                )
            Column(modifier = Modifier
                    .weight(1f),
                   verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = LocalContext.current.getString(models.stringResourceId),
                    modifier = Modifier
                        .padding(5.dp)
                        .align(CenterHorizontally),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "dato",
                    modifier = Modifier
                        .padding(2.dp)
                        .align(CenterHorizontally),
                )
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .align(CenterVertically)
                .padding(horizontal = 10.dp)
            ) { Text(text = "Fjern")

            }
        }
    }
}

@Composable
fun FoodItemList(foodItemList: List<Models>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(foodItemList) { models ->
            FoodItem(
                models = models,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun PantryScreenBtn(modifier: Modifier = Modifier){
    Button(onClick = { /*TODO*/ }, modifier = modifier
        //Finn ut hvordan å sentrere knappen uten å manuelt skrive inn halve dp mengden
        .padding(horizontal = 130.dp)
    ) {
        Text(text = "Del matskap")
    }
}

@Composable
fun PantryScreen(navController: NavController){
   StockPalAppTheme {
     Surface(tonalElevation = 5.dp) {
      AppLayout(content = { paddingValues ->
          Column(modifier = Modifier.padding(paddingValues)) {
             FoodItemList(foodItemList = Datasource().loadFoodItems())
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
        ProfileScreen(navController)
    }
}