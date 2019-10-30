package com.example.jetpackwithkoin

import android.app.Application
import com.example.jetpackwithkoin.core.di.AppModule
import com.example.jetpackwithkoin.db.DatabaseRepository
import com.example.jetpackwithkoin.db.GeneralDatabaseModule
import com.example.jetpackwithkoin.features.coroutine.CoroutineViewModel
import com.example.jetpackwithkoin.features.login.LoginViewModel
import com.example.jetpackwithkoin.features.main.MainViewModel
import com.example.jetpackwithkoin.rest.GeneralRepository
import com.github.ajalt.timberkt.Timber
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
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
            modules(listOf(repoModules, databaseModules, networkModules, viewModelModules))
        }
    }

    fun loadModules(){
        repoModules = module{
            single { GeneralRepository(get()) }
        }

        networkModules = module {
            single{ AppModule() }
            single { AppModule().provideGson() }
//            If you have a custom Interceptor for your Header, can add here
//            single { AppModule().provideOkHttpClientCredential(get()) }
            single{ AppModule().provideOKHttpClient(get())}
            single{ AppModule().provideGeneralService(get(),get())}
            single{ AppModule().provideSchedulerProvider()}
        }

        databaseModules = module{
            single { GeneralDatabaseModule() }
            single { GeneralDatabaseModule().provideDB(get())}
            single { GeneralDatabaseModule().provideUserDao(get()) }
            single { DatabaseRepository(get()) }
        }

        sharedPrefModules = module {

        }

        viewModelModules = module{
            viewModel { MainViewModel() }
            viewModel { LoginViewModel(get(), get(), get()) }
            viewModel{ CoroutineViewModel(get()) }
        }
    }

    private fun initLogger() {
        if(BuildConfig.DEBUG){
            Timber.plant(timber.log.Timber.DebugTree())
        }
    }

    fun reloadingModules(){
        stopKoin()

        loadModules()
        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(listOf(repoModules, databaseModules, networkModules, viewModelModules))
        }
    }
}