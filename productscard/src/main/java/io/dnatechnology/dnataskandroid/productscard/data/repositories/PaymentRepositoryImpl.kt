package io.dnatechnology.dnataskandroid.productscard.data.repositories

import io.dnatechnology.dnataskandroid.productscard.data.source.cardreader.CardData
import io.dnatechnology.dnataskandroid.productscard.data.source.cardreader.CardReaderService
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.PaymentDataSource
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.PurchaseDataSource
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PaymentRequest
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PurchaseRequest
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PurchaseResponse
import io.dnatechnology.dnataskandroid.productscard.domain.repositories.PaymentRepository
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentDataSource: PaymentDataSource,
    private val purchaseDataSource: PurchaseDataSource,
    private val cardReaderService: CardReaderService
): PaymentRepository {

    override suspend fun initiateTransaction(order: Map<String, Long>): PurchaseResponse =
        purchaseDataSource.initiatePurchaseTransaction(PurchaseRequest(order = order))

    override suspend fun getCardToken(): CardData =
        cardReaderService.readCard()

    override suspend fun pay(transactionID: String, amount: Double, currency: String, cardToken: String) =
        paymentDataSource.pay(PaymentRequest(transactionID = transactionID, amount = amount, currency = currency, cardToken = cardToken))

}