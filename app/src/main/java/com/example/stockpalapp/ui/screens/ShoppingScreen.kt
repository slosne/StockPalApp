package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShoppingItem() {
        Card {
            Row {
                Text(text = "bilde")
                Column {
                    Text(text = "tekst")
                    Button(onClick = { /*TODO*/ }) { Text(text = "Kjøp")} 
                }
                Button(onClick = { /*TODO*/ }) { Text(text = "Fjern")}
            }
    }
}

@Composable
fun ShoppingItemList(){
    LazyColumn(){
        items(3){ shoppingItem -> ShoppingItem()}
    }
}

@Composable
fun ShoppingListBtn(){
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Legg til")
        
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope){
    AppLayout(content = { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            ShoppingListSearch()
            ShoppingItem()
            ShoppingItemList()
            ShoppingListBtn()
        }},
        topAppBarTitle = "Handleliste",
        navigationIcon = Icons.Default.ArrowBack,
        actionIcon = Icons.Default.Menu,
        navigationContentDescription = null,
        actionContentDescription = null,
        navController = navController,
        leftIconClickHandler = {navController.navigateUp()},
        drawerState = drawerState,
        scope = scope,
        rightIconClickHandler = {scope.launch { drawerState.open() }}

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListSearch(){
    Row {
        TextField(value = "", onValueChange = {})
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Søk")
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ShoppingScreenPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ShoppingScreen(navController, drawerState, scope)


    }
}