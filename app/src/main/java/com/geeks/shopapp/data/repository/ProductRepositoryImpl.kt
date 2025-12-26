package com.geeks.shopapp.data.repository

import com.geeks.shopapp.data.datasource.StoreApi
import com.geeks.shopapp.data.mappers.toDomain
import com.geeks.shopapp.domain.models.Product
import com.geeks.shopapp.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: StoreApi

): ProductRepository {
    override suspend fun getProducts(): List<Product> {
        val data = api.getAllProducts()
        return data.map {it.toDomain()}
    }

    override suspend fun getProductById(id: Int): Product {
        val data = api.getProductsById(id)
        return data.toDomain()
    }
}