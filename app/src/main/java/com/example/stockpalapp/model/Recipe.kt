package com.example.stockpalapp.model

import com.google.firebase.firestore.DocumentId

data class Recipe(
    @DocumentId val id: String = "",
    val image : String = "",
    val title : String = "",
    val ingredients: List<String> = emptyList(),
    val cookingTime : String = "",
    val cuisine: String = "",
    val servings: Int = 0,
    val instructions: List<String> = emptyList()
)