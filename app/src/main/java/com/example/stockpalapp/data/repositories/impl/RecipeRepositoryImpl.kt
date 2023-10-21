package com.example.stockpalapp.data.repositories.impl

import com.example.stockpalapp.data.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow
import com.example.stockpalapp.model.Recipe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RecipeRepositoryImpl
@Inject
constructor(private val firestore: FirebaseFirestore) : RecipeRepository {

    override val recipes: Flow<List<Recipe>>
        get() = firestore.collection(RECIPE_COLLECTION).dataObjects()

    override suspend fun getRecipe(itemID: String): Recipe? =
        firestore.collection(RECIPE_COLLECTION).document(itemID).get().await().toObject()

    override suspend fun postRecipe(item: Recipe): String =
        firestore.collection(RECIPE_COLLECTION).add(item).await().id

    companion object {
        private const val RECIPE_COLLECTION = "recipes"
    }
}