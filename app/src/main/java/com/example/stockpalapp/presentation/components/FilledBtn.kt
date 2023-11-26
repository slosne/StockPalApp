package com.example.stockpalapp.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stockpalapp.presentation.theme.StockPalAppTheme

@Composable
fun FilledBtn(
    modifier: Modifier = Modifier,
    clickHandler: () -> Unit,
    btnText: String,
    enabled: Boolean = true) {

    Button(
        onClick = clickHandler,
        modifier = modifier,
        enabled = enabled,
        elevation = ButtonDefaults.buttonElevation(5.dp))
    {
        Text(text = btnText)
    }
}


@Preview(showBackground = true)
@Composable
fun FilledBtnPreview() {
    StockPalAppTheme(useDarkTheme = false) {
        FilledBtn(clickHandler = {}, btnText = "Test")
    }
}