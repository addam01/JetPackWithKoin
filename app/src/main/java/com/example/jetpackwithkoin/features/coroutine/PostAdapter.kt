package com.example.jetpackwithkoin.features.coroutine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackwithkoin.R
import com.example.jetpackwithkoin.databinding.AdapterPostBinding
import com.example.jetpackwithkoin.rest.models.PostsResponse

/**
 * Created by Addam on 19/06/2019
 */
class PostAdapter(var items: ArrayList<PostsResponse>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.adapter_post, parent, false
        ))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.model = items[position]
    }


    class PostViewHolder(val binding: AdapterPostBinding): RecyclerView.ViewHolder(binding.root)
}