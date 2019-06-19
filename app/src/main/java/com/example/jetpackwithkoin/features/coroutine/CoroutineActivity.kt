package com.example.jetpackwithkoin.features.coroutine

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackwithkoin.R
import com.example.jetpackwithkoin.core.utils.BaseActivity
import com.example.jetpackwithkoin.core.utils.observe
import com.example.jetpackwithkoin.databinding.ActivityCoroutineBinding
import com.example.jetpackwithkoin.rest.models.PostsResponse
import kotlinx.android.synthetic.main.activity_coroutine.*
import org.koin.android.viewmodel.ext.android.viewModel

class CoroutineActivity : BaseActivity() {

    val viewModel: CoroutineViewModel by viewModel()

    lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityCoroutineBinding = DataBindingUtil.setContentView(this@CoroutineActivity, R.layout.activity_coroutine)
        mBinding.viewModel = viewModel

        setupRecyclerVew()
        setupEvent()
    }

    private fun setupEvent() {
        viewModel.getPosts()

        viewModel.items.observe(this){
            it?:return@observe
            adapter.run{
                this.items = it as ArrayList<PostsResponse>
                this.notifyDataSetChanged()
            }
        }
    }

    private fun setupRecyclerVew() {
        rv_post.layoutManager = LinearLayoutManager(this)
        adapter = PostAdapter(arrayListOf())
        rv_post.adapter = adapter
    }
}
