package io.dnatechnology.dnataskandroid.productscard.domain.model

import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.TransactionStatus

data class TransactionData(
    val transactionId: String,
    val transactionStatus: TransactionStatus
)
