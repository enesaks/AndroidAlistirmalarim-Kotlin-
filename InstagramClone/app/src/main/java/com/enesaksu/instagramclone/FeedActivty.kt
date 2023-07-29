package com.enesaksu.instagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enesaksu.instagramclone.databinding.ActivityFeedActivtyBinding
import com.enesaksu.instagramclone.databinding.ActivityMainBinding

private lateinit var binding: ActivityFeedActivtyBinding

class FeedActivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedActivtyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}