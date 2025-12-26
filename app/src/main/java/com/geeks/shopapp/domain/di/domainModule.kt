package com.geeks.shopapp.domain.di

import com.geeks.shopapp.domain.usecases.GetProductByIdUseCase
import com.geeks.shopapp.domain.usecases.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module{
    factory { GetProductsUseCase(repository = get()) }
    factory { GetProductByIdUseCase(repository = get()) }
}