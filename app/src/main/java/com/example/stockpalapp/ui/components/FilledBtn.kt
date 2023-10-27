package com.example.stockpalapp.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockpalapp.ui.theme.StockPalAppTheme

@Composable
fun FilledBtn(
    modifier: Modifier = Modifier,
    clickHandler: () -> Unit = {},
    btnText: String)
{
    Button(
        onClick = { clickHandler }, modifier = modifier, colors = ButtonDefaults.buttonColors(Color.Blue),
    ) {
        Text(text = btnText)
    }
}

@Preview(showBackground = true)
@Composable
fun FilledBtnPreview() {
    StockPalAppTheme {
        FilledBtn(btnText = "test")
    }
}