package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.viewmodels.ThemeViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ToggleThemeSwitch(
    useDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit
){

    Switch(
        checked = useDarkTheme,
        onCheckedChange = {
            onThemeChanged(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    themeViewModel: ThemeViewModel
)
{

    val useDarkTheme by themeViewModel.useDarkTheme

    AppLayout(
        content = { paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally){
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    shadowElevation = 10.dp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Endre innstillinger",
                            style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.size(20.dp))
                        if(useDarkTheme){
                            Text(
                                text = "Bytt til light mode",
                                style = MaterialTheme.typography.titleLarge)}
                        else{
                            Text(
                                text = "Bytt til dark mode",
                                style = MaterialTheme.typography.titleLarge)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        ToggleThemeSwitch(
                            useDarkTheme = useDarkTheme,
                            onThemeChanged = { isChecked ->
                                themeViewModel.setDarkTheme(isChecked)
                            }
                        )
                    }
                }
            }} ,
        topAppBarTitle = "Settings",
        navigationIcon = null,
        actionIcon = Icons.Default.Menu,
        navigationContentDescription = stringResource(R.string.navigation_drawer),
        actionContentDescription = null,
        navController = navController,
        drawerState = drawerState,
        scope = scope
    )
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SettingPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        SettingScreen(navController, drawerState, scope)
    }
}

 */