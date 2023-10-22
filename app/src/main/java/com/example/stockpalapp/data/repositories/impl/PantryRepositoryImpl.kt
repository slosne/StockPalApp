package com.example.stockpalapp.data.repositories.impl

import com.example.stockpalapp.data.repositories.PantryRepository
import kotlinx.coroutines.flow.Flow
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.dataObjects
import com.example.stockpalapp.model.PantryItem
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PantryRepositoryImpl
@Inject
constructor(private val firestore: FirebaseFirestore) : PantryRepository {

    override val pantry: Flow<List<PantryItem>>
        get() = firestore.collection(PANTRY_COLLECTION).dataObjects()

    override suspend fun getPantryItem(itemID: String): PantryItem? =
        firestore.collection(PANTRY_COLLECTION).document(itemID).get().await().toObject()

    override suspend fun save(item: PantryItem): String =
        firestore.collection(PANTRY_COLLECTION).add(item).await().id

    companion object {
        private const val PANTRY_COLLECTION = "pantry"
    }
}