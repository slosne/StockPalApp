package com.example.stockpalapp.data.repositories

import com.example.stockpalapp.model.PantryItem
import kotlinx.coroutines.flow.Flow

interface PantryRepository {
    val pantry: Flow<List<PantryItem>>
    suspend fun getPantryItem(itemID: String): PantryItem?
    suspend fun save(item: PantryItem): String
}

