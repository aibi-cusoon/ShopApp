package com.geeks.shopapp.domain.usecases

import com.geeks.shopapp.domain.models.Product
import com.geeks.shopapp.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product>{
        return repository.getProducts()
    }

}