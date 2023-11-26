package com.example.stockpalapp.presentation.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.presentation.components.AlertDialogComponent
import com.example.stockpalapp.presentation.components.FilledBtn
import com.example.stockpalapp.presentation.components.ProductListItem
import com.example.stockpalapp.presentation.model.Routes
import com.example.stockpalapp.presentation.theme.StockPalAppTheme
import com.example.stockpalapp.presentation.viewmodels.ShoppingScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShoppingItemList(modifier: Modifier = Modifier){

    val shoppingScreenViewModel: ShoppingScreenViewModel = hiltViewModel()
    val shoppingList by shoppingScreenViewModel.shoppingListProducts.collectAsState(initial = emptyList())

    val context = LocalContext.current

    LazyColumn(modifier = modifier){
        items(shoppingList) { item ->
            ProductListItem(
                title = item.name,
                description = item.category,
                imageUrl = item.image,
                date = null,
                amount = null)
            {
                Row {
                    val openAlertDialog1 = remember { mutableStateOf(false)}

                    IconButton(onClick = { openAlertDialog1.value = true })
                    {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = Icons.Default.Check,
                            contentDescription = "Kjøpt"
                        )
                    }

                    if (openAlertDialog1.value) {
                        AlertDialogComponent(
                            onDismissRequest = { openAlertDialog1.value = false },
                            onConfirmation = {
                                openAlertDialog1.value = false

                                //Varen fjernes og legges til i matskapet
                                shoppingScreenViewModel.AddShoppingListProductToPantry(item)
                            },
                            dialogTitle = stringResource(R.string.add_to_pantry),
                            dialogText = stringResource(R.string.pantry_add_conf),
                            icon = Icons.Default.Info
                        )
                        if (!shoppingScreenViewModel.internetConnection.isInternetAvailable(context)){
                            Toast.makeText(context, "Internet ikke tilgjengelig. Fjernes fra liste, men ikke syncet til skyen", Toast.LENGTH_LONG).show()
                        }
                    }

                    Spacer(modifier = Modifier.size(7.dp))
                    val openAlertDialog2 = remember { mutableStateOf(false)}
                    IconButton(onClick = { openAlertDialog2.value = true })
                    {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Kjøpt"
                        )
                    }

                    if (openAlertDialog2.value) {
                        AlertDialogComponent(
                            onDismissRequest = { openAlertDialog2.value = false },
                            onConfirmation = {
                                openAlertDialog2.value = false

                                //Varen fjernes fra handlelisten
                                shoppingScreenViewModel.removeShoppingListProduct(item.id)
                            },
                            dialogTitle = stringResource(R.string.shoppinglist_rem),
                            dialogText = stringResource(R.string.shopping_list_rem_conf),
                            icon = Icons.Default.Info
                        )
                        if (!shoppingScreenViewModel.internetConnection.isInternetAvailable(context)){
                            Toast.makeText(context, "Internet ikke tilgjengelig. Fjernes fra liste, men ikke syncet til skyen", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShoppingScreen(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope) {

        AppLayout(
            content = { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues))
                {
                    ShoppingItemList()
                    Spacer(modifier = Modifier.size(30.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth())
                    {
                        FilledBtn(
                            modifier = Modifier,
                            clickHandler = {navController.navigate(Routes().addPantryItem)},
                            btnText = "Legg til")
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
            },
            topAppBarTitle = stringResource(R.string.shopping_list),
            navigationIcon = Icons.Default.ArrowBack,
            actionIcon = Icons.Default.Menu,
            navigationContentDescription = stringResource(R.string.navigate_up),
            actionContentDescription = stringResource(R.string.navigation_drawer),
            navController = navController,
            navigationClickHandler = { navController.navigateUp() },
            arrowBackClickHandler = { scope.launch { drawerState.open() } },
            drawerState = drawerState,
            scope = scope
        )
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "LightModePreview"
)

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkModePreview"
)

@Composable
fun ShoppingScreenPreview() {
    StockPalAppTheme(useDarkTheme = false) {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ShoppingScreen(navController, drawerState, scope)
    }
}

