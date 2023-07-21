package com.enesaksu.kotlinoopcalismasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = Usser("Enes",21)
        /*
        user.name = "ENes"
        user.age = 21
         */

        println("Name : ${user.name} Age : $user.age")

        val musician = Musician("Enes","Guitar",21)

        println(musician.retrunBandName("passawprd"))
        println(musician.retrunBandName("dasd"))

    }
}