package io.dnatechnology.dnataskandroid.productscard.presentation

import android.content.Context
import android.widget.Toast
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

fun Context.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}