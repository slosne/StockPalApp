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
        get() = firestore.collection("pantry").dataObjects()

    override suspend fun getPantryItem(itemID: String): PantryItem? =
        firestore.collection("pantry").document(itemID).get().await().toObject()

    override suspend fun save(item: PantryItem): String =
        firestore.collection("pantry").add(item).await().id

}