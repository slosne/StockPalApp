package com.example.stockpalapp.domain.model

import com.google.firebase.firestore.DocumentId

data class ShoppingList(
    @DocumentId val id: String = "",
    val name : String = "",
)
