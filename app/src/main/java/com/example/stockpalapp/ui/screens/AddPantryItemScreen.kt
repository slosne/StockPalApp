package com.example.stockpalapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockpalapp.ui.components.FilledBtn
import com.example.stockpalapp.ui.viewmodels.AddPantryItemViewModel

@Composable
fun AddPantryItemScanning() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        FilledBtn(clickHandler = { /*TODO*/ }, btnText = stringResource(R.string.scan_item))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPantryItem() {
    val addPantryItemViewModel: AddPantryItemViewModel = hiltViewModel()
    var expandedState by remember { mutableStateOf(true)}

    val context = LocalContext.current

    ElevatedCard(
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 10.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(modifier = Modifier.padding(30.dp)) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = stringResource(R.string.add_pantryitem)
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = { expandedState = !expandedState }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop-Down Arrow")
                }

            }
            Spacer(modifier = Modifier.size(15.dp))

            if(expandedState) {
                Column {
                    var text by remember { mutableStateOf("")}
                    OutlinedTextField(
                        value = text,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newText ->
                            text = newText
                        },
                        label = {
                            Text(text = stringResource(R.string.item_name))
                        },
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    var text5 by remember { mutableStateOf("")}
                    Text(text = stringResource(R.string.required), color = Color.Red, fontSize = 12.sp)
                    OutlinedTextField(
                        value = text5,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newText ->
                            text5 = newText
                        },
                        label = {
                            Text(text = stringResource(R.string.ean))
                        }
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    var text2 by remember { mutableStateOf("")}
                    OutlinedTextField(
                        value = text2,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newText ->
                            text2 = newText
                        },
                        label = {
                            Text(text = stringResource(R.string.ammount))
                        }
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    var text3 by remember { mutableStateOf("")}
                    OutlinedTextField(
                        value = text3,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newText ->
                            text3 = newText
                        },
                        label = {
                            Text(text = stringResource(R.string.category))
                        }
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                    var text4 by remember { mutableStateOf("")}
                    OutlinedTextField(
                        value = text4,
                        shape = TextFieldDefaults.outlinedShape,
                        onValueChange = { newText ->
                            text4 = newText
                        },
                        label = {
                            Text(text = stringResource(R.string.exp_date))
                        }
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()){
                        FilledBtn(clickHandler = { addPantryItemViewModel.addPantryProduct(text, text5.toInt(), text2.toInt(), text3, text4, context)
                            text = ""
                            text2 = ""
                            text3 = ""
                            text4 = ""
                            text5 = "" }, btnText = stringResource(R.string.save))
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
                    Spacer(modifier = Modifier.size(10.dp))
                    AddPantryItemScanning()
                    AddPantryItem()
                }
            },
                topAppBarTitle = stringResource(R.string.add_pantryitem),
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