package com.sanvi.ecommercetarget.screen.utils.Firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.sanvi.ecommercetarget.screen.home.Model.Category
import com.sanvi.ecommercetarget.screen.home.Model.Product
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseStoreRepository @Inject constructor(
    private val firestore : FirebaseFirestore
){

    // Flow :- it is cold stream of asynchronous data in kotlin -- it nly start running when collected (live updates)
    fun getCategoriesFlow(): Flow<List<Category>> = callbackFlow {

        val listenerRegistration = firestore
            .collection("categories")
            .addSnapshotListener { snapshot, error ->

                if (error != null){
                    close(error)

                    print("error fetching categories firestore ==> ${error.message}")
                    return@addSnapshotListener
                }

                if (snapshot != null){
                    val categories = snapshot.toObjects(Category::class.java)
                    trySend(categories)
                }

            }

        // close the flow when the listener is no longer needed
        awaitClose {
            listenerRegistration.remove()
        }

    }

    suspend fun getProductsByCategory(categoryId : String): List<Product>{

      return try {
        // Where result will contain a querysnapshot of all document in "products" where "categoryId" matches the provided value
         val result = firestore.collection("Products")
            .whereEqualTo("categoryId",categoryId)
            .get()
            .await()

         result.toObjects(Product::class.java).also {
            Log.v("TAGY","mapped product : $it")
         }

       }catch (e : Exception){
        emptyList()

      }

    }

    suspend fun getProductById(productId: String): Product? {

        return try {
            val result = firestore.collection("Products")
                .document(productId)
                .get()
                .await()

            result.toObject()

        }catch (e: Exception){
            null
        }

    }

    suspend fun getAllProductsInFireStore(): List<Product>{

        return try {
            val allProduct = firestore.collection("Products")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Product::class.java) }

            allProduct

        }catch (e: Exception){
            emptyList()
        }

    }



}