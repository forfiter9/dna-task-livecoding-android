package io.dnatechnology.dnataskandroid.productscard.data.repositories.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.dnatechnology.dnataskandroid.productscard.domain.repositories.PaymentRepository
import io.dnatechnology.dnataskandroid.productscard.data.repositories.PaymentRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PaymentModule {
    @Binds
    @Singleton
    internal abstract fun bindPaymentRepository(
        paymentRepositoryImpl: PaymentRepositoryImpl
    ): PaymentRepository
}