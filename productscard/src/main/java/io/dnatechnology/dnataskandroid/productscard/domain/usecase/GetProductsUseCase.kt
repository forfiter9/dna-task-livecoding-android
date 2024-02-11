package io.dnatechnology.dnataskandroid.productscard.domain.usecase

import io.dnatechnology.dnataskandroid.productscard.data.repositories.ProductRepository
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun invoke(): List<Product> = productRepository.getProducts()
}