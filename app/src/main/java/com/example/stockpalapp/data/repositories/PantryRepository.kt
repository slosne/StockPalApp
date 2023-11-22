package com.example.stockpalapp.data.repositories

import com.example.stockpalapp.model.Pantry
import com.example.stockpalapp.model.PantryProduct
import com.example.stockpalapp.model.Product
import kotlinx.coroutines.flow.Flow

interface PantryRepository {
    val pantry: Flow<List<Pantry>>

    val pantryProduct: Flow<List<PantryProduct>>

    suspend fun getPantryItem(itemID: String): Pantry?
    suspend fun savePantry(item: Pantry, itemID: String): String
    suspend fun savePantryProduct(item: PantryProduct): String
    suspend fun saveMultiplePantryProducts(items: List<Product>): String
    suspend fun deletePantryProduct(itemID: String): String
    suspend fun updatePantryProduct(itemID: String, updatedProduct: PantryProduct): String
}