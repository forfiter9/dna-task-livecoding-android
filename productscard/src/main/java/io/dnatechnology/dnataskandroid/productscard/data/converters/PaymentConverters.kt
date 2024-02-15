package io.dnatechnology.dnataskandroid.productscard.data.converters

import io.dnatechnology.dnataskandroid.productscard.data.source.cardreader.CardData as DataCardData
import io.dnatechnology.dnataskandroid.productscard.domain.model.CardData as DomainCardData
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PaymentResponse
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PaymentStatus
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PurchaseResponse
import io.dnatechnology.dnataskandroid.productscard.domain.model.BasketProduct
import io.dnatechnology.dnataskandroid.productscard.domain.model.CardStatus
import io.dnatechnology.dnataskandroid.productscard.domain.model.PaymentResults
import io.dnatechnology.dnataskandroid.productscard.domain.model.Status
import io.dnatechnology.dnataskandroid.productscard.domain.model.TransactionData

fun List<BasketProduct>.toOrderMap() = this.associate {
    it.product.productID to it.productAmount.toLong()
}

fun PurchaseResponse.toDomainTransactionData() = TransactionData(
    transactionId = this.transactionID,
    transactionStatus = this.transactionStatus
)

fun DataCardData.toDomainCardData(readStatus: CardStatus) = DomainCardData(
    cardToken = this.token,
    readStatus = readStatus
)

fun PaymentResponse.toDomainPaymentStatus() =
    PaymentResults(
        status = if (this.status == PaymentStatus.SUCCESS) Status.SUCCESS else Status.FAILED
    )