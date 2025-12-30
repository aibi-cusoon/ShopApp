package com.geeks.shopapp.domain.usecases

import com.geeks.shopapp.domain.repository.CartRepository

class CheckOutUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(): Result<String> {
        return cartRepository.checkout()
    }
}