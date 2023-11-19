package com.example.stockpalapp.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class PantryProduct(
    @DocumentId val id: String = "",
    override val name: String = "",
    override val number: Int = 0,
    val eanNumber: Double = 0.0,
    val category: String = "",
    val image: String = "",
    val expDate: Timestamp? = null,
) : IProduct
