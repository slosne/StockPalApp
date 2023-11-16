package com.example.stockpalapp.ui.components

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StandardBtn(
    modifier: Modifier = Modifier,
    clickHandler: () -> Unit,
    btnText: String)
{
    OutlinedButton(onClick = clickHandler, modifier = modifier,
    ) {
        Text(text = btnText)
    }
}

/*
@Preview(showBackground = true)
@Composable
fun StandardBtnPreview() {
    StockPalAppTheme {
        StandardBtn(btnText = "test", clickHandler = {})
    }
}

 */