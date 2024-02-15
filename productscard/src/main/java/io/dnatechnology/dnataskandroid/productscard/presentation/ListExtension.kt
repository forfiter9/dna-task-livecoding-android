package io.dnatechnology.dnataskandroid.productscard.presentation

import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product
import io.dnatechnology.dnataskandroid.productscard.domain.model.BasketProduct

fun MutableList<BasketProduct>.addOrUpdateAmount(product: Product) {
    val existingModel = this.find { it.product.productID == product.productID }
    if (existingModel != null) {
        existingModel.productAmount++
    } else {
        add(BasketProduct(product, 1))
    }
}