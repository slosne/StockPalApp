package com.example.stockpalapp.model

import com.google.firebase.firestore.DocumentId

data class PantryItem(
    @DocumentId val uid : String = "",
    val ean : Int,
    val date : String = "",
    val image : String = "",
    val name : String = "",
    val vendor : String = "",
)
