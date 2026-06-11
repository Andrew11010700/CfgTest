package com.cfg.testtask.di.utils

import com.cfg.testtask.utils.resource.AndroidResourceProvider
import com.cfg.testtask.utils.resource.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceModule {

    @Binds
    @Singleton
    abstract fun bindResourceProvider(
        impl: AndroidResourceProvider
    ): ResourceProvider

}