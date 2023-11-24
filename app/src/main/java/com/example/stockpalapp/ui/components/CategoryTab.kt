package com.example.stockpalapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
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
fun CategoryTab() {

    val pantryViewModel: PantryViewModel = hiltViewModel()
    val pantryProducts by pantryViewModel
        .sortPantryByCategory
        .collectAsState(initial = emptyList())
    pantryViewModel.updateList(pantryProducts)

    val selectedIndex = when (pantryViewModel.category.collectAsState().value) {
        categories[0] -> 0
        categories[1] -> 1
        categories[2] -> 2
        else -> 3
    }

    Column {
        TabRow(selectedTabIndex = selectedIndex) {
            categories.forEachIndexed { index, category ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = {
                        pantryViewModel.convertStateToCategory(index)
                        pantryViewModel.updatePantryCategorisation()
                        pantryViewModel.updateList(pantryProducts)
                    },
                    text = {
                        Text(
                            text = category,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
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