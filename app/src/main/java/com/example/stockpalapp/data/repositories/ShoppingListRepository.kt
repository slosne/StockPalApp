package com.example.stockpalapp.data.repositories

import com.example.stockpalapp.model.Product
import com.example.stockpalapp.model.ShoppingList
import com.example.stockpalapp.model.ShoppingListProduct
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {
    val shoppingListProduct: Flow<List<ShoppingListProduct>>
    suspend fun getShoppingList(itemID: String): ShoppingList?
    suspend fun saveShoppingList(item: ShoppingList, itemID: String): String
    suspend fun saveShoppingListProduct(item: ShoppingListProduct): String
    suspend fun saveMultipleShoppingListProducts(item: List<Product>): String
    suspend fun deleteShoppingListProduct(itemID: String): String
}