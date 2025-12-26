package com.geeks.shopapp.domain.usecases

import com.geeks.shopapp.domain.models.Product
import com.geeks.shopapp.domain.repository.ProductRepository

class GetProductByIdUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int): Product {
        return repository.getProductById(id)
    }
}