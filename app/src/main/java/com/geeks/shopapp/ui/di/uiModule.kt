package com.geeks.shopapp.ui.di

import com.geeks.shopapp.ui.fragments.cart.CartViewModel
import com.geeks.shopapp.ui.fragments.product.ListViewModel
import com.geeks.shopapp.ui.fragments.product.detail.ProductDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ListViewModel(
        getProductsUseCase = get(),
        addToCartUseCase = get()
    ) }
    viewModel { ProductDetailViewModel(getProductByIdUseCase = get()) }
    viewModel { CartViewModel(getCartItemsUseCase = get(),
        checkOutUseCase = get(),
        clearCartUseCase = get()) }
}