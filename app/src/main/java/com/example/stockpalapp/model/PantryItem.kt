package com.example.stockpalapp.model

import com.google.firebase.firestore.DocumentId

data class PantryItem(
    @DocumentId val id: String = "",
    val name : String = "",
    val image : String = "",
    val eannumber: Int = 0,
    val vendor : String = "",
    val cookingTime : String = "",
)
