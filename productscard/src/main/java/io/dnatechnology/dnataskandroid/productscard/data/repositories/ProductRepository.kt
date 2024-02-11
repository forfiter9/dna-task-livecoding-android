package io.dnatechnology.dnataskandroid.productscard.data.repositories

import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}