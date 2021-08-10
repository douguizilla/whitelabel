package com.odougle.whitelabel.data

import android.net.Uri
import com.odougle.whitelabel.domain.model.Product

class ProductRepository(
    private val dataSource: ProductDataSource
) {
    suspend fun getProducts(): List<Product> = dataSource.getProducts()

    suspend fun uploadProductImage(imageUri: Uri): String =
        dataSource.uploadProductImage(imageUri)

    suspend fun createProduct(product: Product): Product =
        dataSource.createProduct(product)
}