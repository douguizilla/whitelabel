package com.odougle.whitelabel.config.di

import com.odougle.whitelabel.config.Config
import com.odougle.whitelabel.config.ConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ConfigModule {

    @Binds
    fun binConfig(config: ConfigImpl): Config
}