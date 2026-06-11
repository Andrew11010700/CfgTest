package com.cfg.testtask.di.repository

import com.cfg.testtask.data.local.ProductDao
import com.cfg.testtask.data.remote.ProductApi
import com.cfg.testtask.data.repository.ProductRepositoryImpl
import com.cfg.testtask.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        api: ProductApi,
        dao: ProductDao
    ): ProductRepository {
        return ProductRepositoryImpl(api, dao)
    }

}