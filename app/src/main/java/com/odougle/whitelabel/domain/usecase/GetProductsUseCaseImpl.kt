package com.odougle.whitelabel.domain.usecase

import com.odougle.whitelabel.data.ProductRepository
import com.odougle.whitelabel.domain.model.Product

class GetProductsUseCaseImpl(
    private val productRepository: ProductRepository
) : GetProductsUseCase{
    override suspend fun invoke(): List<Product> {
        return productRepository.getProducts()
    }

}