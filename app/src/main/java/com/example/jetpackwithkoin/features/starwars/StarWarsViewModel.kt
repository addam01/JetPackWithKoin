package com.example.jetpackwithkoin.features.starwars

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.jetpackwithkoin.core.SchedulerProvider
import com.example.jetpackwithkoin.rest.GeneralRepository
import com.example.jetpackwithkoin.rest.models.Person
import com.example.jetpackwithkoin.rest.models.Planet
import com.example.jetpackwithkoin.rest.models.Starship
import com.example.jetpackwithkoin.utilities.ObservableString
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

/** Created by addam in 2019-10-31 **/
class StarWarsViewModel(private val generalRepository: GeneralRepository, private val schedulerProvider: SchedulerProvider): ViewModel() {

    var name = ObservableString("")
    var showLoading = ObservableBoolean(false)
    var searchComplete = ObservableBoolean(false)

    var personName = ObservableString("")
    var height = ObservableString("")
    var planet = ObservableString("")
    var spaceShip = ObservableString("")
    var starClass = ObservableString("")

    fun searchOnClick(){
        searchComplete.set(false)
        showLoading.set(true)
        val nameLength: String = name.get().toString().length.toString()

        getPerson(nameLength).subscribeBy(
            onSuccess = {
                personName.set(it.name)
                height.set(it.height)

                callPlanet(nameLength)
            }
        )
    }

    fun callPlanet(id:String){
        getPlanet(id).subscribeBy(
            onSuccess = {
                planet.set(it.name)

                callStarShip(id)
            }
        )
    }

    private fun callStarShip(id: String) {
        getStarShip(id).subscribeBy(onSuccess = {
            spaceShip.set(it.name)
            starClass.set(it.starship_class)

            showLoading.set(false)
            searchComplete.set(true)
        })
    }

    fun getPerson(id: String): Single<Person>{


        return generalRepository.getPerson(id).compose(schedulerProvider.getSchedulersForSingle())
    }

    fun getPlanet(id: String):Single<Planet>
    = generalRepository.getPlanet(id).compose(schedulerProvider.getSchedulersForSingle())

    fun getStarShip(id: String):Single<Starship>
    = generalRepository.getStarShip(id).compose(schedulerProvider.getSchedulersForSingle())
}