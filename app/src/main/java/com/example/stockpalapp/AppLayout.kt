package com.example.stockpalapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
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
                NavigationBar(Modifier.fillMaxWidth()) {

                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        NavigationBarItem(selected = true, onClick = { navController.navigate("home")}, icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) }, label = { Text(
                            text = "Home"
                        )})
                        NavigationBarItem(selected = false, onClick = { navController.navigate("shoppinglist") }, icon = {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                        }, label = { Text(
                            text = "Shopping list"
                        )})
                        NavigationBarItem(selected = false, onClick = { navController.navigate("pantry") }, icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null) }, label = { Text(
                            text = "Pantry"
                        )})
                        NavigationBarItem(selected = false, onClick = { navController.navigate("recipes") }, icon = { Icon(imageVector = Icons.Default.List, contentDescription = null) }, label = { Text(
                            text = "Recipes"
                        )})
                    }
                }
            }
        },
        content = { paddingValues ->
            content(paddingValues)
        },
    )

}