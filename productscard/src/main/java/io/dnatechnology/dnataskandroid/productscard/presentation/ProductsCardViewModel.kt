package io.dnatechnology.dnataskandroid.productscard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.PurchaseApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsCardViewModel @Inject constructor(

): ViewModel() {

    val purchaseApiClient: PurchaseApiClient = PurchaseApiClient()

    private var mutableCart = MutableStateFlow<Map<String, Long>>(mapOf())
    var cart: StateFlow<Map<String, Long>> = mutableCart

    private var _productCardUiState = MutableStateFlow<ProductCardUiState>(ProductCardUiState.EMPTY)
    var productCardUiState: StateFlow<ProductCardUiState> = _productCardUiState

    fun getProducts() {
        viewModelScope.launch {
            _productCardUiState.value = _productCardUiState.value.copy(
                products = purchaseApiClient.getProducts()
            )
        }
    }

    fun addToCart(productID: String) {

        val newMap = mutableCart.value.toMutableMap()
        newMap[productID] = (mutableCart.value[productID] ?: 0L) + 1L

        mutableCart.value = newMap.toMap()
    }
}