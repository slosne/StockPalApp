package com.example.stockpalapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.components.FilledBtn
import com.example.stockpalapp.ui.components.ProductListItem
import com.example.stockpalapp.ui.components.StandardBtn
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.ShoppingScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShoppingItemList(modifier: Modifier = Modifier){

    val shoppingScreenViewModel: ShoppingScreenViewModel = hiltViewModel()
    val shoppingList by shoppingScreenViewModel.recipes.collectAsState(initial = emptyList())

    LazyColumn(modifier = modifier){
        items(shoppingList) { item -> ProductListItem(title = item.title, description = item.cuisine, imageUrl = item.image) {
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(modifier = Modifier.size(40.dp), imageVector = Icons.Default.Check, contentDescription = "Kjøpt")
                }
                Spacer(modifier = Modifier.size(7.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(modifier = Modifier.size(40.dp), imageVector = Icons.Default.Delete, contentDescription = "Kjøpt")
                }
            }

        }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    StockPalAppTheme {
        Surface(tonalElevation = 5.dp) {
            AppLayout(
                content = { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) {
                        ShoppingItemList()
                        Spacer(modifier = Modifier.size(30.dp))
                        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                            StandardBtn(modifier = Modifier, {}, "Legg til")
                            Spacer(modifier = Modifier.size(10.dp))
                            StandardBtn(modifier = Modifier, {}, "Del handleliste")

                        }
                    }
                },
                topAppBarTitle = stringResource(R.string.shopping_list),
                navigationIcon = Icons.Default.ArrowBack,
                actionIcon = Icons.Default.Menu,
                navigationContentDescription = stringResource(R.string.navigate_up),
                actionContentDescription = stringResource(R.string.navigation_drawer),
                navController = navController,
                navigationClickHandler = { navController.navigateUp() },
                arrowBackClickHandler = { scope.launch { drawerState.open() } },
                drawerState = drawerState,
                scope = scope,

                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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