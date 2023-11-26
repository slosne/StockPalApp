package com.example.stockpalapp.domain.model.product

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class PantryProduct(
    @DocumentId val id: String = "",
    override val name: String = "",
    override var number: Int = 0,
    val eanNumber: Long = 0,
    val category: String = "",
    val image: String = "",
    val expDate: Timestamp? = null,
) : IProduct
