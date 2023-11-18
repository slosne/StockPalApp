package com.example.stockpalapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockpalapp.ui.model.categories
import com.example.stockpalapp.ui.theme.StockPalAppTheme


@Composable
fun CategoryTab(){

    var state by remember {
        mutableStateOf(0)
    }

    Column {
        TabRow(selectedTabIndex = state) {
            categories.forEachIndexed { index, category ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = category, maxLines = 2, overflow = TextOverflow.Ellipsis) }
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