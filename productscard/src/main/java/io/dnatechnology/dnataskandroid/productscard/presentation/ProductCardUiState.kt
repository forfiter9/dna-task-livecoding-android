package io.dnatechnology.dnataskandroid.productscard.presentation

import androidx.compose.runtime.snapshots.SnapshotStateList
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product
import io.dnatechnology.dnataskandroid.productscard.domain.model.BasketProduct

data class ProductCardUiState (
    val products: List<Product>?,
    val basketProducts: SnapshotStateList<BasketProduct>,
    val totalPrice: Double,
    val paymentStatus: PaymentStatus
) {
    companion object {
        val EMPTY =
            ProductCardUiState(
                products = null,
                basketProducts = SnapshotStateList(),
                totalPrice = 0.0,
                paymentStatus = PaymentStatus.None
            )
    }
}