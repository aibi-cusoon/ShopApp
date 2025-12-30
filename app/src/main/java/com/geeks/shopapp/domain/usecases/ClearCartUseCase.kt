package com.geeks.shopapp.domain.usecases

import com.geeks.shopapp.domain.repository.CartRepository

class ClearCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(){
        cartRepository.clearCart()
    }
}