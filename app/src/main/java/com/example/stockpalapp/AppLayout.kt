package com.example.stockpalapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpalapp.ui.model.BottomNavItem

val navItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Shoppinglist,
    BottomNavItem.Pantry,
    BottomNavItem.Recipe
)

@Composable
fun BottomNavigation(navController: NavController){
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    NavigationBar {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            navItems.forEach { item ->
                val selected = currentRoute == item.screen_route
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(item.screen_route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {saveState = true}
                            }
                            launchSingleTop = true
                            restoreState = true}
                    },
                    icon = { Icon(imageVector = item.icon, contentDescription = null) },
                    label = { Text(item.title) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayout(
    content: @Composable (PaddingValues) -> Unit,
    leftIconClickHandler: () -> Unit = {},
    rightIconClickHandler: () -> Unit = {},
    topAppBarTitle: String,
    navigationIcon: ImageVector,
    actionIcon: ImageVector?,
    navigationContentDescription: String?,
    actionContentDescription: String?,
    navController: NavController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = topAppBarTitle) },
                navigationIcon = {
                    IconButton(onClick = {leftIconClickHandler()}) {
                        Icon(
                            imageVector = navigationIcon,
                            contentDescription = navigationContentDescription
                        )
                    }
                }, actions = {
                    IconButton(onClick = {rightIconClickHandler()}) {
                        if (actionIcon != null) {
                            Icon(
                                imageVector = actionIcon,
                                contentDescription = actionContentDescription
                            )
                        }
                    }
                }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xE7F0F5ff))
            )
        },
        bottomBar = {
            Column(horizontalAlignment = Alignment.End) {
                LargeFloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(16.dp)) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
                Spacer(modifier = Modifier.padding(10.dp))
                BottomNavigation(navController)
            }
        },
        content = { paddingValues ->
            content(paddingValues)
        },
    )

}