package com.enesaksu.coroutinesalistirmasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Light Weightness
        /*

        GlobalScope.launch {
            repeat(100000){
                launch {
                    println("android")
                }
            }
        }
        */

        //Scope
        //Global Scope, runBlocking, CoroutineScope

        //Run Blocking
        /*
        println("run blocking start")
        runBlocking {
            launch {
                delay(3000)
                println("run blocking")
            }
        }
         */

        //Global Scope
        /*
        println("globol scope start")
        GlobalScope.launch {
            delay(5000)
            println("global scope..")
        }
        println("Global scope end")
         */

        //Context
        //Coroutune Scope
        /*
        println("coroutune scop start")
        CoroutineScope(Dispatchers.Default).launch {
            delay(5000)
            println("coroutune scop")
        }
        println("coroutune scop end")
         */




    }
}