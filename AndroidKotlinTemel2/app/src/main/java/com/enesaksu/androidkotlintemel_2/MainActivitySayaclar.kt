package com.enesaksu.androidkotlintemel_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import com.enesaksu.androidkotlintemel_2.databinding.ActivityMain3Binding
import com.enesaksu.androidkotlintemel_2.databinding.ActivityMainSayaclarBinding

    private lateinit var binding: ActivityMainSayaclarBinding
    var number = 0
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler(Looper.getMainLooper())



class MainActivitySayaclar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSayaclarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.goToFirstActvtiy.setOnClickListener { goToFirstActvty(view) }
        binding.start.setOnClickListener { start(view) }
        binding.stop.setOnClickListener { stop(view) }

        object : CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                binding.txtSayac.text = "Left : ${p0 / 1000}"
            }

            override fun onFinish() {
                binding.txtSayac.text = "Left : 0"
            }

        }.start()




    }


    fun goToFirstActvty(view: View){
        val intent = Intent(this@MainActivitySayaclar,MainActivity::class.java)
        finish()
        startActivity(intent)
    }

    fun start(view: View){
        number = 0

        runnable = object : Runnable{
            override fun run() {

                number += 1
                binding.time.text = "Time : $number"

                handler.postDelayed(this,1000)
            }
        }
        handler.post(runnable)
        binding.start.isEnabled = false
    }

    fun stop(view: View){
        binding.start.isEnabled =true
        number =0
        binding.time.text = "Time : 0"
        handler.removeCallbacks(runnable)

    }

}