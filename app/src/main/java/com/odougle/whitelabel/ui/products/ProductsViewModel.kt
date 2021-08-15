package com.odougle.whitelabel.ui.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odougle.whitelabel.config.Config
import com.odougle.whitelabel.domain.model.Product
import com.odougle.whitelabel.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val config: Config
) : ViewModel() {

    private val _productData = MutableLiveData<List<Product>>()
    val productsData: LiveData<List<Product>> = _productData

    private val _addButtonVisibilityData = MutableLiveData(config.addButtonVisibility)
    val addButtonVisibilityData: LiveData<Int> = _addButtonVisibilityData

    fun getProducts() = viewModelScope.launch {
        try {
            val products = getProductsUseCase()
            _productData.value = products
        }catch (e: Exception){
            Log.d("ProductsViewModel", e.toString())
        }
    }
}