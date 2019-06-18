package com.example.jetpackwithkoin.rest

import com.example.jetpackwithkoin.rest.models.PostsResponse
import com.example.jetpackwithkoin.rest.models.RepoModel
import com.example.jetpackwithkoin.rest.models.SampleLoginResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by owner on 12/06/2019
 */

class GeneralRepository( val api: GeneralService){

//    fun getLogin(username: String, password: String): Single<List<RepoModel>> =
//            api.listRepos(username)

    fun getLogin(): Single<SampleLoginResponse> = api.getSampleLogin()

    suspend fun getPost(): Response<List<PostsResponse>> = api.getPosts()
}