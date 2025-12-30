package com.geeks.shopapp.domain.usecases

import com.geeks.shopapp.domain.models.Product
import com.geeks.shopapp.domain.repository.CartRepository

class AddToCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(product: Product) {
        cartRepository.addToCart(product)
    }
}