package com.odougle.whitelabel.ui.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odougle.whitelabel.domain.model.Product
import com.odougle.whitelabel.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _productData = MutableLiveData<List<Product>>()
    val productsData: LiveData<List<Product>> = _productData

    fun getProducts() = viewModelScope.launch {
        try {
            val products = getProductsUseCase()
            _productData.value = products
        }catch (e: Exception){
            Log.d("ProductsViewModel", e.toString())
        }
    }
}