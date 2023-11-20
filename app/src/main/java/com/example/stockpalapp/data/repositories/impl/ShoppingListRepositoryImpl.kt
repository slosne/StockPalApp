package com.example.stockpalapp.data.repositories.impl

import com.example.stockpalapp.data.repositories.AuthRepository
import com.example.stockpalapp.data.repositories.ShoppingListRepository
import com.example.stockpalapp.model.Product
import com.example.stockpalapp.model.ShoppingList
import com.example.stockpalapp.model.ShoppingListProduct
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ShoppingListRepositoryImpl
@Inject
constructor(
    private val firestore: FirebaseFirestore,
    private val authRepositoryImpl: AuthRepository
) : ShoppingListRepository {

    override val shoppingListProduct: Flow<List<ShoppingListProduct>>
        get() = firestore.collection(SHOPPINGLIST_COLLECTION).document(authRepositoryImpl.currentUserId).collection(
            SHOPPINGLIST_COLLECTION_PRODUCTS).dataObjects()

    override suspend fun getShoppingList(itemID: String): ShoppingList? =
        firestore.collection(SHOPPINGLIST_COLLECTION).document(itemID).get().await().toObject()

    override suspend fun saveShoppingList(item: ShoppingList, itemID: String): String {
        firestore.collection(SHOPPINGLIST_COLLECTION).document(itemID).set(item).await()
        return itemID
    }

    override suspend fun saveShoppingListProduct(item: ShoppingListProduct): String =
        firestore.collection(SHOPPINGLIST_COLLECTION).document(authRepositoryImpl.currentUserId).collection(
            SHOPPINGLIST_COLLECTION_PRODUCTS).add(item).await().id

    override suspend fun saveMultipleShoppingListProducts(items: List<Product>): String {
        val batch = firestore.batch()
        val productIds = mutableListOf<String>()

        val userId = authRepositoryImpl.currentUserId
        val collectionReference = firestore.collection(SHOPPINGLIST_COLLECTION)
            .document(userId)
            .collection(SHOPPINGLIST_COLLECTION_PRODUCTS)

        for (item in items) {
            val documentReference = collectionReference.document()
            batch.set(documentReference, item)
            productIds.add(documentReference.id)
        }

        batch.commit().await()

        return productIds.toString()
    }

    override suspend fun deleteShoppingListProduct(itemID: String): String {
        firestore.collection(SHOPPINGLIST_COLLECTION).document(authRepositoryImpl.currentUserId).collection(
            SHOPPINGLIST_COLLECTION_PRODUCTS).document(itemID).delete()
        return itemID
    }

    companion object {
        private const val SHOPPINGLIST_COLLECTION = "shoppinglist"
        private const val SHOPPINGLIST_COLLECTION_PRODUCTS = "shoppinglistproducts"
    }
}