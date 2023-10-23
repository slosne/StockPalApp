package com.example.stockpalapp.model

import com.google.firebase.firestore.DocumentId

data class ShoppingListProduct(
    @DocumentId val id: String = "",
    override val name: String = "",
    override val number: Int = 0,
) : IProduct
