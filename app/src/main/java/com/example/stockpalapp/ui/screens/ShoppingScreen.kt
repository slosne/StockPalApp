package com.example.stockpalapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.data.Datasource
import com.example.stockpalapp.ui.model.Models
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShoppingItem(models: Models, modifier: Modifier = Modifier) {
        Card(modifier = modifier
            .fillMaxWidth()
            .height(100.dp)) {
            Row {
                Image(
                    painter = painterResource(models.imageResourceId),
                    contentDescription = stringResource(models.stringResourceId),
                    modifier = modifier
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
                            .align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.headlineSmall

                    )
                    Button(onClick = { /*TODO*/ }, modifier = Modifier
                        //Finn ut hvordan å sentrere knappen
                        // uten å manuelt skrive inn halve dp mengden
                        .padding(horizontal = 40.dp)
                    ) { Text(text = stringResource(R.string.buy))}
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp)
                    ) { Text(text = stringResource(R.string.delete))}
            }
    }
}

@Composable
fun ShoppingItemList(shoppingItemList: List<Models>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier){
        items(shoppingItemList) { models ->
            ShoppingItem(
                models = models,
                modifier = Modifier.padding(8.dp)
        )

        }
    }
}

@Composable
fun ShoppingListBtn(modifier: Modifier = Modifier){
    Button(onClick = { /*TODO*/}, modifier = modifier
        //Finn ut hvordan å sentrere knappen uten å manuelt skrive inn halve dp mengden
        .padding(horizontal = 145.dp)
    ) {
        Text(text = stringResource(R.string.add))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(navController: NavController) {
    StockPalAppTheme {
        Surface(tonalElevation = 5.dp) {
            AppLayout(content = { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    ShoppingListSearch()
                    ShoppingItemList(shoppingItemList = Datasource().loadShoppingItems())
                    ShoppingListBtn()
                }
            },
                topAppBarTitle = stringResource(R.string.shopping_list),
                navigationIcon = Icons.Default.ArrowBack,
                actionIcon = Icons.Default.Menu,
                navigationContentDescription = stringResource(R.string.navigate_up),
                actionContentDescription = stringResource(R.string.navigation_drawer),
                navController = navController,
                leftIconClickHandler = { navController.navigateUp() }

            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListSearch(modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(value = "", onValueChange = {})
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.search))
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightModePreview"
)

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkModePreview"
)

@Composable
fun ShoppingScreenPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ShoppingScreen(navController, drawerState, scope)


    }
}