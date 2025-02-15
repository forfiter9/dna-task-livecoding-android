package io.dnatechnology.dnataskandroid.productscard

import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.PaymentDataSource
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.PurchaseDataSource
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PaymentRequest
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PaymentStatus
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PurchaseConfirmRequest
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.PurchaseRequest
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.data.TransactionStatus
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PaymentAPITests {
    private val paymentAPI =
        PaymentDataSource()

    @Test
    fun whenCorrectDataThenSuccess() = runBlocking {
        // given
        val paymentRequest =
            PaymentRequest(
                transactionID = "Tr1",
                amount = 33.66,
                currency = "EUR",
                cardToken = "Token"
            )

        // when
        val paymentResponse =  paymentAPI.pay(paymentRequest)

        // then
        assertEquals(paymentResponse.status, PaymentStatus.SUCCESS)
    }

    @Test
    fun whenIncorrectAmountThenFail() = runBlocking {
        // given
        val paymentRequest =
            PaymentRequest(
                transactionID = "Tr1",
                amount = 19.66,
                currency = "EUR",
                cardToken = "Token"
            )

        // when
        val paymentResponse =  paymentAPI.pay(paymentRequest)

        // then
        assertEquals(paymentResponse.status, PaymentStatus.FAILED)
    }

    @Test
    fun whenIncorrectCurrencyThenFail() = runBlocking {
        // given
        val paymentRequest =
            PaymentRequest(
                transactionID = "Tr1",
                amount = 22.66,
                currency = "PLN",
                cardToken = "Token"
            )

        // when
        val paymentResponse =  paymentAPI.pay(paymentRequest)

        // then
        assertEquals(paymentResponse.status, PaymentStatus.FAILED)
    }

    @Test
    fun whenRevertingCorrectAmountThenSuccess() = runBlocking {
        // given
        val paymentRequest =
            PaymentRequest(
                transactionID = "Tr1",
                amount = 12.66,
                currency = "EUR",
                cardToken = "Token"
            )

        // when
        val paymentResponse =  paymentAPI.revert(paymentRequest)

        // then
        assertEquals(paymentResponse.status, PaymentStatus.SUCCESS)
    }

    @Test
    fun whenRevertingIncorrectAmountThenFail() = runBlocking {
        // given
        val paymentRequest =
            PaymentRequest(
                transactionID = "Tr1",
                amount = 0.66,
                currency = "EUR",
                cardToken = "Token"
            )

        // when
        val paymentResponse =  paymentAPI.revert(paymentRequest)

        // then
        assertEquals(paymentResponse.status, PaymentStatus.FAILED)
    }
}

class PurchaseAPITests {
    private val purchaseApiClient =
        PurchaseDataSource() // TODO mock it with mockk

    @Test
    fun whenGetProductsThenSuccess() = runBlocking {
        // when
        val products =  purchaseApiClient.getProducts()

        // then
        assertEquals(products.size, 5)
    }

    @Test
    fun whenInitiateEmptyOrderThenFail() = runBlocking {
        // given
       val purchaseRequest =
           PurchaseRequest(
               mapOf()
           )

        // when
        val purchaseResponse =  purchaseApiClient.initiatePurchaseTransaction(purchaseRequest)

        // then
        assertEquals(purchaseResponse.transactionStatus, TransactionStatus.FAILED)
    }

    @Test
    fun whenInitiateOrderWithZeroItemsThenFail() = runBlocking {
        // given
        val purchaseRequest =
            PurchaseRequest(
                mapOf("12345" to 0)
            )

        // when
        val purchaseResponse =  purchaseApiClient.initiatePurchaseTransaction(purchaseRequest)

        // then
        assertEquals(purchaseResponse.transactionStatus, TransactionStatus.FAILED)
    }

    @Test
    fun whenInitiateOrderWithToManyItemsThenFail() = runBlocking {
        // given
        val purchaseRequest =
            PurchaseRequest(
                mapOf("12348" to 2001)
            )

        // when
        val purchaseResponse =  purchaseApiClient.initiatePurchaseTransaction(purchaseRequest)

        // then
        assertEquals(purchaseResponse.transactionStatus, TransactionStatus.FAILED)
    }

    @Test
    fun whenConfirmrderWithToManyItemsThenFail() = runBlocking {
        // given
        val purchaseRequest =
            PurchaseConfirmRequest(
                mapOf("12348" to 2001),
                "Tr2"
            )

        // when
        val purchaseResponse =  purchaseApiClient.confirm(purchaseRequest)

        // then
        assertEquals(purchaseResponse.status, TransactionStatus.FAILED)
    }
}