package io.dnatechnology.dnataskandroid.productscard.presentation

import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product

data class ProductCardUiState (
    val products: List<Product>?
) {
    companion object {
        val EMPTY =
            ProductCardUiState(
                products = null,
            )
    }
}