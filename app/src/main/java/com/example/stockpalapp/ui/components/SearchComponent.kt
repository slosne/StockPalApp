package com.example.stockpalapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.PantryViewModel

@Composable
fun SearchComponent(){

    val pantryViewModel: PantryViewModel = hiltViewModel()
    val pantryProducts by pantryViewModel.sortPantryByCategory.collectAsState(initial = emptyList())
    pantryViewModel.updateList(pantryProducts)

    var searchValue by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        OutlinedTextField(
            value = searchValue,
            onValueChange = { searchValue = it ; pantryViewModel.updateSearch(searchValue); pantryViewModel.updatePantryCategorisation(); pantryViewModel.updateList(pantryProducts)},
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


@Preview(showBackground = true)
@Composable
fun PantrySearchPreview() {
    StockPalAppTheme(useDarkTheme = false) {
        SearchComponent()
    }
}
