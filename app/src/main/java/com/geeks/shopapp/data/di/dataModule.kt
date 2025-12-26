package com.geeks.shopapp.data.di

import com.geeks.shopapp.data.datasource.StoreApi
import com.geeks.shopapp.data.repository.ProductRepositoryImpl
import com.geeks.shopapp.domain.repository.ProductRepository
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}
private const val BASE_URL = "https://fakestoreapi.com/"

val dataModule = module {
    single {
        json.asConverterFactory("application/json".toMediaType())
    }

    single<Interceptor> (named("logging")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named("logging")))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
            .create(StoreApi::class.java)
    }
    single<ProductRepository> { ProductRepositoryImpl(api = get()) }

}