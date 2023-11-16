package com.example.stockpalapp.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FilledBtn(
    modifier: Modifier = Modifier,
    clickHandler: () -> Unit,
    btnText: String)
{
    Button(
        onClick = clickHandler, modifier = modifier
    ) {
        Text(text = btnText)
    }
}
/*

@Preview(showBackground = true)
@Composable
fun FilledBtnPreview() {
    StockPalAppTheme {
        FilledBtn(btnText = "test", clickHandler = {})
    }
}

 */