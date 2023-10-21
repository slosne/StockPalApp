package com.example.stockpalapp.model

import com.google.firebase.firestore.DocumentId

data class PantryItem(
    @DocumentId val uid : String = "",
    val date : String = "",
    val ean : Int,
    val image : String = "",
    val name : String = "",
    val vendor : String = "",
)
