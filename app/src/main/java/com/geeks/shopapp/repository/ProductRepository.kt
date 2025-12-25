package com.geeks.shopapp.repository

import com.geeks.shopapp.data.api.RetrofitService
import com.geeks.shopapp.data.model.ProductDto

class ProductRepository {
    suspend fun getProducts(): List<ProductDto>{
        return RetrofitService.api.getAllProducts()
    }
    suspend fun getProductById(id: Int): ProductDto {
        return RetrofitService.api.getProductsById(id)
    }
}