package com.odougle.whitelabel.ui.products.addproduct

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odougle.whitelabel.domain.usecase.CreateProductUseCase
import kotlinx.coroutines.launch

class AddProductViewModel(
    private val createProductUseCase: CreateProductUseCase
) : ViewModel() {


}