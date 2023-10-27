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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@Composable
fun FullRecipeCard(
    title: String,
    imageUrl: String,
    cuisine: String,
    cookingTime: String,
    ingredients: List<String>,
    instructions: List<String>,
    content: @Composable () -> Unit?)
{
    ElevatedCard(
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)){
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
                            style = TextStyle(color = Color.White, fontSize = 28.sp)
                        )

                    }
                }

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Cuisine: " + cuisine, style = TextStyle(fontSize = 12.sp))
                Text(text = "Cooking Time: " + cookingTime, style = TextStyle(fontSize = 12.sp))
                Spacer(modifier = Modifier.size(12.dp))
                Text(text = "Ingredients: ")
                for (ingredient in ingredients) {
                    Text(text = ingredient + ", ", style = TextStyle(fontSize = 12.sp))
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Instructions:")
                for (todo in instructions) {
                    Text(text = todo + ", ", style = TextStyle(fontSize = 12.sp))
                }

            }
        }

    }
}