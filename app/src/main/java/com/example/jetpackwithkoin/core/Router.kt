package com.example.jetpackwithkoin.core

import com.example.jetpackwithkoin.features.SelectActivity
import com.example.jetpackwithkoin.features.login.LoginActivity
import com.example.jetpackwithkoin.features.starwars.StarWarsActivity

/**
 * Created by Addam on 17/06/2019
 * A router class is needed to help navigation between Activities and Storing data names for bundles
 */
class Router {
    enum class Destination{
        LOGIN,
        STARWARS,

        SWAP
    }

    enum class Parameter{
        USERNAME,
        PASSWORD
    }

    companion object{
        fun getClass(destination: Destination): Class<*>{
            return when (destination){
                Destination.LOGIN -> LoginActivity::class.java
                Destination.STARWARS -> StarWarsActivity::class.java
                Destination.SWAP -> SelectActivity::class.java
            }
        }
    }
}