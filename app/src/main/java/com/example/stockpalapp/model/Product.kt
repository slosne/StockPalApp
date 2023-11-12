package com.example.stockpalapp.model

import com.google.firebase.firestore.DocumentId

data class Product(
    @DocumentId val id: String = "",
    override val name: String = "",
    override val number: Int = 0,
    val eanNumber: String = "",
    val category: String = "",
    val image: String = "",
    val vendor: String = "",
) : IProduct
