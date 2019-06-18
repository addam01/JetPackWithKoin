package com.example.jetpackwithkoin.rest

import com.example.jetpackwithkoin.rest.models.PostsResponse
import com.example.jetpackwithkoin.rest.models.RepoModel
import com.example.jetpackwithkoin.rest.models.SampleLoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by owner on 12/06/2019
 */
interface GeneralService {

//    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String):
//            Single<List<RepoModel>>

    @GET("login")
    fun getSampleLogin(): Single<SampleLoginResponse>

    @GET("posts")
    suspend fun getPosts(): Response<List<PostsResponse>>
}