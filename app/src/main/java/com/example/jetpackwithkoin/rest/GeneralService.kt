package com.example.jetpackwithkoin.rest

import com.example.jetpackwithkoin.rest.models.Person
import com.example.jetpackwithkoin.rest.models.Planet
import com.example.jetpackwithkoin.rest.models.SampleLoginResponse
import com.example.jetpackwithkoin.rest.models.Starship
import io.reactivex.Single
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

//    @GET("posts")
//    suspend fun getPosts(): Response<List<PostsResponse>>

    @GET("people/{id}/")
    fun getPeople(@Path("id") id: String): Single<Person>

    @GET("planets/{id}/")
    fun getPlanet(@Path("id") id: String): Single<Planet>

    @GET("starships/{id}/")
    fun getStarship(@Path("id") id: String): Single<Starship>
}