package com.example.stockpalapp.data.repositories.impl

import com.example.stockpalapp.data.repositories.AuthRepository
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.model.Pantry
import com.example.stockpalapp.model.PantryProduct
import com.example.stockpalapp.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PantryRepositoryImpl
@Inject
constructor(
    private val firestore: FirebaseFirestore,
    private val authRepositoryImpl: AuthRepository
) : PantryRepository {

    override val pantryProduct: Flow<List<PantryProduct>>
        get() = firestore.collection(PANTRY_COLLECTION).document(authRepositoryImpl.currentUserId).collection("pantryproducts").dataObjects()

    override val pantry: Flow<List<Pantry>>
        get() = firestore.collection(PANTRY_COLLECTION).dataObjects()

    override suspend fun getPantryItem(itemID: String): Pantry? =
        firestore.collection(PANTRY_COLLECTION).document(itemID).get().await().toObject()

    override suspend fun savePantry(item: Pantry, itemID: String): String {
        firestore.collection(PANTRY_COLLECTION).document(itemID).set(item).await()
        return itemID
    }

    override suspend fun savePantryProduct(item: PantryProduct): String =
        firestore.collection(PANTRY_COLLECTION).document(authRepositoryImpl.currentUserId).collection("pantryproducts").add(item).await().id

    override suspend fun saveMultiplePantryProducts(items: List<Product>): String {
        val batch = firestore.batch()
        val productIds = mutableListOf<String>()

        val userId = authRepositoryImpl.currentUserId
        val collectionReference = firestore.collection(PANTRY_COLLECTION)
            .document(userId)
            .collection("pantryproducts")

        for (item in items) {
            val documentReference = collectionReference.document()
            batch.set(documentReference, item)
            productIds.add(documentReference.id)
        }

        batch.commit().await()

        return productIds.toString()
    }

    override suspend fun deletePantryProduct(itemID: String): String {
        firestore.collection(PANTRY_COLLECTION).document(authRepositoryImpl.currentUserId).collection("pantryproducts").document(itemID).delete()
        return itemID
    }

    companion object {
        private const val PANTRY_COLLECTION = "pantry"
    }
}