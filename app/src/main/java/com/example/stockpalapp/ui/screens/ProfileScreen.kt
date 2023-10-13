package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.R.drawable.womansprofile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileCard() {
    Card(modifier = Modifier.padding(15.dp)) {
        Column(modifier = Modifier.padding(30.dp)) {
            Image(painter = painterResource(id = womansprofile) , contentDescription = null)
                Text(text = stringResource(R.string.input_label_name))
                Text(text = stringResource(R.string.input_label_email))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope){
    StockPalAppTheme {
        Surface(tonalElevation = 5.dp) {
            AppLayout(content = { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                        ProfileCard()
                }
            },
                topAppBarTitle = stringResource(R.string.profile),
                navigationIcon = Icons.Default.ArrowBack,
                actionIcon = Icons.Default.Menu,
                navigationContentDescription = stringResource(R.string.navigate_up),
                actionContentDescription = stringResource(R.string.navigation_drawer),
                navController = navController,
                leftIconClickHandler = {navController.navigateUp()},
                scope = scope,
                drawerState = drawerState,
                rightIconClickHandler = {scope.launch { drawerState.open() }}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ProfileScreen(navController, drawerState, scope )
    }
}
