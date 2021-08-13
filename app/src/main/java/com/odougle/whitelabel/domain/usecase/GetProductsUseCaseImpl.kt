package com.odougle.whitelabel.domain.usecase

import com.odougle.whitelabel.data.ProductRepository
import com.odougle.whitelabel.domain.model.Product
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetProductsUseCase{
    override suspend fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}