package com.geeks.shopapp.data.mappers

import com.geeks.shopapp.data.model.ProductDto
import com.geeks.shopapp.data.model.RatingDto
import com.geeks.shopapp.domain.models.Product
import com.geeks.shopapp.domain.models.Rating

fun ProductDto.toDomain(): Product{
    return Product(
        id = this.id ?: -1,
        title = this.title ?: "",
        price = this.price ?: 0.0,
        description =  this.description ?: "",
        category = this.category ?: "",
        image = this.image ?:"",
        rating = this.rating?.toDomain() ?: Rating.empty()
    )

}

fun RatingDto.toDomain() : Rating {
    return Rating(
        rate = this.rate ?: 0.0,
        count = this.count ?: 0
    )
}