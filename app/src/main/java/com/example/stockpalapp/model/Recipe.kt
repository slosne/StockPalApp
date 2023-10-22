package com.example.stockpalapp.model

import com.google.firebase.firestore.DocumentId

data class Recipe(
    @DocumentId val id: String = "",
    val cookingTime : String = "",
    val cuisine: String = "",
    val image : String = "",
    val ingredients: List<String> = emptyList(),
    val instructions: List<String> = emptyList(),
    val servings: Int = 0,
    val title : String = ""
)