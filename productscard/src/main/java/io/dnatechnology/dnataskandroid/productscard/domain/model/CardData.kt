package io.dnatechnology.dnataskandroid.productscard.domain.model

data class CardData (
    val cardToken: String,
    val readStatus: CardStatus
)

enum class CardStatus {
    SUCCESS, FAILED
}