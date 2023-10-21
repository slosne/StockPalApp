package com.example.stockpalapp.data.repositories.impl

import com.example.stockpalapp.data.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow
import com.example.stockpalapp.model.Recipe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RecipeRepositoryImpl
@Inject
constructor(private val firestore: FirebaseFirestore) : RecipeRepository {

    override val recipes: Flow<List<Recipe>>
        get() = firestore.collection("recipes").dataObjects()

    override suspend fun getRecipe(movieId: String): Recipe? =
        firestore.collection("recipes").document(movieId).get().await().toObject()


    override suspend fun save(recipe: Recipe): String =
        firestore.collection("recipes").add(recipe).await().id

}