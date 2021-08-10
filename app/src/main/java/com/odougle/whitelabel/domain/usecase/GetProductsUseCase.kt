package com.odougle.whitelabel.domain.usecase

import android.net.Uri
import com.odougle.whitelabel.domain.model.Product

interface GetProductsUseCase {

    suspend operator fun invoke(): List<Product>
}