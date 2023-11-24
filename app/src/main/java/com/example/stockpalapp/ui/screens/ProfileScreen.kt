package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.model.Routes
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileCard(
    viewModel: AuthViewModel?,
    name: String,
    email: String,
    navController: NavController) {

    Card(modifier = Modifier
        .padding(15.dp)
        .fillMaxSize()) {

        Column(modifier = Modifier.padding(30.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(R.string.your_profile),
                style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.padding(20.dp))
            Icon(imageVector = Icons.Default.AccountCircle,
                modifier = Modifier.size(100.dp),
                contentDescription = stringResource(R.string.profile_pic_placeholder))
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = stringResource(R.string.input_label_name) + name,
                style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = stringResource(R.string.input_label_email) + email,
                style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = { viewModel?.logout()
                navController.navigate(Routes().login){
                    popUpTo(Routes().home){
                        inclusive = true
                    }
                }
            })
            {
                Text(text = stringResource(R.string.log_out))
            }
        }
    }
}

@Composable
fun ProfileScreen(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    viewModel: AuthViewModel?)
{
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


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    StockPalAppTheme(useDarkTheme = false) {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ProfileScreen(navController, drawerState, scope, null )
    }
}
