package com.example.jetpackwithkoin.features.main

import androidx.lifecycle.ViewModel
import com.example.jetpackwithkoin.core.Router
import com.example.jetpackwithkoin.core.event.StartActivityEvent
import com.example.jetpackwithkoin.core.event.StartActivityModel

/**
 * Created by owner on 18/06/2019
 */
class MainViewModel: ViewModel() {

    val startActivityEvent = StartActivityEvent()

    fun goLogin(){
        startActivityEvent.value = StartActivityModel(Router.Destination.LOGIN)
    }

    fun goCoroutine(){
        startActivityEvent.value = StartActivityModel(Router.Destination.COROUTINES)
    }
}