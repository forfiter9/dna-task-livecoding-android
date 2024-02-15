package io.dnatechnology.dnataskandroid.productscard.domain.usecase

import io.dnatechnology.dnataskandroid.productscard.domain.repositories.PaymentRepository
import io.dnatechnology.dnataskandroid.productscard.domain.model.BasketProduct
import io.dnatechnology.dnataskandroid.productscard.domain.model.TransactionData
import io.dnatechnology.dnataskandroid.productscard.data.converters.toDomainTransactionData
import io.dnatechnology.dnataskandroid.productscard.data.converters.toOrderMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InitiateTransactionUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    suspend fun invoke(basketProducts: List<BasketProduct>): TransactionData =
        paymentRepository.initiateTransaction(basketProducts.toOrderMap()).toDomainTransactionData()
}