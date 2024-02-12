package io.dnatechnology.dnataskandroid.productscard

import io.dnatechnology.dnataskandroid.productscard.data.repositories.PaymentRepository
import io.dnatechnology.dnataskandroid.productscard.data.source.cardreader.CardReaderException
import io.dnatechnology.dnataskandroid.productscard.domain.model.CardData
import io.dnatechnology.dnataskandroid.productscard.domain.model.CardStatus
import io.dnatechnology.dnataskandroid.productscard.domain.usecase.GetCardDataUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCardDataUseCaseTest {

    private val mockPaymentRepository: PaymentRepository = mockk()

    private val systemUnderTest = GetCardDataUseCase(
        paymentRepository = mockPaymentRepository
    )

    @Test
    fun `when card reader failed use case return failed status`() = runTest {
        coEvery { mockPaymentRepository.getCardToken() } throws CardReaderException()
        val actual = systemUnderTest.invoke()
        actual shouldBe CardData("", CardStatus.FAILED)
    }
}