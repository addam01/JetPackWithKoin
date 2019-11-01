package com.example.jetpackwithkoin.features

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackwithkoin.KoinApplication
import com.example.jetpackwithkoin.R
import com.example.jetpackwithkoin.core.PreferencesModule

class SelectActivity : AppCompatActivity() {

    lateinit var application: KoinApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        application = KoinApplication()
    }

    fun onLoginSelect(view: View){
        val preferencesModule = PreferencesModule(this)
        preferencesModule.setUrl("https://my-json-server.typicode.com/addam01/demoJson/")
        application.reloadingModules()
        finish()
    }

    fun onStarWarsSelect(view: View){
        val preferencesModule = PreferencesModule(this)
        preferencesModule.setUrl("https://swapi.co/api/")
        application.reloadingModules()
        finish()
    }

}
