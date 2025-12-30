package com.geeks.shopapp.domain.models

data class CartItem (
    val product: Product,
    val quantity: Int = 1
)