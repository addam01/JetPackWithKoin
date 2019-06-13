package com.example.jetpackwithkoin.rest

import com.example.jetpackwithkoin.rest.models.RepoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by owner on 12/06/2019
 */
interface GeneralService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String):
            Single<List<RepoModel>>
}