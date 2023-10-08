package com.example.stockpalapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
            BottomAppBar {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {navController.navigate("home")}) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                    IconButton(onClick = {navController.navigate("shoppinglist")}) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    LargeFloatingActionButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }
            }
        },
        content = { paddingValues ->
            content(paddingValues)
        },
    )

}