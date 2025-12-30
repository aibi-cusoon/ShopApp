package com.geeks.shopapp.domain.di

import com.geeks.shopapp.domain.usecases.AddToCartUseCase
import com.geeks.shopapp.domain.usecases.CheckOutUseCase
import com.geeks.shopapp.domain.usecases.ClearCartUseCase
import com.geeks.shopapp.domain.usecases.GetCartItemsUseCase
import com.geeks.shopapp.domain.usecases.GetProductByIdUseCase
import com.geeks.shopapp.domain.usecases.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module{
    factory { GetProductsUseCase(repository = get()) }
    factory { GetProductByIdUseCase(repository = get()) }

    factory { AddToCartUseCase(cartRepository = get()) }
    factory { CheckOutUseCase(cartRepository = get()) }
    factory { ClearCartUseCase(cartRepository = get()) }
    factory { GetCartItemsUseCase(cartRepository = get()) }
}