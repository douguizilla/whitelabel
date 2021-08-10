package com.odougle.whitelabel.domain.usecase

import com.odougle.whitelabel.domain.model.Product

interface GetProductsUseCase {

    suspend operator fun invoke(): List<Product>
}