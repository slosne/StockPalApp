package com.example.stockpalapp.model

import com.google.firebase.firestore.DocumentId

data class ShoppingList(
    @DocumentId val id: String = "",
    val name : String = "",
)
