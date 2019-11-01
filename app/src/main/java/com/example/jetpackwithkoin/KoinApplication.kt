package com.example.jetpackwithkoin

import android.app.Application
import com.example.jetpackwithkoin.core.PreferencesModule
import com.example.jetpackwithkoin.core.di.AppModule
import com.example.jetpackwithkoin.db.DatabaseRepository
import com.example.jetpackwithkoin.db.GeneralDatabaseModule
import com.example.jetpackwithkoin.features.login.LoginViewModel
import com.example.jetpackwithkoin.features.main.MainViewModel
import com.example.jetpackwithkoin.features.starwars.StarWarsViewModel
import com.example.jetpackwithkoin.rest.GeneralRepository
import com.github.ajalt.timberkt.Timber
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by owner on 12/06/2019
 */

class KoinApplication: Application(){

    lateinit var repoModules: Module
    lateinit var networkModules: Module
    lateinit var databaseModules: Module
    lateinit var sharedPrefModules: Module
    lateinit var viewModelModules: Module

    override fun onCreate() {
        super.onCreate()

        initLogger()

        loadModules()


        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(listOf(networkModules, repoModules, sharedPrefModules, databaseModules, viewModelModules))
        }
    }

    private fun loadModules(){
        networkModules = module {
            single{ AppModule(get()) }
            single { AppModule(get()).provideGson() }
//            If you have a custom Interceptor for your Header, can add here
//            single { AppModule().provideOkHttpClientCredential(get()) }
            single{ AppModule(get()).provideOKHttpClient()}
            single{ AppModule(get()).provideGeneralService(get(),get())}
            single{ AppModule(get()).provideSchedulerProvider()}
        }

        repoModules = module{
            single { GeneralRepository(get()) }
        }

        sharedPrefModules = module {
            single { PreferencesModule(get()) }
        }

        databaseModules = module{
            single { GeneralDatabaseModule() }
            single { GeneralDatabaseModule().provideDB(get())}
            single { GeneralDatabaseModule().provideUserDao(get()) }
            single { DatabaseRepository(get()) }
        }


        viewModelModules = module{
            viewModel { MainViewModel(get(),get()) }
            viewModel { LoginViewModel(get(), get(), get()) }
            viewModel { StarWarsViewModel(get(), get()) }
        }
    }

    private fun initLogger() {
        Timber.plant(timber.log.Timber.DebugTree())
    }

    fun reloadingModules(){
//        stopKoin()
//
//        loadModules()
//        startKoin {
//            androidLogger()
//            androidContext(this@KoinApplication)
//            modules(listOf(networkModules, repoModules, sharedPrefModules, databaseModules, viewModelModules))
//        }
        networkModules = module {
            single{ AppModule(get()) }
            single { AppModule(get()).provideGson() }
//            If you have a custom Interceptor for your Header, can add here
//            single { AppModule().provideOkHttpClientCredential(get()) }
            single{ AppModule(get()).provideOKHttpClient()}
            single{ AppModule(get()).provideGeneralService(get(),get())}
            single{ AppModule(get()).provideSchedulerProvider()}
        }

        unloadKoinModules(networkModules)

        loadKoinModules(networkModules)
    }
}