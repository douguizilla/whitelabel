package com.odougle.whitelabel.ui.products.addproduct

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odougle.whitelabel.domain.usecase.CreateProductUseCase
import com.odougle.whitelabel.util.fromCurrency
import kotlinx.coroutines.launch
import kotlin.Exception

class AddProductViewModel(
    private val createProductUseCase: CreateProductUseCase
) : ViewModel() {

    private var isFormValid = false

    private val _descriptionFieldErrorResId = MutableLiveData<Int?>()
    val descriptionFieldErrorResId: LiveData<Int?> = _descriptionFieldErrorResId

    private val _priceFieldErrorResId = MutableLiveData<Int?>()
    val priceFieldErrorResId: LiveData<Int?> = _priceFieldErrorResId

    private val _imageErrorResId = MutableLiveData<Int?>()
    val imageErrorResId: LiveData<Int?> = _imageErrorResId


    //para poder usar funcoes suspensas dentro do escopo usei viewModelScope.launch
    fun createProduct(description: String, price: String, imageUri: Uri) = viewModelScope.launch {
        isFormValid = true



        if (isFormValid){
            try {
                val product = createProductUseCase(description, price.fromCurrency(), imageUri)
            }catch (e: Exception){
                Log.d("CreateProduct", e.toString())
            }
        }
    }
}