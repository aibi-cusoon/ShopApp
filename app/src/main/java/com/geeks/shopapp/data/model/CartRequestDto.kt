package com.geeks.shopapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class CartRequestDto (
    @SerialName("userId") val userId: Int = 1,
    @SerialName("date") val data: String = "2025-12-20",
    @SerialName("products") val products: List<CartProductDto>
)

@Serializable
data class CartProductDto(
    @SerialName("productId") val productId: Int,
    @SerialName("quantity") val quantity: Int
)

@Serializable
data class CartResponseDto(
    @SerialName("id") val id: Int
)