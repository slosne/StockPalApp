package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.R.drawable.womansprofile

@Composable
fun ProfileCard() {
    Card(modifier = Modifier.padding(15.dp)) {
        Column(modifier = Modifier.padding(30.dp)) {
            Image(painter = painterResource(id = womansprofile) , contentDescription = null)
                Text(text = "Kari Nordmann")
                Text(text = "kari@nordmann.com")
        }
    }

}
@Composable
fun ProfileScreen(navController: NavController){
    StockPalAppTheme {
        Surface(tonalElevation = 5.dp) {
            AppLayout(content = { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                        ProfileCard()
                }
            },
                topAppBarTitle = "Profil",
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

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        ProfileScreen(navController)
    }
}
