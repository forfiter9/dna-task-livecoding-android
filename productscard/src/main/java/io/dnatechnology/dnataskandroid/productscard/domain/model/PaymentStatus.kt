package io.dnatechnology.dnataskandroid.productscard.domain.model

data class PaymentResults (
    val status: Status
)

enum class Status {
    SUCCESS,
    FAILED
}