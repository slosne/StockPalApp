package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.ui.theme.StockPalAppTheme

@Composable
fun ShoppingItem(modifier: Modifier = Modifier) {
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
fun ShoppingItemList(modifier: Modifier = Modifier){
    LazyColumn(){
        items(3){ shoppingItem -> ShoppingItem()}
    }
}

@Composable
fun ShoppingListBtn(modifier: Modifier = Modifier){
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Legg til")
        
    }
}

@Composable
fun ShoppingLayout(modifier: Modifier = Modifier){
    AppLayout(content = { paddingValues ->
        Column {
            ShoppingListSearch()
            ShoppingItem()
            ShoppingItemList()
            ShoppingListBtn()
        }}, topAppBarTitle = "Handleliste")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListSearch(modifier: Modifier = Modifier){
    Row {
        TextField(value = "", onValueChange = {})
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Søk")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ShoppingScreenPreview() {
    StockPalAppTheme {
        ShoppingLayout()


    }
}