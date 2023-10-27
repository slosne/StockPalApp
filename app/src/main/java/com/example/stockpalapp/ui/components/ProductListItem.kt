package com.example.stockpalapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListItem(
    title: String,
    description: String,
    imageUrl: String,
    actions: @Composable () -> Unit

)
{
    Column {
        ListItem(
            headlineText = { Text(text = title) },
            supportingText = { Text(text = description) },
            leadingContent = {
                Box(
                    modifier = Modifier.size(100.dp)
                ) {
                    val imagePainter = rememberImagePainter(data = imageUrl)
                    Image(
                        painter = imagePainter,
                        contentDescription = title,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            },
            trailingContent = { actions() }
        )
        Divider()
    }
}