package com.example.stockpalapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stockpalapp.ui.theme.StockPalAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(){

    var searchValue by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        OutlinedTextField(
            value = searchValue,
            modifier = Modifier.height(40.dp),
            onValueChange = { searchValue = it },
            shape = TextFieldDefaults.outlinedShape ,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color.Black
                )
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PantrySearchPreview() {
    StockPalAppTheme {
        SearchComponent()
    }
}