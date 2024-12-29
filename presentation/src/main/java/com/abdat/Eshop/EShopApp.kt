package com.abdat.Eshop

import android.app.Application
import com.abdat.Eshop.di.presentationModule
import com.abdat.data.di.dataModule
import com.abdat.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class EShopApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EShopApp)
            modules(listOf (
                presentationModule,
                domainModule,
                dataModule,
            ))
        }
    }
}