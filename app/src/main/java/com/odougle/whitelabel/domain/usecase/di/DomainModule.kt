package com.odougle.whitelabel.domain.usecase.di

import com.odougle.whitelabel.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindCreateProductUseCase(userCase: CreateProductUseCaseImpl): CreateProductUseCase

    @Binds
    fun bindGetProductsUseCase(userCase: GetProductsUseCaseImpl): GetProductsUseCase

    @Binds
    fun bindUploadProductUseCase(userCase: UploadProductImageUseCaseImpl): UploadProductImageUseCase

}