package com.odougle.whitelabel.ui.products.addproduct

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odougle.whitelabel.R
import com.odougle.whitelabel.domain.usecase.CreateProductUseCase
import com.odougle.whitelabel.util.fromCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val createProductUseCase: CreateProductUseCase
) : ViewModel() {

    private val _descriptionFieldErrorResId = MutableLiveData<Int?>()
    val descriptionFieldErrorResId: LiveData<Int?> = _descriptionFieldErrorResId

    private val _priceFieldErrorResId = MutableLiveData<Int?>()
    val priceFieldErrorResId: LiveData<Int?> = _priceFieldErrorResId

    private val _imageErrorResId = MutableLiveData<Int>()
    val imageErrorResId: LiveData<Int> = _imageErrorResId

    private var isFormValid = false


    //para poder usar funcoes suspensas dentro do escopo usei viewModelScope.launch
    fun createProduct(description: String, price: String, imageUri: Uri?) = viewModelScope.launch {
        isFormValid = true

        //validation
        _imageErrorResId.value = getDrawableResIdIfNull(imageUri)
        _descriptionFieldErrorResId.value = getErrorStringResIdIfEmpty(description)
        _priceFieldErrorResId.value = getErrorStringResIdIfEmpty(price)

        if (isFormValid){
            try {
                val product = createProductUseCase(description, price.fromCurrency(), imageUri!!)
            }catch (e: Exception){
                Log.d("CreateProduct", e.toString())
            }
        }
    }

    private fun getErrorStringResIdIfEmpty(value: String): Int? {
        return if(value.isEmpty()){
            isFormValid = false
            R.string.add_product_field_error
        }else null
    }

    private fun getDrawableResIdIfNull(value: Uri?): Int{
        return if(value == null){
            isFormValid = false
            R.drawable.background_product_image_error
        }else R.drawable.background_product_image
    }
}