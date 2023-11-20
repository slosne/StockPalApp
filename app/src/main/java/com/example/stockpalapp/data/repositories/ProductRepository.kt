package com.example.stockpalapp.data.repositories

import com.example.stockpalapp.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val product: Flow<List<Product>>

    suspend fun getProduct(itemID: String): Product?
    suspend fun getProductByEanNumber(eanNumber: Long): Product?
    suspend fun saveProduct(item: Product): String
}