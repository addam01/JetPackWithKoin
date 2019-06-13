package com.example.jetpackwithkoin.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpackwithkoin.R
import com.example.jetpackwithkoin.databinding.ActivityLoginBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.viewModel = viewModel
    }
}
