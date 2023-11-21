package com.example.stockpalapp.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.AppLayout
import com.example.stockpalapp.R
import com.example.stockpalapp.ui.components.AlertDialogExample
import com.example.stockpalapp.ui.components.CategoryTab
import com.example.stockpalapp.ui.components.ProductListItem
import com.example.stockpalapp.ui.components.SearchComponent
import com.example.stockpalapp.ui.components.StandardBtn
import com.example.stockpalapp.ui.model.Routes
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.PantryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FoodItemList(modifier: Modifier = Modifier){

    val pantryViewModel: PantryViewModel = hiltViewModel()
    val sortedList = pantryViewModel.sortedList.collectAsState().value

    LazyColumn(modifier = modifier){
        items(sortedList) { item -> ProductListItem(
            title = item.name,
            description = null,
            imageUrl = item.image,
            date = item.expDate,
            ammount = item.number) {
                val openAlertDialog = remember { mutableStateOf(false) }
                IconButton(onClick = { openAlertDialog.value = true }) {
                    Icon(modifier = Modifier.size(30.dp), imageVector = Icons.Default.Delete, contentDescription = "Kjøpt")
                }

                if (openAlertDialog.value) {
                    AlertDialogExample(
                        onDismissRequest = { openAlertDialog.value = false },
                        onConfirmation = {
                            openAlertDialog.value = false
                            println("Confirmation registered") // Add logic here to handle confirmation.

                            //Varen fjernes og legges til i handlevognen
                            pantryViewModel.removePantryProduct(item.id)
                        },
                        dialogTitle = "Vil du fjerne matvaren fra Matskapet?",
                        dialogText = "Er du helt sikker på at du vill fjerne varen fra Matskapet?",
                        icon = Icons.Default.Info
                    )
                }
            }
        }
    }
}


@Composable
fun PantryScreenBtn(navController: NavController){
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        StandardBtn(btnText = "Del matskap", clickHandler = {/*TODO*/})
        Spacer(modifier = Modifier.size(15.dp))
        StandardBtn(btnText = "Legg til", clickHandler = { navController.navigate(Routes().addPantryItem)})
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope,){
   StockPalAppTheme {
     Surface(tonalElevation = 5.dp) {
      AppLayout(content = { paddingValues ->
          Column(modifier = Modifier.padding(paddingValues)) {
              CategoryTab()
              Spacer(modifier = Modifier.size(30.dp))
              SearchComponent()
              Spacer(modifier = Modifier.size(10.dp))
             FoodItemList()
             Spacer(modifier = Modifier.size(20.dp))
              PantryScreenBtn(navController)
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
   }
}


@OptIn(ExperimentalMaterial3Api::class)
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
    StockPalAppTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        PantryScreen(navController, drawerState, scope )
    }
}