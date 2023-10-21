package com.example.stockpalapp.data.repositories

import com.example.stockpalapp.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    val recipes: Flow<List<Recipe>>
    suspend fun getRecipe(itemID: String): Recipe?
    suspend fun save(item: Recipe): String
}
