package com.example.jetpackwithkoin

import android.app.Application
import com.example.jetpackwithkoin.core.di.AppModule
import com.example.jetpackwithkoin.features.LoginViewModel
import com.example.jetpackwithkoin.rest.GeneralRepository
import com.example.jetpackwithkoin.rest.GeneralService
import com.github.ajalt.timberkt.Timber
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Created by owner on 12/06/2019
 */

class KoinApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        initLogger()

        val repoModules = module{
            single { GeneralRepository(get()) }
        }

        val networkModules = module {
            single{ AppModule()}
            single { AppModule().provideGson() }
//            If you have a custom Interceptor for your Header, can add here
//            single { AppModule().provideOkHttpClientCredential(get()) }
            single{ AppModule().provideOKHttpClient(get())}
            single{ AppModule().provideGeneralService(get(),get())}
            single{ AppModule().provideSchedulerProvider()}
        }

        val databaseModules = module{

        }

        val sharedPrefModules = module {

        }

        val viewModelModules = module{
            viewModel { LoginViewModel(get(), get()) }
        }


        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(listOf(repoModules, networkModules, viewModelModules))
        }
    }


    private fun initLogger() {
        if(BuildConfig.DEBUG){
            Timber.plant(timber.log.Timber.DebugTree())
        }
    }
}