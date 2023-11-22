package com.example.stockpalapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.stockpalapp.ui.theme.StockPalAppTheme


@Composable
fun SmallRecipeCard(
    title: String,
    imageUrl: String,
    missingIngredients: List<String>)
{

    val imagePainter = rememberImagePainter(data = imageUrl)

    Surface(
        modifier = Modifier
            .height(210.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 7.dp
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(4f), horizontalAlignment = Alignment.CenterHorizontally){
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = title, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.size(7.dp))
                    Text(text = "Du mangler: ", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.size(7.dp))
                    for (ingredient in missingIngredients) {
                        Text(text = ingredient, style = MaterialTheme.typography.titleSmall)
                    }
                }
                Surface(shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.size(width = 100.dp, height = 140.dp)) {
                    Image(
                        painter = imagePainter,
                        contentDescription = "plate of " + title,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }

}


val ing = listOf(
    "one",
    "two",
    "three"
)


@Preview(showBackground = true)
@Composable
fun SmallRecipeCardPreview() {
    StockPalAppTheme(useDarkTheme = false) {
        SmallRecipeCard(
            title = "test",
            imageUrl = "https://images.unsplash.com/photo-1678051299678-439050e063a3?auto=format&fit=crop&q=80&w=1974&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            missingIngredients = ing
        )
    }
}

