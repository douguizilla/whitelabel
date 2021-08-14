package com.odougle.whitelabel.ui.products

import androidx.lifecycle.ViewModel
import com.odougle.whitelabel.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

}