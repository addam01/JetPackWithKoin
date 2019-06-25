This is a simple Android application that uses MVVM with 

- [Kotlin Koin](https://github.com/InsertKoinIO/koin) for dependancy injection
- [Retrofit](https://square.github.io/retrofit/) for Network calls
- [RxKotlin](https://github.com/ReactiveX/RxKotlin) for thread management

**Here are the steps taken to build an application using KOIN**

# Step 1 #
In build.gradle app must have these
```
// Koin for Android
    implementation "org.koin:koin-android:$koin_version"
// Koin Android Scope features
    implementation "org.koin:koin-android-scope:$koin_version"
// Koin Android ViewModel features
    implementation "org.koin:koin-android-viewmodel:$koin_version"
// Koin Android Experimental features
    implementation "org.koin:koin-android-ext:$koin_version"

    implementation "io.reactivex.rxjava2:rxjava:$rxjava_version"
    implementation "io.reactivex.rxjava2:rxkotlin:$rxkotlin_version"
    implementation "io.reactivex.rxjava2:rxandroid:$rxandroid_version"
    
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
    implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-scalars:$retrofit_version"
    
```

Then make sure to have an Application class and add it into your manifest like [KoinApplication.kt](https://github.com/addam01/JetPackWithKoin/blob/master/app/src/main/java/com/example/jetpackwithkoin/KoinApplication.kt)

# Step 2 #
For every module/viewModels/Factory, just add those in their respective module variables in KoinApplication.kt, if there are new viewModels say UserViewModel

```kotlin
val viewModelModules = module{
            viewModel { UserViewModel(get(), get()) }
        }
```
The *get()* parameters are for modules already injected and needed for the viewModel class, like the LoginViewModel.kt has 2 params which are already injected in the 
```kotlin
val networkModules = module {
            single{ AppModule()}
            single { AppModule().provideGson() }
            single { AppModule().provideOkHttpClientCredential(get()) }
            single{ AppModule().provideGeneralService(get(),get())}
            single{ AppModule().provideSchedulerProvider()}
        }
```

# Step 3 #
Once the viewModel modules has been created, you can use them freely by injecting them with the *by viewModel()* keyword inside your activity. We call these *lazy* declaration.
```kotlin
val viewModel: LoginViewModel by viewModel()
```

# Notes #
In Koin there are 3 types of modules, 
- single{} for singletons or instances. Invoke them with ***by inject()***
- viewModel{} for viewModels. Invoke them with ***by viewModel()***
- factory{} if you need new instance everytime. Invoke them with ***by get()***
- 
# Reference taken from #
- [Understanding Dependency Injection with Koin - MVVM Clean Architecture for Android](https://www.modernietech.com/blog/2019/4/8/mvvm-with-clean-architecture-for-android-part-2-kotlin-clean-code)
- [Dependency Injection with Kotlin for Android newbies! (Koin,Retrofit and Coroutines)](https://medium.com/@w.g.sandaru/dependency-injection-with-kotlin-koin-retrofit-and-coroutines-android-6bcf6d9a907c)
- [Getting started with Android Architecture & ViewModel](https://insert-koin.io/docs/2.0/getting-started/android-viewmodel/)
- [Android Dependency Injection with Koin](https://dev.to/levimoreira/android-dependency-injection-with-koin-i34)
