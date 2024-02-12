package io.dnatechnology.dnataskandroid.productscard.domain.model

import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product

data class BasketProduct (
    val product: Product,
    var productAmount: Int,
)