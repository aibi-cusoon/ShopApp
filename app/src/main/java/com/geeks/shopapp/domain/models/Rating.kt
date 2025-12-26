package com.geeks.shopapp.domain.models


data class Rating(
    val count: Int,
    val rate: Double

) {
    companion object {
        fun empty(): Rating {
            return Rating(
                rate = 0.0,
                count = 0
            )
        }
    }
}
