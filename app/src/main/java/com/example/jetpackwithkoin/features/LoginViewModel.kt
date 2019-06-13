package com.example.jetpackwithkoin.features

import androidx.lifecycle.ViewModel
import com.example.jetpackwithkoin.core.SchedulerProvider
import com.example.jetpackwithkoin.rest.GeneralRepository
import com.example.jetpackwithkoin.rest.models.RepoModel
import com.example.jetpackwithkoin.utilities.ObservableString
import com.github.ajalt.timberkt.Timber
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by owner on 12/06/2019
 */
class LoginViewModel(private val generalRepository: GeneralRepository, private val schedulerProvider: SchedulerProvider): ViewModel() {

    val username: ObservableString = ObservableString("")
    val password = ObservableString("")

    fun onLoginClicked(){
        callLogin().subscribeBy(onSuccess = {

        }, onError = {
            Timber.e{it.message.toString()}
        })
    }

    fun callLogin(): Single<List<RepoModel>> =
            generalRepository.getLogin(username.get().toString(), password.get().toString()).compose(schedulerProvider.getSchedulersForSingle())
}