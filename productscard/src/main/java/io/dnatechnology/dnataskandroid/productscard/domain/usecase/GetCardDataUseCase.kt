package io.dnatechnology.dnataskandroid.productscard.domain.usecase

import io.dnatechnology.dnataskandroid.productscard.data.repositories.PaymentRepository
import io.dnatechnology.dnataskandroid.productscard.data.source.cardreader.CardReaderException
import io.dnatechnology.dnataskandroid.productscard.domain.model.CardData
import io.dnatechnology.dnataskandroid.productscard.domain.model.CardStatus
import io.dnatechnology.dnataskandroid.productscard.domain.usecase.converters.toDomainCardData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCardDataUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    suspend fun invoke(): CardData =
        try {
            paymentRepository.getCardToken().toDomainCardData(CardStatus.SUCCESS)
        } catch (e: CardReaderException) {
            CardData("", CardStatus.FAILED)
        }
}