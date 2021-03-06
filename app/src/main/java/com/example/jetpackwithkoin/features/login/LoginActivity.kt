package com.example.jetpackwithkoin.features.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackwithkoin.R
import com.example.jetpackwithkoin.databinding.ActivityLoginBinding
import com.example.jetpackwithkoin.rest.models.SampleLoginResponse
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), LoginViewModel.LoginCallback {
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this
        viewModel.callback = this
    }

    override fun onSuccess(response: SampleLoginResponse) {
        Toast.makeText(this@LoginActivity, response.username, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
    }
}
