package com.enesaksu.kennyyakalamaoyunu

import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Layout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginRight
import com.enesaksu.kennyyakalamaoyunu.databinding.ActivityMainBinding
import java.util.Random

private lateinit var binding: ActivityMainBinding

private var score = 0
private var second = 0



class MainActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        object : CountDownTimer(20000,1000) {
            override fun onTick(p0: Long) {
                binding.txtTime.text = "Time : ${p0/1000}"

                var rx = (10..250).random()
                var ry = (150..500).random()
                binding.imgKenny.x = dpTopx(rx)
                binding.imgKenny.y = dpTopx(ry)
            }

            override fun onFinish() {
                binding.txtTime.text = "Time : 0"

                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Game Over")
                    .setMessage("Restart the Game ?\nScore : $score")
                    .setPositiveButton("YES"){p0,p1 ->
                        intent = Intent(this@MainActivity,MainActivity::class.java)
                        finish()
                        startActivity(intent)
                    }
                    .setNegativeButton("QUIT"){p0,p1 ->
                        finish()
                    }.show()

            }

        }.start()

        binding.imgKenny.setOnClickListener {
            score += 1
            binding.txtScore.text = "Score : $score"
            var rx = (10..250).random()
            var ry = (150..500).random()
            binding.imgKenny.x = dpTopx(rx)
            binding.imgKenny.y = dpTopx(ry)

        }


    }

    private fun dpTopx(dp : Int) : Float{
        val density =resources.displayMetrics.density
        return dp*density
    }



}