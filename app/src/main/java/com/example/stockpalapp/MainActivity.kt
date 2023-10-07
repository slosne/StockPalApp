package com.example.stockpalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stockpalapp.ui.screens.HomeLayout
import com.example.stockpalapp.ui.theme.StockPalAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockPalAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeLayout()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayout(
    content: @Composable (PaddingValues) -> Unit,
    navigationClickHandler: () -> Unit,
    actionClickHandler: () -> Unit,
    topAppBarTitle: String,
    navigationIcon: ImageVector,
    actionIcon: ImageVector?,
    navigationContentDescription: String?,
    actionContentDescription: String?,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = topAppBarTitle) },
                navigationIcon = {
                    IconButton(onClick = { navigationClickHandler }) {
                        Icon(
                            imageVector = navigationIcon,
                            contentDescription = navigationContentDescription
                        )
                    }
                }, actions = {
                    IconButton(onClick = { actionClickHandler }) {
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
                    Row {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null
                            )
                        }
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        FloatingActionButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    }
                }
            }
        },
        content = { paddingValues ->
            content(paddingValues)
        },
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StockPalAppTheme {
        HomeLayout()
    }
}