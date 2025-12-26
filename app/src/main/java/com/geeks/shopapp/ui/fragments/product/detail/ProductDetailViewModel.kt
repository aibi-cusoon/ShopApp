package com.geeks.shopapp.ui.fragments.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.shopapp.domain.models.Product
import com.geeks.shopapp.domain.usecases.GetProductByIdUseCase
import com.geeks.shopapp.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {

    private val _state =
        MutableStateFlow<UiState<Product>>(UiState.Loading)
    val state = _state.asStateFlow()

    fun loadProductById(id: Int) {
        viewModelScope.launch {
            val product = getProductByIdUseCase(id)
            _state.value = UiState.Success(product)
        }
    }
}
