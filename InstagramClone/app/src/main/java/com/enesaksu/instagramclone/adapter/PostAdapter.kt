package com.enesaksu.instagramclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enesaksu.instagramclone.databinding.ReceyclerRowBinding
import com.enesaksu.instagramclone.model.Post
import com.squareup.picasso.Picasso

class PostAdapter(val postArrayList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostHolder>(){

    inner class PostHolder(val binding: ReceyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = ReceyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int {
        return postArrayList.size
    }


    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        holder.binding.emailTitle.text = postArrayList.get(position).email
        holder.binding.comment.text = postArrayList.get(position).comment
        Picasso.get().load(postArrayList.get(position).downloadUrl).into(holder.binding.imageView)


    }

}