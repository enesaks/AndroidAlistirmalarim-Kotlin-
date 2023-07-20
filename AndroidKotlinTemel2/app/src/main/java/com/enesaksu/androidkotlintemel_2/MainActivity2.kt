package com.enesaksu.androidkotlintemel_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.enesaksu.androidkotlintemel_2.databinding.ActivityMain2Binding

private lateinit var binding: ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.goToFirstActvty.setOnClickListener{
            goToFirstActvty(view)
        }

        val intentfromMain = intent
        val name = intentfromMain.getStringExtra("name")
        binding.txtName.text = "Name : $name"

    }

    fun goToFirstActvty(view: View){
        val intent = Intent(this@MainActivity2,MainActivity::class.java)
        finish()
        startActivity(intent)
    }

}