package com.geeks.shopapp.ui.fragments.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.shopapp.data.model.ProductDto
import com.geeks.shopapp.repository.ProductRepository
import com.geeks.shopapp.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel : ViewModel(){
    private val repository = ProductRepository()

    private val _state = MutableStateFlow<UiState<List<ProductDto>>>(UiState.Loading)
    val state: StateFlow<UiState<List<ProductDto>>> = _state.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts(){
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val products = repository.getProducts()
                _state.value = UiState.Success(products)
            }catch (e: Exception){
                _state.value = UiState.Error(e.message ?:"")
            }

        }
    }
}