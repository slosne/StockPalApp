package com.example.stockpalapp.data.repositories.impl

import com.example.stockpalapp.data.repositories.PantryRepository
import kotlinx.coroutines.flow.Flow
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.dataObjects
import com.example.stockpalapp.model.Pantry
import com.example.stockpalapp.model.PantryProduct
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PantryRepositoryImpl
@Inject
constructor(private val firestore: FirebaseFirestore) : PantryRepository {

    override val pantryProduct: Flow<List<PantryProduct>>
        get() = firestore.collection(PANTRY_COLLECTION).document("55wGgQdBnqyVnA8cIc08").collection("pantryproducts").dataObjects()

    override val pantry: Flow<List<Pantry>>
        get() = firestore.collection(PANTRY_COLLECTION).dataObjects()

    override suspend fun getPantryProducts(documentId: String): Flow<List<PantryProduct>> {
        val pantryProductsCollection = firestore.collection(PANTRY_COLLECTION)
            .document(documentId)
            .collection("pantryproducts")

        return pantryProductsCollection.dataObjects()
    }

    override suspend fun getPantryItem(itemID: String): Pantry? =
        firestore.collection(PANTRY_COLLECTION).document(itemID).get().await().toObject()

    override suspend fun save(item: Pantry): String =
        firestore.collection(PANTRY_COLLECTION).document("55wGgQdBnqyVnA8cIc08").collection("pantryproducts").add(item).await().id

    override suspend fun savePantryProduct(item: PantryProduct): String =
        firestore.collection(PANTRY_COLLECTION).document("55wGgQdBnqyVnA8cIc08").collection("pantryproducts").add(item).await().id

    companion object {
        private const val PANTRY_COLLECTION = "pantry"
    }
}