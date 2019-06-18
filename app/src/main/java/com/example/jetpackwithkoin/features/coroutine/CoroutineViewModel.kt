package com.example.jetpackwithkoin.features.coroutine

import androidx.lifecycle.ViewModel
import com.example.jetpackwithkoin.core.SchedulerProvider
import com.example.jetpackwithkoin.rest.GeneralRepository
import com.example.jetpackwithkoin.rest.models.PostsResponse
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by owner on 17/06/2019
 */
class CoroutineViewModel(val generalRepository: GeneralRepository): ViewModel() {

    fun getPosts(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = generalRepository.getPost()
            withContext(Dispatchers.Main){
                try{
                    if(response.isSuccessful){
//                        Do something to show the UI
                    }else{
//                        Show error
                    }
                }catch (e: HttpException){
//                    Show http exception
                }catch (e: Throwable){
//                    Show other errors
                }
            }
        }
    }
}