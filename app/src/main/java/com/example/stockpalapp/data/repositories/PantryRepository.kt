package com.example.stockpalapp.data.repositories

import com.example.stockpalapp.model.Pantry
import com.example.stockpalapp.model.PantryProduct
import com.google.firebase.firestore.DocumentId
import kotlinx.coroutines.flow.Flow

interface PantryRepository {
    val pantry: Flow<List<Pantry>>

    val pantryProduct: Flow<List<PantryProduct>>

    suspend fun getPantryProducts(documentId: String): Flow<List<PantryProduct>>
    suspend fun getPantryItem(itemID: String): Pantry?
    suspend fun save(item: Pantry): String
    suspend fun savePantryProduct(item: PantryProduct): String
}