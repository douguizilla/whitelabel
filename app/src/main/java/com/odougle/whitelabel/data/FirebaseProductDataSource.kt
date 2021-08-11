package com.odougle.whitelabel.data

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.odougle.whitelabel.BuildConfig
import com.odougle.whitelabel.domain.model.Product
import com.odougle.whitelabel.util.COLLECTION_PRODUCTS
import com.odougle.whitelabel.util.COLLECTION_ROOT
import com.odougle.whitelabel.util.STORAGE_IMAGES
import java.util.*
import kotlin.coroutines.suspendCoroutine

class FirebaseProductDataSource(
    firebaseFirestore: FirebaseFirestore,
    firebaseStorage: FirebaseStorage
) : ProductDataSource {

    //data/car/products/timestamp/productA
    //data/bike/products/timestamp/productB
    //COLLECTION/DOCUMENT/COLLECTION/DOCUMENT/OBJECT

    private val documentReference = firebaseFirestore
        .document("$COLLECTION_ROOT/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}")

    private val storageReference = firebaseStorage.reference

    override suspend fun getProducts(): List<Product> {
        return suspendCoroutine { continuation ->
            val productsReference = documentReference.collection(COLLECTION_PRODUCTS)
            productsReference.get().addOnSuccessListener { documents ->
                val products = mutableListOf<Product>()
                for (document in documents){
                    document.toObject(Product::class.java).run {
                        products.add(this)
                    }
                }
                continuation.resumeWith(Result.success(products))
            }
            productsReference.get().addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
        }
    }

    override suspend fun uploadProductImage(imageUri: Uri): String {
        return suspendCoroutine { continuation ->
            val randomKey = UUID.randomUUID()
            val childReference = storageReference.child(
                "$STORAGE_IMAGES/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}/$randomKey"
            )
        }
    }

    override suspend fun createProduct(product: Product): Product {
        return suspendCoroutine { continuation ->

        }
    }

}