package io.dnatechnology.dnataskandroid.productscard.presentation

sealed class PaymentStatus {
    object CardReaderFailed: PaymentStatus()
    object PaymentFailed: PaymentStatus()
    object PaymentSuccess: PaymentStatus()
    object None: PaymentStatus()
    object IsProcessing: PaymentStatus()
}
