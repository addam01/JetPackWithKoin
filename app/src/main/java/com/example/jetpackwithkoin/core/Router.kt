package com.example.jetpackwithkoin.core

import com.example.jetpackwithkoin.features.coroutine.CoroutineActivity
import com.example.jetpackwithkoin.features.login.LoginActivity

/**
 * Created by Addam on 17/06/2019
 * A router class is needed to help navigation between Activities and Storing data names for bundles
 */
class Router {
    enum class Destination{
        LOGIN,
        COROUTINES
    }

    enum class Parameter{
        USERNAME,
        PASSWORD
    }

    companion object{
        fun getClass(destination: Destination): Class<*>{
            return when (destination){
                Destination.LOGIN -> LoginActivity::class.java
                Destination.COROUTINES -> CoroutineActivity::class.java
            }
        }
    }
}