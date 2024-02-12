package io.dnatechnology.dnataskandroid.productscard.domain.usecase

import io.dnatechnology.dnataskandroid.productscard.data.repositories.PaymentRepository
import io.dnatechnology.dnataskandroid.productscard.domain.model.CardData
import io.dnatechnology.dnataskandroid.productscard.domain.model.PaymentResults
import io.dnatechnology.dnataskandroid.productscard.domain.model.TransactionData
import io.dnatechnology.dnataskandroid.productscard.domain.usecase.converters.toDomainPaymentStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakePaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    suspend fun invoke(transactionData: TransactionData, cardData: CardData, currency: String, amount: Double): PaymentResults = paymentRepository.pay(
        transactionID = transactionData.transactionId,
        cardToken = cardData.cardToken,
        currency = currency,
        amount = amount
    ).toDomainPaymentStatus()
}