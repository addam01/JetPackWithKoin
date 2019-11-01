package com.example.jetpackwithkoin.features.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpackwithkoin.R
import com.example.jetpackwithkoin.core.Router
import com.example.jetpackwithkoin.core.event.StartActivityEvent
import com.example.jetpackwithkoin.core.event.StartActivityModel
import com.example.jetpackwithkoin.core.utils.BaseActivity
import com.example.jetpackwithkoin.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityMainBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this

        setupEvent()
    }

    private fun setupEvent() {
        viewModel.startActivityEvent.observe(this@MainActivity, object: StartActivityEvent.StartActivityObserver{
            override fun onStartActivity(data: StartActivityModel) {
                startActivity(this@MainActivity, Router.getClass(data.to))
            }

            override fun onStartActivityForResult(data: StartActivityModel) {
                startActivity(this@MainActivity, Router.getClass(data.to))
            }

        })
    }
}
