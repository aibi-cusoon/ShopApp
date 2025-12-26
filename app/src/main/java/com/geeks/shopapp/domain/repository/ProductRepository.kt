package com.geeks.shopapp.domain.repository

import com.geeks.shopapp.domain.models.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById (id: Int): Product
}