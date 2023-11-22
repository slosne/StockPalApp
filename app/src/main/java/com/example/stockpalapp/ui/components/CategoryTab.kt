package com.example.stockpalapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockpalapp.ui.model.categories
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.PantryViewModel


@Composable
fun CategoryTab(){

    val pantryViewModel: PantryViewModel = hiltViewModel()
    val pantryProducts by pantryViewModel.sortPantryByCategory.collectAsState(initial = emptyList())
    pantryViewModel.updateList(pantryProducts)




    val selectedIndex =
        if (pantryViewModel.category.collectAsState().value == "Frysevarer") {
            0
        }
        else  if (pantryViewModel.category.collectAsState().value == "Kjølevarer") {
            1
        }
        else if (pantryViewModel.category.collectAsState().value == "Tørrvarer") {
            2
        }
        else {
            3
        }


    Column {
        TabRow(selectedTabIndex = selectedIndex) {
            categories.forEachIndexed { index, category ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { pantryViewModel.convertStateToCategory(index); pantryViewModel.updatePantryCategorisation(); pantryViewModel.updateList(pantryProducts)},
                    text = { Text(text = category, maxLines = 2, overflow = TextOverflow.Ellipsis,) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabPreview() {
    StockPalAppTheme(useDarkTheme = false) {
        CategoryTab()
    }
}