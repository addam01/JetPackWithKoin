package com.example.jetpackwithkoin.features.starwars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackwithkoin.R
import com.example.jetpackwithkoin.databinding.ActivityStarWarsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class StarWarsActivity : AppCompatActivity() {

    private val viewModel: StarWarsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityStarWarsBinding = DataBindingUtil.setContentView(this, R.layout.activity_star_wars)
        mBinding.viewModel = viewModel

        mBinding.lifecycleOwner = this
    }
}
