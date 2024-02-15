package io.dnatechnology.dnataskandroid.productscard.data.repositories

import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.PurchaseDataSource
import io.dnatechnology.dnataskandroid.productscard.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val purchaseDataSource: PurchaseDataSource
): ProductRepository {
    override suspend fun getProducts(): List<Product> = purchaseDataSource.getProducts()
}