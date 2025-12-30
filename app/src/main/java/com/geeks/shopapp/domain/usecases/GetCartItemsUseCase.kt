package com.geeks.shopapp.domain.usecases

import com.geeks.shopapp.domain.models.CartItem
import com.geeks.shopapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartItemsUseCase(
    private val cartRepository: CartRepository
) {
    operator fun invoke(): Flow<List<CartItem>> {
        return cartRepository.cartItems
    }
}