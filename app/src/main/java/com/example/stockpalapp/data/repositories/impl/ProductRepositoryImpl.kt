package com.example.stockpalapp.data.repositories.impl

import com.example.stockpalapp.data.repositories.ProductRepository
import com.example.stockpalapp.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProductRepository {

    override val product: Flow<List<Product>>
        get() = firestore.collection("Products").dataObjects()

    override suspend fun getProduct(itemID: String): Product? =
        firestore.collection("Products").document(itemID).get().await().toObject()

    override suspend fun getProductByEanNumber(eanNumber: String): Product? {
        val querySnapshot = firestore.collection("products")
            .whereEqualTo("eanNumber", eanNumber)
            .get()
            .await()

        val document = querySnapshot.documents.firstOrNull()
        return document?.toObject(Product::class.java)
    }

    override suspend fun saveProduct(item: Product): String =
        firestore.collection("Products").add(item).await().id
}