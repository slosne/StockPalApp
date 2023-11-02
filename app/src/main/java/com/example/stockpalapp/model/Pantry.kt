package com.example.stockpalapp.model

import com.google.firebase.firestore.DocumentId

data class Pantry(
    @DocumentId val id: String = "",
    val name : String = "",
)
