package com.enesaksu.instagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enesaksu.instagramclone.databinding.ActivityMainBinding
import com.enesaksu.instagramclone.databinding.ActivityUploadBinding

private lateinit var binding: ActivityUploadBinding

class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




    }
}