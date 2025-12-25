package com.geeks.shopapp.ui.fragments.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.shopapp.data.model.ProductDto
import com.geeks.shopapp.repository.ProductRepository
import com.geeks.shopapp.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _state =
        MutableStateFlow<UiState<ProductDto>>(UiState.Loading)

    val state: StateFlow<UiState<ProductDto>> =
        _state.asStateFlow()

    fun loadProductById(id: Int) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val product = repository.getProductById(id)
                _state.value = UiState.Success(product)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "")
            }
        }
    }
}
