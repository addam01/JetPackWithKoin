package com.example.jetpackwithkoin.core.di

import android.app.Application
import android.content.Context
import com.example.jetpackwithkoin.core.SchedulerProvider
import com.example.jetpackwithkoin.rest.GeneralRepository
import com.example.jetpackwithkoin.rest.GeneralService
import com.github.ajalt.timberkt.Timber
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by owner on 12/06/2019
 */

class AppModule{
        private val URL = "http://github.com/"

    fun provideOkHttpClientCredential(application: Application): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val cache = Cache(cacheDir, 10 * 1024 * 1024)

        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor{ chain ->
                Timber.d { "Added Authorization" }
                val original = chain.request()
                val builder = original.newBuilder()
                builder.addHeader("Authorization", Credentials.basic("admin", "1234"))
                val request = builder.build()
                chain.proceed(request)
            }
            .addInterceptor(interceptor)
            .build()
    }

    fun provideGeneralService(gson: Gson, okHttpClient: OkHttpClient): GeneralService{
        val baseUrl = URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(GeneralService::class.java)
    }

    fun provideSchedulerProvider() = SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread())

    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    fun getContext(application: Application): Context {
        return application
    }
}