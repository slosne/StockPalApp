package com.example.stockpalapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.stockpalapp.ui.theme.StockPalAppTheme

@Composable
fun SmallRecipeCard(
    title: String,
    imageUrl: String,
    missingIngredients: List<String>)
{
    ElevatedCard(
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(10.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)){
                val imagePainter = rememberImagePainter(data = imageUrl)
                Image(
                    painter = imagePainter,
                    contentDescription = "plate of " + title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp), contentAlignment = Alignment.TopCenter){
                    Column {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(color = Color.White, fontSize = 26.sp)
                        )

                    }
                }

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Du mangler: ", style = TextStyle(fontSize = 16.sp))
                for (ingredient in missingIngredients) {
                    Text(text = ingredient + ", ", style = TextStyle(fontSize = 12.sp))
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
    StockPalAppTheme {
        SmallRecipeCard(
            title = "test",
            imageUrl = "https://images.unsplash.com/photo-1678051299678-439050e063a3?auto=format&fit=crop&q=80&w=1974&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            missingIngredients = ing
        )
    }
}