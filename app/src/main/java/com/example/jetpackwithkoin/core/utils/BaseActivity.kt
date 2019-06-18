package com.example.jetpackwithkoin.core.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackwithkoin.core.Router

/**
 * Created by Addam on 18/06/2019
 */
open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun startActivity(from: Context, to: Class<*>, parameters: HashMap<Router.Parameter, Any?> = hashMapOf(), clearHistory: Boolean = false, singleTask: Boolean = false) {
        val intent = Intent(from, to)
        if (singleTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        }
        if (clearHistory) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        if (parameters.isNotEmpty()) {
            intent.putExtras(parameters.bundle)
        }
        startActivity(intent)
    }
}