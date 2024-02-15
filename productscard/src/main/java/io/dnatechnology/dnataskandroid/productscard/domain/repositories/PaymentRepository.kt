package io.dnatechnology.dnataskandroid.productscard.domain.repositories

import io.dnatechnology.dnataskandroid.productscard.data.source.cardreader.CardData
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PaymentResponse
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PurchaseResponse

interface PaymentRepository {
    suspend fun initiateTransaction(order: Map<String, Long>): PurchaseResponse
    suspend fun getCardToken(): CardData
    suspend fun pay(transactionID: String, amount: Double, currency: String, cardToken: String): PaymentResponse
}