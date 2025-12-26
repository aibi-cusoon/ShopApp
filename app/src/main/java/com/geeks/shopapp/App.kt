package com.geeks.shopapp

import android.app.Application
import com.geeks.shopapp.data.di.dataModule
import com.geeks.shopapp.domain.di.domainModule
import com.geeks.shopapp.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)

            modules(
                dataModule,
                domainModule,
                uiModule
            )
        }
    }
}