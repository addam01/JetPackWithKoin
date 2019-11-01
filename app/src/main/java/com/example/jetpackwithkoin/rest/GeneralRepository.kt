package com.example.jetpackwithkoin.rest

import com.example.jetpackwithkoin.rest.models.Person
import com.example.jetpackwithkoin.rest.models.Planet
import com.example.jetpackwithkoin.rest.models.SampleLoginResponse
import com.example.jetpackwithkoin.rest.models.Starship
import io.reactivex.Single

/**
 * Created by owner on 12/06/2019
 */

class GeneralRepository(private val api: GeneralService){

//    fun getLogin(username: String, password: String): Single<List<RepoModel>> =
//            api.listRepos(username)

    fun getLogin(): Single<SampleLoginResponse> = api.getSampleLogin()

//    suspend fun getPost(): Response<List<PostsResponse>> = api.getPosts()

    fun getPerson(id:String): Single<Person> = api.getPeople(id)
    fun getPlanet(id: String): Single<Planet> = api.getPlanet(id)
    fun getStarShip(id: String): Single<Starship> = api.getStarship(id)
}