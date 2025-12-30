package com.geeks.shopapp.data.repository

import android.util.Log
import com.geeks.shopapp.data.datasource.StoreApi
import com.geeks.shopapp.data.model.CartProductDto
import com.geeks.shopapp.data.model.CartRequestDto
import com.geeks.shopapp.domain.models.CartItem
import com.geeks.shopapp.domain.models.Product
import com.geeks.shopapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CartRepositoryImpl(
    private val api: StoreApi,
) : CartRepository {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    override val cartItems = _cartItems.asStateFlow()

    override suspend fun addToCart(product: Product) {
        _cartItems.update { currentList ->
            val existing = currentList.find { it.product.id == product.id }
            val newList = if (existing != null){
                currentList.map { item ->
                    if (item.product.id == product.id) item.copy(quantity = item.quantity + 1)
                    else item
                }
            } else{
                currentList + CartItem(product, quantity = 1)
            }
            Log.d("CartRepo", "Cart after add: $newList")
            newList
        }
    }


    override suspend fun clearCart() {
        _cartItems.value = emptyList()
    }

    override suspend fun checkout(): Result<String> {
        return try {
            val items = _cartItems.value.map { CartProductDto(it.product.id, it.quantity) }
            Log.d("CartRepo", "Sending checkout request: $items")
            val resp = api.checkout(CartRequestDto(products = items))
            Log.d("CartRepo", "Checkout response: $resp")
            Result.success("Заказ №${resp.id} успешно оформлен")
        } catch (e: Exception) {
            Log.e("CartRepo", "Checkout error", e)
            Result.failure(e)
        }
    }
}