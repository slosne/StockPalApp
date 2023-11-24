package com.example.stockpalapp.ui.components

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockpalapp.ui.theme.StockPalAppTheme

@Composable
fun OutlinedBtn(
    modifier: Modifier = Modifier,
    clickHandler: () -> Unit,
    btnText: String)
{
    OutlinedButton(
        onClick = clickHandler,
        modifier = modifier)
    {
        Text(text = btnText)
    }
}

@Preview(showBackground = true)
@Composable
fun StandardBtnPreview() {
    StockPalAppTheme(useDarkTheme = false) {
        OutlinedBtn(btnText = "test", clickHandler = {})
    }
}