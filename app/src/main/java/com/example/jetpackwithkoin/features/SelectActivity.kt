package com.example.jetpackwithkoin.features

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackwithkoin.KoinApplication
import com.example.jetpackwithkoin.R
import com.example.jetpackwithkoin.core.PreferencesModule
import com.example.jetpackwithkoin.features.main.MainActivity
import kotlin.system.exitProcess

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
//        application.reloadingModules()
        restartAppDialog()
    }

    fun onStarWarsSelect(view: View){
        val preferencesModule = PreferencesModule(this)
        preferencesModule.setUrl("https://swapi.dev/api/")
//        application.reloadingModules()
        restartAppDialog()
    }

    private fun restartAppDialog(){
        AlertDialog.Builder(this)
            .setMessage("App will restart")
            .setPositiveButton("OK") { p0, p1 ->
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                exitProcess(0)
            }.show()
    }

}
