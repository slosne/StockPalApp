package com.example.stockpalapp.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.stockpalapp.R
import com.example.stockpalapp.data.Datasource
import com.example.stockpalapp.ui.components.CategoryTab
import com.example.stockpalapp.ui.components.PantrySearch
import com.example.stockpalapp.ui.model.Models
import com.example.stockpalapp.ui.model.Routes
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
                    text = stringResource(R.string.date),
                    modifier = Modifier
                        .padding(2.dp)
                        .align(CenterHorizontally),
                )
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .align(CenterVertically)
                .padding(horizontal = 10.dp)
            ) { Text(text = stringResource(R.string.delete))

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
fun PantryScreenBtn(navController: NavController){
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(R.string.share_pantry))
        }
        Spacer(modifier = Modifier.size(15.dp))
        Button(onClick = { navController.navigate(Routes().addPantryItem) }) {
            Text(text = stringResource(R.string.add))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope){
   StockPalAppTheme {
     Surface(tonalElevation = 5.dp) {
      AppLayout(content = { paddingValues ->
          Column(modifier = Modifier.padding(paddingValues)) {
              CategoryTab()
              Spacer(modifier = Modifier.size(30.dp))
              PantrySearch()
              Spacer(modifier = Modifier.size(10.dp))
             FoodItemList(foodItemList = Datasource().loadFoodItems())
             Spacer(modifier = Modifier.size(20.dp))
              PantryScreenBtn(navController)
     }},
          topAppBarTitle = stringResource(R.string.pantry),
          navigationIcon = Icons.Default.ArrowBack,
          actionIcon = Icons.Default.Menu,
          navigationContentDescription = stringResource(R.string.navigate_up),
          actionContentDescription = stringResource(R.string.navigation_drawer),
          navController = navController,
          navigationClickHandler = {navController.navigateUp()},
          scope = scope,
          drawerState = drawerState,
          arrowBackClickHandler = {scope.launch { drawerState.open() }}
      )
     }
   }   
}


@OptIn(ExperimentalMaterial3Api::class)
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
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        PantryScreen(navController, drawerState, scope )
    }
}