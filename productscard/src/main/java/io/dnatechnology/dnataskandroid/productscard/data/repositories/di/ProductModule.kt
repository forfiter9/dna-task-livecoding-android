package io.dnatechnology.dnataskandroid.productscard.data.repositories.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.dnatechnology.dnataskandroid.productscard.data.repositories.ProductRepository
import io.dnatechnology.dnataskandroid.productscard.data.repositories.ProductRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductModule {
    @Binds
    @Singleton
    internal abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository
}