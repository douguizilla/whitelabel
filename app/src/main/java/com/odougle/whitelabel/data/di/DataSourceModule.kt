package com.odougle.whitelabel.data.di

import com.odougle.whitelabel.data.FirebaseProductDataSource
import com.odougle.whitelabel.data.ProductDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindProductDataSource(dataSource: FirebaseProductDataSource) : ProductDataSource
}