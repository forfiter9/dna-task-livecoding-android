package io.dnatechnology.dnataskandroid.productscard.presentation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product
import io.dnatechnology.dnataskandroid.productscard.domain.model.BasketProduct
import io.dnatechnology.dnataskandroid.productscard.domain.model.CardStatus
import io.dnatechnology.dnataskandroid.productscard.domain.model.Status
import io.dnatechnology.dnataskandroid.productscard.domain.usecase.GetCardDataUseCase
import io.dnatechnology.dnataskandroid.productscard.domain.usecase.GetProductsUseCase
import io.dnatechnology.dnataskandroid.productscard.domain.usecase.InitiateTransactionUseCase
import io.dnatechnology.dnataskandroid.productscard.domain.usecase.MakePaymentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsCardViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val initiateTransactionUseCase: InitiateTransactionUseCase,
    private val getCardDataUseCase: GetCardDataUseCase,
    private val paymentUseCase: MakePaymentUseCase
) : ViewModel() {
    private val _productCardUiState = MutableStateFlow(ProductCardUiState.EMPTY)
    val productCardUiState: StateFlow<ProductCardUiState> = _productCardUiState

    fun getProducts() {
        viewModelScope.launch {
            _productCardUiState.value = _productCardUiState.value.copy(
                products = getProductsUseCase.invoke()
            )
        }
    }

    fun addToCart(product: Product) {
        val basketProducts = _productCardUiState.value.basketProducts.toMutableStateList()
        val totalPrice = _productCardUiState.value.totalPrice
        basketProducts.addOrUpdateAmount(product)

        viewModelScope.launch(Dispatchers.Main) {
            _productCardUiState.value = _productCardUiState.value.copy(
                basketProducts = basketProducts,
                totalPrice = totalPrice + product.unitNetValue
            )
        }
    }

    fun pay(basketProducts: SnapshotStateList<BasketProduct>, totalPrice: Double) {
        viewModelScope.launch {
            _productCardUiState.value = _productCardUiState.value.copy(
                paymentStatus = PaymentStatus.IsProcessing,
            )
            val cardData = async { getCardDataUseCase.invoke() }.await()
            val transactionData =
                async { initiateTransactionUseCase.invoke(basketProducts.toList()) }.await()
            val paymentStatus = if (cardData.readStatus == CardStatus.SUCCESS) {
                val paymentResult =
                    paymentUseCase.invoke(transactionData, cardData, basketProducts.first().product.unitValueCurrency, totalPrice)
                if (paymentResult.status == Status.SUCCESS) {
                    PaymentStatus.PaymentSuccess
                } else {
                    PaymentStatus.PaymentFailed
                }
            } else {
                PaymentStatus.CardReaderFailed
            }
            _productCardUiState.value = _productCardUiState.value.copy(
                paymentStatus = paymentStatus,
            )
        }
    }

    fun resetPaymentStatus() {
        _productCardUiState.value = _productCardUiState.value.copy(
            paymentStatus = PaymentStatus.None,
        )
    }
}