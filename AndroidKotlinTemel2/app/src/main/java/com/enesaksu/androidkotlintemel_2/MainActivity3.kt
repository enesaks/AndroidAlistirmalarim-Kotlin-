package com.enesaksu.androidkotlintemel_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.enesaksu.androidkotlintemel_2.databinding.ActivityMain2Binding
import com.enesaksu.androidkotlintemel_2.databinding.ActivityMain3Binding

private lateinit var binding: ActivityMain3Binding




class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.goToFirstActivity.setOnClickListener {
            goToFirstActvty(view)
        }

        //Yaşam Döngüleri..
        println("on Create")

    }

    override fun onStart() {
        println("on Start")
        super.onStart()
    }

    override fun onResume() {
        println("on resume")

        super.onResume()
    }

    override fun onPause() {
        println("on pause")

        super.onPause()
    }

    override fun onStop() {
        println("on stop")

        super.onStop()
    }

    override fun onDestroy() {
        println("on destroy")

        super.onDestroy()
    }


    fun goToFirstActvty(view: View){
        val intent = Intent(this@MainActivity3,MainActivity::class.java)
        finish()
        startActivity(intent)
    }

}