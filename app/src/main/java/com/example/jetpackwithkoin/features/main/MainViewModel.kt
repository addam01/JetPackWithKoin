package com.example.jetpackwithkoin.features.main

import androidx.lifecycle.ViewModel
import com.example.jetpackwithkoin.core.PreferencesModule
import com.example.jetpackwithkoin.core.Router
import com.example.jetpackwithkoin.core.event.StartActivityEvent
import com.example.jetpackwithkoin.core.event.StartActivityModel
import com.example.jetpackwithkoin.rest.GeneralRepository

/**
 * Created by owner on 18/06/2019
 */
class MainViewModel( val generalRepository: GeneralRepository, val preferencesModule: PreferencesModule): ViewModel() {

    val startActivityEvent = StartActivityEvent()

    fun goLogin(){
        startActivityEvent.value = StartActivityModel(Router.Destination.LOGIN)
    }

    fun goStarWars(){
        startActivityEvent.value = StartActivityModel(Router.Destination.STARWARS)
    }

    fun goSwap(){
        startActivityEvent.value = StartActivityModel(Router.Destination.SWAP)
    }
}