package com.enesaksu.coroutinesalistirmasi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    //Dispatchers
    //Dispatchers.Default -> CPU Intensive
    //Dispatchers.IO -> Input / Output, Networking
    //Dispatchers.Main -> UI
    //Dispatchers.Unconfined -> Inherited dispatcher

    /*
    runBlocking {

        launch(Dispatchers.Main){
            println("Main Thread : ${Thread.currentThread().name}")
        }
        launch(Dispatchers.IO){
            println("Main Thread : ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default){
            println("Main Thread : ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined){
            println("Main Thread : ${Thread.currentThread().name}")
        }

    }

     */




}