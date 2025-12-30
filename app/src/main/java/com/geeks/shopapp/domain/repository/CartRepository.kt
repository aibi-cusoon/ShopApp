package com.geeks.shopapp.domain.repository

import com.geeks.shopapp.domain.models.CartItem
import com.geeks.shopapp.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    val cartItems: Flow<List<CartItem>>

    suspend fun addToCart(product: Product)

    suspend fun clearCart()

    suspend fun checkout(): Result<String>
}