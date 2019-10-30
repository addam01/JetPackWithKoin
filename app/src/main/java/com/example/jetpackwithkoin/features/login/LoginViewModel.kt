package com.example.jetpackwithkoin.features.login

import androidx.lifecycle.ViewModel
import com.example.jetpackwithkoin.core.SchedulerProvider
import com.example.jetpackwithkoin.db.DatabaseRepository
import com.example.jetpackwithkoin.db.entities.UsersEntity
import com.example.jetpackwithkoin.rest.GeneralRepository
import com.example.jetpackwithkoin.rest.models.SampleLoginResponse
import com.example.jetpackwithkoin.utilities.ObservableString
import com.github.ajalt.timberkt.Timber
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by owner on 12/06/2019
 */
class LoginViewModel(private val generalRepository: GeneralRepository, private val schedulerProvider: SchedulerProvider, private val databaseRepository: DatabaseRepository): ViewModel() {

    val username: ObservableString = ObservableString("")
    val password = ObservableString("")

    lateinit var callback: LoginCallback

    val compositeDisposable = CompositeDisposable()

    fun onLoginClicked(){
        callLogin().subscribeBy(onSuccess = {
            callback.onSuccess(it)
            saveUserInfo(it)

        }, onError = {
            Timber.e{it.message.toString()}
        })
    }

    private fun saveUserInfo(loginResponse: SampleLoginResponse) {
        val usersEntity = UsersEntity(loginResponse.username, loginResponse.password)
        Completable.fromAction { databaseRepository.saveUser(usersEntity) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver{
                override fun onComplete() {
                    callback.onSuccess("User info saved")
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })
    }

    fun callLogin(): Single<SampleLoginResponse> =
//            generalRepository.getLogin(username.get().toString(), password.get().toString()).compose(schedulerProvider.getSchedulersForSingle())
        generalRepository.getLogin().compose(schedulerProvider.getSchedulersForSingle())

    interface LoginCallback{
        fun onSuccess(response: SampleLoginResponse)
        fun onSuccess( message: String)
    }
}