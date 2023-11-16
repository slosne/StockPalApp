package com.example.stockpalapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.model.Routes
import com.example.stockpalapp.ui.viewmodels.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileCard(
    viewModel: AuthViewModel?,
    name: String,
    email: String,
    navController: NavController)
{
    Card(modifier = Modifier.padding(15.dp)) {
        Column(modifier = Modifier.padding(30.dp)) {
            Text(text = "Your profile")
            Text(text = stringResource(R.string.input_label_name) + name)
                Text(text = stringResource(R.string.input_label_email) + email)
            
            Button(onClick = { viewModel?.logout()
                Log.e("ProfileScreen", "Logging out")
                navController.navigate(Routes().login){
                    popUpTo(Routes().home){
                        inclusive = true
                    }
                } }) 
            {
                Text(text = "Log Out")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    viewModel: AuthViewModel?)
{
    Surface(tonalElevation = 5.dp) {
        AppLayout(content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                viewModel?.currentUser?.let {
                    ProfileCard(viewModel = viewModel, navController = navController, name = it.displayName.toString(), email = it.email.toString())
                }
            }
        },
            topAppBarTitle = stringResource(R.string.profile),
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

/*
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


 */