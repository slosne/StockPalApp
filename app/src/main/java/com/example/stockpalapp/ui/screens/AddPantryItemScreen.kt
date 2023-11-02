package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockpalapp.ui.viewmodels.AddPantryItemViewModel
import com.example.stockpalapp.ui.viewmodels.HomeScreenViewModel

@Composable
fun AddPantryItemScanning() {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Scanne Produkter")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPantryItem() {
    val addPantryItemViewModel: AddPantryItemViewModel = hiltViewModel()
    var expandedState by remember { mutableStateOf(true)}

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        modifier = Modifier
            .padding(30.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(modifier = Modifier) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = "Add Product")
                IconButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = { expandedState = !expandedState }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop-Down Arrow")
                }

            }
            if(expandedState) {
                Column {
                    var text by remember { mutableStateOf("Type here")}
                    TextField(
                        value = text,
                        onValueChange = { newText ->
                        text = newText
                        },
                        label = {
                            Text(text = "Name")
                        },
                        supportingText = {
                            Text(text = "Obligatorisk")
                        }
                    )
                    var text5 by remember { mutableStateOf("Type here")}
                    TextField(
                        value = text5,
                        onValueChange = { newText ->
                            text5 = newText
                        },
                        label = {
                            Text(text = "EAN Nummer")
                        }
                    )
                    var text2 by remember { mutableStateOf("Type here")}
                    TextField(
                        value = text2,
                        onValueChange = { newText ->
                            text2 = newText
                        },
                        label = {
                            Text(text = "Antall")
                        }
                    )
                    var text3 by remember { mutableStateOf("Type here")}
                    TextField(
                        value = text3,
                        onValueChange = { newText ->
                            text3 = newText
                        },
                        label = {
                            Text(text = "Kategori")
                        }
                    )
                    var text4 by remember { mutableStateOf("Type here")}
                    TextField(
                        value = text4,
                        onValueChange = { newText ->
                            text4 = newText
                        },
                        label = {
                            Text(text = "Utløpsdato")
                        }
                    )
                    Button(onClick = { addPantryItemViewModel.addPantryProduct(text, text5.toInt(), text2.toInt(), text3, text4) }) {
                        Text(text = "Submit")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPantryItemScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope){
    StockPalAppTheme {
        Surface(tonalElevation = 5.dp) {
            AppLayout(content = { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    AddPantryItemScanning()
                    AddPantryItem()
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddPantryItemScreenPreview() {
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        AddPantryItemScreen(navController, drawerState, scope)
    }
}