package com.example.stockpalapp.presentation.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.presentation.components.CategoryTab
import com.example.stockpalapp.presentation.components.FilledBtn
import com.example.stockpalapp.presentation.components.ProductListItem
import com.example.stockpalapp.presentation.model.Routes
import com.example.stockpalapp.presentation.theme.StockPalAppTheme
import com.example.stockpalapp.presentation.viewmodels.PantryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItemList(modifier: Modifier = Modifier){

    val pantryViewModel: PantryViewModel = hiltViewModel()
    val sortedList = pantryViewModel.sortedList.collectAsState().value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    LazyColumn(
        state = rememberLazyListState(),
        modifier = modifier
    )
    {
        itemsIndexed(items = sortedList, key = {index, item -> item.hashCode() }){index, item ->

            val state = rememberDismissState(
                confirmValueChange = {
                    if (it == DismissValue.DismissedToStart){
                        pantryViewModel.removePantryProduct(item.id)

                        scope.launch {

                            if (pantryViewModel.internetConnection.isInternetAvailable(context)) {
                                val result = snackbarHostState.showSnackbar(
                                    message = "${item.name} er fjernet",
                                    actionLabel = "Angre",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Long
                                )
                                when(result) {
                                    SnackbarResult.Dismissed -> {
                                    }
                                    SnackbarResult.ActionPerformed -> {
                                        pantryViewModel.addPantryProduct(item)
                                    }
                                }
                            } else {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Internet er ikke tilgjengelig. Sletting av vare vil ikke synce før internetet er tilbake",
                                    actionLabel = "Angre",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Long
                                )
                                when(result) {
                                    SnackbarResult.Dismissed -> {
                                    }
                                    SnackbarResult.ActionPerformed -> {
                                        pantryViewModel.addPantryProduct(item)
                                    }
                                }
                            }

                        }
                    }
                    true
                }
            )



            SwipeToDismiss(
                state = state,
                directions = setOf(DismissDirection.EndToStart),
                background = {
                    val color = when(state.dismissDirection){
                        DismissDirection.EndToStart-> Color.Red
                        DismissDirection.StartToEnd-> Color.Transparent
                        null-> Color.Transparent
                    }
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                    ){
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete",
                            modifier = Modifier.align(Alignment.CenterEnd))
                    }
                },
                dismissContent = {

                    val numberUI = remember { mutableStateOf(item.number)}
                    ProductListItem(
                        title = item.name,
                        description = null,
                        imageUrl = item.image,
                        date = item.expDate,
                        amount = numberUI.value) {


                        Row {
                            Column {
                                IconButton(onClick = {
                                    pantryViewModel.updatePantryProductByAddingANumber(item.id, item)
                                    numberUI.value = numberUI.value + 1
                                }) {
                                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Add")
                                }
                                IconButton(onClick = {
                                    pantryViewModel.updatePantryProductBySubtractingAnumber(item.id, item)
                                    numberUI.value = numberUI.value - 1
                                }) {
                                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Remove")
                                }
                            }
                        }
                    }
                }
            )
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ){

        }

    }
}


@Composable
fun PantryScreenBtn(navController: NavController){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        FilledBtn(btnText = stringResource(R.string.add_item), clickHandler = { navController.navigate(Routes().addPantryItem)})
    }
}

@Composable
fun PantrySearch(){

    val pantryViewModel: PantryViewModel = hiltViewModel()
    val pantryProducts by pantryViewModel.sortPantryByCategory.collectAsState(initial = emptyList())
    pantryViewModel.updateList(pantryProducts)

    var searchValue by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center){
        OutlinedTextField(
            value = searchValue,
            onValueChange = { searchValue = it
                pantryViewModel.updateSearch(searchValue)
                pantryViewModel.updatePantryCategorisation()
                pantryViewModel.updateList(pantryProducts)},
            shape = OutlinedTextFieldDefaults.shape,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        )
    }
}

@Composable
fun PantryScreen(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope){

    AppLayout(content = { paddingValues ->
          Column(modifier = Modifier.padding(paddingValues))
          {
              CategoryTab()
              Spacer(modifier = Modifier.size(30.dp))
              PantrySearch()
              Spacer(modifier = Modifier.size(10.dp))
              PantryScreenBtn(navController)
              Spacer(modifier = Modifier.size(20.dp))
              FoodItemList()


          }},
      topAppBarTitle = stringResource(R.string.pantry),
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


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "LightModePreview"
)

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DarkModePreview"
)


@Preview(showBackground = true)
@Composable
fun PantryScreenPreview() {
    StockPalAppTheme(useDarkTheme = false) {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        PantryScreen(navController, drawerState, scope )
    }
}