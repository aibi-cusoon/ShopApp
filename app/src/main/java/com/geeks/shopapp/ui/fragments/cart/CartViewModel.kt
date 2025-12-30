package com.geeks.shopapp.ui.fragments.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.shopapp.domain.models.Product
import com.geeks.shopapp.domain.usecases.AddToCartUseCase
import com.geeks.shopapp.domain.usecases.CheckOutUseCase
import com.geeks.shopapp.domain.usecases.ClearCartUseCase
import com.geeks.shopapp.domain.usecases.GetCartItemsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val checkOutUseCase: CheckOutUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {

    val items = getCartItemsUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val total = items.map { list ->
        list.sumOf { it.product.price * it.quantity }
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0.0)


    fun addToCart(product: Product) {
        viewModelScope.launch {
            addToCartUseCase(product)
        }
    }

    fun checkout() {
        viewModelScope.launch {
            val result = checkOutUseCase()
            result.onSuccess {
                Log.d("Cart", "Checkout successful")
                clearCartUseCase()
            }.onFailure {
                Log.e("Cart", "Checkout failed", it)
            }
        }
    }


}
