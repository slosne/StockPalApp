package com.example.stockpalapp.model

import com.google.firebase.firestore.DocumentId

data class PantryProduct(
    @DocumentId val id: String = "",
    override val name: String = "",
    override val number: Int = 0,
    val eanNumber: Int = 0,
    val category: String = "",
    val image: String = "",
    val expDate: String = "",
) : IProduct
