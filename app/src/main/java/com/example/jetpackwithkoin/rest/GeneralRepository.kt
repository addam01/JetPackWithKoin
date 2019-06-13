package com.example.jetpackwithkoin.rest

import com.example.jetpackwithkoin.rest.models.RepoModel
import io.reactivex.Single

/**
 * Created by owner on 12/06/2019
 */

class GeneralRepository( val api: GeneralService){

    fun getLogin(username: String, password: String): Single<List<RepoModel>> =
            api.listRepos(username)
}